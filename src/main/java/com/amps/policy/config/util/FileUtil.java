package com.amps.policy.config.util;

import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
@Component
public class FileUtil {
    Logger logger = LogManager.getLogger(FileUtil.class.getName());

    Path createTempDirectory() {
        try {
            String customDirPrefix = "DataCuration_";
            Path ipuTempDirPath = Paths.get(System.getProperty("java.io.tmpdir") + File.separator);
            Path tempDirPath = Files.createTempDirectory(ipuTempDirPath, customDirPrefix);
            System.out.println("inTempDirectory()");
            System.out.println(tempDirPath.toString()+"");
            logger.info(" Temporary Directory " + tempDirPath + " is created ");
            return tempDirPath;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public File createZipTempFile(String fileName) {
        try {
            String tempFile = System.getProperty("java.io.tmpdir") + File.separator;
            File directory = new File(tempFile);
            File file = File.createTempFile(FilenameUtils.removeExtension(fileName),"."+ StringUtils.getFilenameExtension(fileName),directory);
            logger.info(file.getAbsolutePath());
            file.deleteOnExit();
            return file;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String extractFile(String unZipFilePath, String prefix, String suffix) {
        String extractedFilePath = null;
        File inputFile = new File(unZipFilePath);
        for (File file : inputFile.listFiles()) {
            if (file.getName().startsWith(prefix) && file.getName().endsWith(suffix))
                extractedFilePath = file.getAbsolutePath();

        }
        return extractedFilePath;

    }
}
