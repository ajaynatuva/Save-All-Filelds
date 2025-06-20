package com.amps.policy.config.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.graph.authentication.IAuthenticationProvider;
import com.microsoft.graph.models.extensions.DriveItem;
import com.microsoft.graph.models.extensions.IGraphServiceClient;
import com.microsoft.graph.requests.extensions.DriveItemCollectionPage;
import com.microsoft.graph.requests.extensions.GraphServiceClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
//import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static java.lang.String.format;

@Component
public class SharePointUtil {

    public static final String FILE_SEPARATOR = "/";

    @Value("${client.id}")
    String clientID;

    @Value("${client.secret}")
    String clientSecret;

    @Value("${tenant.id}")
    String tenantID;

    @Value("${user.name}")
    String userName;

    @Value("${user.password}")
    String password;

    @Value("${scope}")
    String scope;

    @Value("${sharepoint.app.location}")
    String sharePointAppLocation;

    @Value("${sharepoint.app.link}")
    String sharePointAppLink;

    @Value("${site.id}")
    String siteId;
    @Value("${drive.id}")
    String driveId;

    @Autowired
    FileUtil fileUtil;


    String destinationPath;

    Logger logger = LogManager.getLogger(SharePointUtil.class.getName());

    public IGraphServiceClient getAuthProvider() {
        IAuthenticationProvider mAuthenticationProvider;
        try {
            String accessToken = getAuthToken();
            mAuthenticationProvider = request -> request.addHeader("Authorization", "Bearer " + accessToken);
        } catch (Exception e) {
            throw new Error("Could not create a graph client: " + e.getLocalizedMessage());
        }
        return GraphServiceClient.builder().authenticationProvider(mAuthenticationProvider).buildClient();
    }

    private String getAuthToken() {
        try {
            CloseableHttpClient client = HttpClientBuilder.create().build();
            HttpPost request = new HttpPost(format("https://login.microsoftonline.com/%s/oauth2/v2.0/token", tenantID));
            request.addHeader("Content-Type", "application/x-www-form-urlencoded");
            request.addHeader("cache-control", "no-cache");
            List<BasicNameValuePair> nvps = new ArrayList<>();
            nvps.add(new BasicNameValuePair("client_id", clientID));
            nvps.add(new BasicNameValuePair("client_secret", clientSecret));
            nvps.add(new BasicNameValuePair("scope", scope));
            nvps.add(new BasicNameValuePair("grant_type", "client_credentials"));
            nvps.add(new BasicNameValuePair("username", userName));
            nvps.add(new BasicNameValuePair("password", password));
            request.setEntity(new UrlEncodedFormEntity(nvps, StandardCharsets.UTF_8));
            CloseableHttpResponse response = client.execute(request);
            ObjectMapper mapper = new ObjectMapper();
            final JsonNode jsonNode = mapper.readTree(response.getEntity().getContent());
            return jsonNode.get("access_token").textValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getURL(String link) {
        link = link.trim();
        link = link.replaceAll("\\s", "%20");
        return link;
    }

    public void uploadFile(File file, String destinationFilePath) {
        try {
            if (file.isDirectory())
                return;
            destinationFilePath = destinationFilePath + FILE_SEPARATOR + file.getName();
//			InputStream inputStream = new FileInputStream(file);
            GraphServiceClient graphClient = (GraphServiceClient) getAuthProvider();
            byte[] fileArray = Files.readAllBytes(file.toPath());

            graphClient.sites(siteId).drives(driveId).root().itemWithPath(getURL(destinationFilePath)).content()
                    .buildRequest().put(fileArray);
            System.out.println("file uploaded sucessfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveFileToSharePoint(String inputFile, String destinationPath, String sourceName) {
        File inputSourceFolder = new File(inputFile);
        this.destinationPath = !sourceName.equals("") ? destinationPath + sourceName : destinationPath;
        isFolderExists(this.destinationPath);
        readFiles(inputSourceFolder.listFiles(), this.destinationPath);
    }

    private void readFiles(File[] files, String fileDestinationPath) {
        for (File file : files) {
            if (file.isDirectory()) {
                fileDestinationPath = fileDestinationPath + FILE_SEPARATOR + file.getName();
                isFolderExists(fileDestinationPath);
                readFiles(file.listFiles(), destinationPath + FILE_SEPARATOR + file.getName());
                readAndUploadFileToSharePoint(file, fileDestinationPath);
            } else {
                readAndUploadFileToSharePoint(file, fileDestinationPath);
            }
        }
    }

    private boolean isFolderExists(String folderPath) {

        return false;
    }

    public void readAndUploadFileToSharePoint(File file, String destinationFilePath) {
        try {
            if (file.isDirectory())
                return;
            destinationFilePath = destinationFilePath + FILE_SEPARATOR + file.getName();
            logger.info("Uploading file to sharePoint at location : " + destinationFilePath);
            GraphServiceClient graphClient = (GraphServiceClient) getAuthProvider();
            byte[] fileArray = Files.readAllBytes(file.toPath());
            graphClient.sites(siteId).drives(driveId).root().itemWithPath(getURL(destinationFilePath)).content()
                    .buildRequest().put(fileArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public InputStream getFileAsStream(String completePathOfFile) throws Exception {
        try {
            IGraphServiceClient graphClient = getAuthProvider();
            InputStream driveItem = graphClient.sites(siteId).drives(driveId).root()
                    .itemWithPath(getURL(completePathOfFile)).content().buildRequest().get();
            return driveItem;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Exception in fetching File");
        }
    }

    public void uploadFileToSharePoint(File filePath, String destinationFilePath) {
        if (filePath.isDirectory())
            readFiles(filePath.listFiles(), destinationPath);
        uploadFile(filePath, destinationFilePath);
    }

    public String getLatestFileContent(String folderPath) {
        // List files in the specified folder
        IGraphServiceClient graphClient = getAuthProvider();

        DriveItemCollectionPage filesPage = (DriveItemCollectionPage) graphClient.sites(siteId).drives(driveId).root()
                .itemWithPath(folderPath).children().buildRequest().get();

        // Convert collection page to a list
        List<DriveItem> files = filesPage.getCurrentPage();

        List<DriveItem> docxFiles = files.stream().filter(item -> item.name != null && item.name.endsWith(".docx"))
                .collect(Collectors.toList());

        // Sort files by last modified date in descending order
        List<DriveItem> sortedFiles = docxFiles.stream()
                .sorted(Comparator.comparing(item -> item.lastModifiedDateTime, Comparator.reverseOrder()))
                .collect(Collectors.toList());

        // Get the latest file (first in the sorted list)
        if (!sortedFiles.isEmpty()) {
            DriveItem latestFile = sortedFiles.get(0);
            String latestFilePath = folderPath + "/" + latestFile.name;

            // Retrieve content of the latest file as InputStream
            return latestFile.name;
        }

        // Return null if no files are found
        return null;
    }

    String getCurrentDate() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MMddyyyyHHmmss");
        String currentDate = formatter.format(date);
        return currentDate;
    }

    String getConfigDate() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String currentDate = formatter.format(date);
        return currentDate;
    }

    String getConfigTime() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("HHmmss");
        String currentDate = formatter.format(date);
        return currentDate;
    }

    public String getSharePointLookupPath(String className) {
        String sharePointPath = sharePointAppLocation + "Lookups" + FILE_SEPARATOR + className + FILE_SEPARATOR
                + getCurrentDate();
        return sharePointPath;
    }

    public String getSharePointSourcePath(Integer policyId, String sourceName) {
        return sharePointAppLocation + sourceName + FILE_SEPARATOR + policyId + FILE_SEPARATOR
                + getCurrentDate() + FILE_SEPARATOR + "SOURCE";
    }

    public String getSharePointDeltaFolderPath(String sourcePath, String deltaFolder) {
        return sourcePath.split("SOURCE")[0] + deltaFolder;
    }

    public String getSharePointDeltaAndExceptionLink(String deltaPath) {
        String path = sharePointAppLink + deltaPath;
        return getURL(path);
    }

    public String getSharePointLink(String path) {
        String sharePointPath = sharePointAppLink + getURL(path);
        return getURL(sharePointPath);
    }

    public String getSharePointConfigValidationPathForSingleRule(String policyNumber, String policyVersion) {
        String folder = policyNumber + "-" + policyVersion;
        String sharePointPath = sharePointAppLocation + "Reports" + FILE_SEPARATOR + "Config_Validation"
                + FILE_SEPARATOR + "Single_Policy" + FILE_SEPARATOR + folder + FILE_SEPARATOR + getConfigDate()
                + FILE_SEPARATOR + getConfigTime();

        return sharePointPath;
    }

    public String getSharePointConfigValidationPath(String folderName) {
        String sharePointPath = sharePointAppLocation + "Reports" + FILE_SEPARATOR + "Config_Validation"
                + FILE_SEPARATOR + "All_Policies" + FILE_SEPARATOR + folderName + FILE_SEPARATOR + getConfigDate()
                + FILE_SEPARATOR + getConfigTime();
        return sharePointPath;
    }

    public String unzipFile(String inputFilePath) throws Exception {
        String outputFilePath = "";
        byte[] buffer = new byte[1024];
        File destinationFolder = new File(fileUtil.createTempDirectory().toString());
        ZipInputStream zis = new ZipInputStream(new FileInputStream(inputFilePath));
        outputFilePath = destinationFolder.getAbsolutePath();
        ZipEntry zipEntry = zis.getNextEntry();
        while (zipEntry != null) {

            while (zipEntry != null) {

                File newFile = newFile(destinationFolder, zipEntry);
                if (zipEntry.isDirectory()) {
                    if (!newFile.isDirectory() && !newFile.mkdirs())
                        throw new IOException("Failed to create directory " + newFile);
                } else {
                    File parent = newFile.getParentFile();
                    if (!parent.isDirectory() && !parent.mkdirs())
                        throw new IOException("Failed to create directory " + parent);

                    FileOutputStream fos = new FileOutputStream(newFile);
                    int len;
                    while ((len = zis.read(buffer)) > 0) {
                        fos.write(buffer, 0, len);
                    }
                    fos.close();
                    parent.deleteOnExit();
                    newFile.deleteOnExit();
                }
                zipEntry = zis.getNextEntry();
            }
        }
        zis.closeEntry();
        zis.close();
        destinationFolder.deleteOnExit();
        return outputFilePath;
    }

    public static File newFile(File destinationDir, ZipEntry zipEntry) throws IOException {
        File destFile = new File(destinationDir, zipEntry.getName());

        String destDirPath = destinationDir.getCanonicalPath();
        String destFilePath = destFile.getCanonicalPath();

        if (!destFilePath.startsWith(destDirPath + File.separator)) {
            throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
        }

        return destFile;
    }

}
