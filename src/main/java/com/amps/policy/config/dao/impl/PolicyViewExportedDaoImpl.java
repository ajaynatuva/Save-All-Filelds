package com.amps.policy.config.dao.impl;

import com.amps.policy.config.dao.PolicyViewExportedDao;
import com.amps.policy.config.dto.PolicyViewExportDTO;
import com.amps.policy.config.model.ClaimTypeLookUp;
import com.amps.policy.config.util.SharePointUtil;

import jakarta.annotation.PostConstruct;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.*;
//import org.openxmlformats.schemas.officeDocument.x2006.math.CTText;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.*;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.Year;
import java.util.*;

import java.io.IOException;
import java.io.InputStream;

@Component
public class PolicyViewExportedDaoImpl implements PolicyViewExportedDao {

	@Autowired
	public NamedParameterJdbcTemplate ipuNamedParameterJdbcTemplate;
	private static NamedParameterJdbcTemplate staticIpuNamedParameterJdbcTemplate;

	@PostConstruct
	public void init() {
		staticIpuNamedParameterJdbcTemplate = ipuNamedParameterJdbcTemplate;
	}

	private static final int TABLE_WIDTH = 13800;
	private static final int FIXED_COLUMN_WIDTH = 1000;
	private static final int FIXED_FIRST_COLUMN_WIDTH = 11200;
	private static final int FIXED_SECOND_COLUMN_WIDTH = 1800;

	static final HashSet<String> uniqueMedicalDesc = new HashSet<String>();
	static final HashSet<String> uniqueMedicalSummary = new HashSet<String>();
	static final HashSet<String> uniqueSubDesc = new HashSet<String>();
	static final HashSet<String> uniqueSubSummary = new HashSet<String>();
	static final HashSet<String> uniqueReference = new HashSet<String>();

	public static List<ClaimTypeLookUp> ClaimDesc = new ArrayList<>();

	private static String currentMedicalDesc = null;
	private static String currentSubDesc = null;
	private static String currentReasonCodeDesc = null;
	public static byte[] ClaimIntroData;

	@Value("${sharepoint.app.location}")
	String sharePointAppLocation;

	@Autowired
	SharePointUtil sharePointUtil;

	@Value("${sharepoint.app.link}")
	String sharePointAppLink;

	@Override
	public byte[] getExportPolicies(List<PolicyViewExportDTO> policyNumber, String buttonType) {
		getClaimIntroFile();
		List<Integer> policyNumbers = new ArrayList<>();
		List<Integer> medicalPolicyKeys = new ArrayList<>();

//		List<ClaimTypeLookUp> claimTypeDesc = new ArrayList<ClaimTypeLookUp>();
		for (PolicyViewExportDTO dto : policyNumber) {
			List<Integer> numbers = List.of(dto.getPolicyNumber());
			List<Integer> medicalKeys = List.of(dto.getMedicalPolicyKeyFk());
			policyNumbers.addAll(numbers);
			medicalPolicyKeys.addAll(medicalKeys);
		}

		String saveQuery = """
				SELECT
				     policy_number,
					 p.priority,
					 category_fk,
					 c.policy_category_desc AS category_desc,
					 reason_code_fk,
					 d.reason_desc AS reason_code_desc,
					 medical_policy_key_fk,
					 medical_policy_summary as med_summary,
					 sub_policy_summary as sub_summary,
					 e.medical_policy_title AS medical_desc,
					 e.medical_policy_summary AS medicalPolicySummary,
					 sub_policy_key_fk,
					 f.sub_policy_title AS sub_desc,
					 f.sub_policy_summary AS subPolicySummary,
					 taxonomy_logic_fk,
					 g.description AS taxonomyDesc,
					 min_age_fk,
					 h.min_max_age_desc AS min_age_desc,
					 max_age_fk,
					 i.min_max_age_desc AS max_age_desc,
					 npi_logic_fk,
					 j.description AS npi_desc,
					 claim_type,
					 deactivated,
					 notes,
					 policy_desc,
					 tax_logic_fk,
					 k.description AS tax_desc,
					 reference,
					 ref_source_desc
				 FROM
					 policy.policy p
				 LEFT JOIN
					 policy.policy_category_lkp c ON c.policy_category_lkp_id = p.category_fk
				 LEFT JOIN
					 policy.reasoncode_lkp d ON d.reason_code = p.reason_code_fk
				 LEFT JOIN
					 policy.medical_policies e ON e.medical_policy_key = p.medical_policy_key_fk
				 LEFT JOIN
					 policy.sub_policies f ON f.sub_policy_key = p.sub_policy_key_fk
				 LEFT JOIN
					 policy.taxonomy_link_lkp g ON g.taxonomy_link_lkp_key = p.taxonomy_logic_fk
				 LEFT JOIN
					 policy.min_max_age_lkp h ON h.min_max_age_lkp_id = p.min_age_fk
				 LEFT JOIN
					 policy.min_max_age_lkp i ON i.min_max_age_lkp_id = p.max_age_fk
				 LEFT JOIN
					 policy.npi_link_lkp j ON j.npi_link_lkp_key = p.npi_logic_fk
				 LEFT JOIN
					 policy.tax_link_lkp k ON k.tax_link_lkp_key = p.tax_logic_fk
				 WHERE
					 policy_number IN (:policyNumbers) and p.medical_policy_key_fk IN (:medicalPolicyKeys)
				 ORDER BY
					 medical_desc, sub_desc, reason_code_desc
				           """;

		MapSqlParameterSource inQueryParams = new MapSqlParameterSource();
		inQueryParams.addValue("policyNumbers", policyNumbers);
		inQueryParams.addValue("medicalPolicyKeys", medicalPolicyKeys);
		List<PolicyViewExportDTO> PolicyData = staticIpuNamedParameterJdbcTemplate.query(saveQuery, inQueryParams,
				new BeanPropertyRowMapper<>(PolicyViewExportDTO.class));

		String sql = "select * from policy.claim_type_lkp";

//        ClaimDesc = ipuNamedParameterJdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ClaimTypeLookUp.class));

		try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {

			// Create a new Word document
			XWPFDocument document = new XWPFDocument();
			landScape(document);
			ClassLoader classLoader = getClass().getClassLoader();
			Path ampsImage = Paths.get(Objects.requireNonNull(classLoader.getResource("Images/ampsLogo.png")).toURI());
			Path claimInsight = Paths
					.get(Objects.requireNonNull(classLoader.getResource("Images/claimInsight.png")).toURI());
			addHeadersAndFooters(document);
			addImages(document, ampsImage, claimInsight);
			addTableOfContents(document, PolicyData, buttonType);
			ImportFile(document);
			Integer currentPolicyNumber = null;

			for (PolicyViewExportDTO policyData : PolicyData) {

				if (buttonType.equals("Internal")) {
					if (currentPolicyNumber == null || !currentPolicyNumber.equals(policyData.getPolicyNumber())) {
						if (currentPolicyNumber != null) {
							document.createParagraph().setPageBreak(true);
						}
						currentPolicyNumber = policyData.getPolicyNumber();
					}
					addDataToDocument(document, policyData, buttonType);

				}
			}

			processPolicyData(document, PolicyData, buttonType);

			document.write(out);
			return out.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}

	private static String extractKeyFromFileName(String fileName) {
		// Assuming the key is the part before the first underscore or dot
		int endIndex = fileName.indexOf('_');
		if (endIndex == -1) {
			endIndex = fileName.indexOf('.');
		}
		return (endIndex != -1) ? fileName.substring(0, endIndex) : fileName;
	}

	public void ImportFile(XWPFDocument document) throws FileNotFoundException {
		try (ByteArrayInputStream bais = new ByteArrayInputStream(ClaimIntroData)) {
			XWPFDocument embeddedDoc = new XWPFDocument(bais);
			copyStylesOfClonedDoc(embeddedDoc, document);
			for (IBodyElement element : embeddedDoc.getBodyElements()) {
				if (element instanceof XWPFTable) {
					XWPFTable embeddedTable = (XWPFTable) element;
					XWPFTable newTable = document.createTable();
					newTable.getCTTbl().set(embeddedTable.getCTTbl().copy());
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		document.createParagraph().setPageBreak(true);
	}

	public void addPageBorder(XWPFDocument document) {
		CTDocument1 ctDocument = document.getDocument();
		CTBody ctBody = ctDocument.getBody();
		CTSectPr ctSectPr = (ctBody.isSetSectPr()) ? ctBody.getSectPr() : ctBody.addNewSectPr();
		CTPageSz ctPageSz = (ctSectPr.isSetPgSz()) ? ctSectPr.getPgSz() : ctSectPr.addNewPgSz();
		// paper size letter
		ctPageSz.setW(java.math.BigInteger.valueOf(Math.round(11 * 1440))); // 8.5 inches
		ctPageSz.setH(java.math.BigInteger.valueOf(Math.round(8.5 * 1440))); // 11 inches
		// page borders
		CTPageBorders ctPageBorders = (ctSectPr.isSetPgBorders()) ? ctSectPr.getPgBorders()
				: ctSectPr.addNewPgBorders();
		ctPageBorders.setOffsetFrom(STPageBorderOffset.PAGE);
		for (int b = 0; b < 4; b++) {
			CTBorder ctBorder = (ctPageBorders.isSetTop()) ? ctPageBorders.getTop() : ctPageBorders.addNewTop();
			if (b == 1)
				ctBorder = (ctPageBorders.isSetBottom()) ? ctPageBorders.getBottom() : ctPageBorders.addNewBottom();
			else if (b == 2)
				ctBorder = (ctPageBorders.isSetLeft()) ? ctPageBorders.getLeft() : ctPageBorders.addNewLeft();
			else if (b == 3)
				ctBorder = (ctPageBorders.isSetRight()) ? ctPageBorders.getRight() : ctPageBorders.addNewRight();
			ctBorder.setVal(STBorder.THREE_D_EMBOSS);
			ctBorder.setSz(java.math.BigInteger.valueOf(3));
			ctBorder.setSpace(java.math.BigInteger.valueOf(15));
			ctBorder.setColor("CBD2D6");
		}
	}

	public static void copyStylesOfClonedDoc(XWPFDocument sourceDocument, XWPFDocument destinationDocument)
			throws XmlException, IOException {
		XWPFStyles documentStyles = destinationDocument.createStyles();
		documentStyles.setStyles(sourceDocument.getStyle());
		List<XWPFParagraph> oldParagraphs = sourceDocument.getParagraphs();
		for (XWPFParagraph oldPar : oldParagraphs) {
			XWPFParagraph newPar = destinationDocument.createParagraph();
			for (XWPFRun oldRun : oldPar.getRuns()) {
				XWPFRun newParRun = newPar.createRun();
				String runText = oldRun.getText(0);
				newParRun.setText(runText);
				newParRun.setFontFamily("Arial");
				CTRPr oldCTRPr = oldRun.getCTR().getRPr();
				if (oldCTRPr != null) {
					String carStyle = oldRun.getStyle();
					newParRun.setText(oldRun.getText(0));
					newParRun.addBreak();
					newParRun.setStyle(carStyle);
				}
			}
		}
	}

	public static boolean isBulletFormat(XWPFParagraph paragraph) {
		// Check if the paragraph has bullet format
		return paragraph.getNumID() != null;
	}

	public static int getIndentationLevel(XWPFParagraph paragraph) {
		// Determine indentation level (usually based on left indentation)
		return paragraph.getIndentationLeft() / 720; // 720 TWIPs = 1/2 inch
	}

	String path;

	private void landScape(XWPFDocument document) {
		CTSectPr sectPr1 = document.getDocument().getBody().addNewSectPr();

		// Set landscape orientation
		sectPr1.addNewPgSz().setOrient(STPageOrientation.LANDSCAPE);

		// Set page size to A3
		sectPr1.getPgSz().setW(BigInteger.valueOf(16840)); // Width for A3
		sectPr1.getPgSz().setH(BigInteger.valueOf(11907));
	}

	private void addImages(XWPFDocument document, Path ampsImage, Path claimInsightImage) throws Exception {
		// Create a table with one row and two columns
		XWPFTable table = document.createTable(1, 2);
		removeTableBorders(table);

		table.setWidth("100%");
		// Add the first image to the first cell
		addImageToCell(table.getRow(0).getCell(0), ampsImage, 130, 60, ParagraphAlignment.LEFT);
		// Add the second image to the second cell
		addImageToCell(table.getRow(0).getCell(1), claimInsightImage, 200, 65, ParagraphAlignment.RIGHT);
		// Add title below the images
		addTitle(document, "Advanced Medical Pricing Solutions", ParagraphAlignment.LEFT);
		// Add gap after the table
		XWPFParagraph gapParagraph = document.createParagraph();
		gapParagraph.setSpacingAfter(1500);
		// Create and configure the box table
		XWPFTable boxTable = createBoxTable(document);
		configureBoxTableBorders(boxTable);
		// Add a page break after the box table
		XWPFParagraph pageBreakParagraph = document.createParagraph();
		XWPFRun pageBreakRun = pageBreakParagraph.createRun();
		addPageBorder(document);

		pageBreakRun.addBreak(BreakType.PAGE);
	}

	private void removeTableBorders(XWPFTable table) {
		CTTbl ctTbl = table.getCTTbl();
		CTTblPr tblPr = ctTbl.getTblPr();
		if (tblPr == null)
			tblPr = ctTbl.addNewTblPr();
		CTTblBorders borders = tblPr.addNewTblBorders();
		borders.addNewTop().setVal(STBorder.NONE);
		borders.addNewBottom().setVal(STBorder.NONE);
		borders.addNewLeft().setVal(STBorder.NONE);
		borders.addNewRight().setVal(STBorder.NONE);
		borders.addNewInsideH().setVal(STBorder.NONE);
		borders.addNewInsideV().setVal(STBorder.NONE);
	}

	private void addImageToCell(XWPFTableCell cell, Path imagePath, int width, int height, ParagraphAlignment alignment)
			throws Exception {
		XWPFParagraph paragraph = cell.addParagraph();
		cell.removeParagraph(0); // Remove the default empty paragraph
		paragraph.setAlignment(alignment);
		XWPFRun run = paragraph.createRun();
		try (InputStream is = Files.newInputStream(imagePath)) {
			run.addPicture(is, XWPFDocument.PICTURE_TYPE_PNG, imagePath.getFileName().toString(), Units.toEMU(width),
					Units.toEMU(height));
		}
	}

	private XWPFTable createBoxTable(XWPFDocument document) {
		XWPFTable boxTable = document.createTable(2, 1);
		boxTable.setWidth("60%"); // Set the width of the table

		// Add content to the first row (name)
		addBoxTableRow(boxTable.getRow(0).getCell(0), "Client Name: ");

		// Add content to the second row (date)
		addBoxTableRow(boxTable.getRow(1).getCell(0), "Date: ");

		// Optionally add some padding to the box table cells
		CTTblWidth tableIndentation = boxTable.getCTTbl().getTblPr().addNewTblInd();
		tableIndentation.setW(BigInteger.valueOf(2300)); // 720 TWentieths of an Inch Point (Twips) = 720/20 = 36 pt =
															// 36/72 = 0.5"
		tableIndentation.setType(STTblWidth.DXA);

		return boxTable;
	}

	private void addBoxTableRow(XWPFTableCell cell, String text) {
		XWPFParagraph paragraph = cell.addParagraph();
		cell.removeParagraph(0); // Remove the default empty paragraph
		XWPFRun run = paragraph.createRun();
		run.setFontSize(20);
		run.setBold(true);
		run.setText(text);
	}

	private void configureBoxTableBorders(XWPFTable boxTable) {
		CTTbl ctTbl = boxTable.getCTTbl();
		CTTblPr tblPr = ctTbl.getTblPr();
		if (tblPr == null)
			tblPr = ctTbl.addNewTblPr();
		CTTblBorders borders = tblPr.addNewTblBorders();
		borders.addNewTop().setVal(STBorder.SINGLE);
		borders.addNewBottom().setVal(STBorder.SINGLE);
		borders.addNewLeft().setVal(STBorder.SINGLE);
		borders.addNewRight().setVal(STBorder.SINGLE);
		borders.addNewInsideH().setVal(STBorder.SINGLE);
		borders.addNewInsideV().setVal(STBorder.SINGLE);
	}

	public void getClaimIntroFile() {
		try {
			// Prepare the SQL statement
			String sql = "select claim_introduction from policy.claim_processing_introduction order by id desc limit 1\n";
			// Prepare the parameters
			Map<String, Object> params = new HashMap<>();
			// Execute the query and retrieve the file data
			ClaimIntroData = ipuNamedParameterJdbcTemplate.queryForObject(sql, params, byte[].class);
			// Now you have the file content in baos
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void addTitle(XWPFDocument document, String title, ParagraphAlignment alignment) {
		XWPFParagraph paragraph = addParagraph(document, title, 25, alignment);
		if (paragraph.getRuns().size() > 0) {
			XWPFRun run = paragraph.getRuns().get(0);
			if (run != null) {
				CTR ctr = run.getCTR();
				if (ctr != null) {
					CTRPr rpr = (ctr.isSetRPr()) ? ctr.getRPr() : ctr.addNewRPr();
					if (rpr != null) {
						CTColor ctColor = CTColor.Factory.newInstance();
						ctColor.setVal("43B4D8");
					}
				}
			}
		}

	}

	private static void addTableOfContents(XWPFDocument document, List<PolicyViewExportDTO> policyData,
			String ButtonType) throws IOException {

		if (ButtonType.equals("External")) {
			XWPFParagraph contentsHead = document.createParagraph();
			contentsHead.setAlignment(ParagraphAlignment.CENTER);
//            setFontStyles(contentsHead,"Arial",14,"Table of Contents");

			contentsHead.createRun().setText("Table of Contents");

			XWPFParagraph tocParagraph = document.createParagraph();

			CTSimpleField tocField = tocParagraph.getCTP().addNewFldSimple();
			tocField.setInstr("TOC \\o \"1-3\" \\h \\z \\u");
			document.enforceUpdateFields();
			// Add a page break
			document.createParagraph().setPageBreak(true);
		}

	}

	private static void processPolicyData(XWPFDocument document, List<PolicyViewExportDTO> policyData,
			String buttonType) {
		if (buttonType.equals("External")) {
			ExportExternalFormat(document, policyData);
		}
	}

	private void addHeadersAndFooters(XWPFDocument document) {
		// Create a new section
		CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();
		// Indicate that the first page should have a different header/footer
		sectPr.addNewTitlePg();

		// Create the default header and footer
		XWPFHeaderFooterPolicy policy = new XWPFHeaderFooterPolicy(document, sectPr);
		sectPr.addNewTitlePg();
		// Create default header and footer
		XWPFHeader defaultHeader = policy.createHeader(XWPFHeaderFooterPolicy.DEFAULT);
		XWPFFooter defaultFooter = policy.createFooter(XWPFHeaderFooterPolicy.DEFAULT);

		// Exclude header and footer for the first page
		if (!isFirstPage(document)) {
			// Set up header and footer content for subsequent pages
			XWPFParagraph headerParagraphDefault = defaultHeader.getParagraphArray(0);
			if (headerParagraphDefault == null) {
				headerParagraphDefault = defaultHeader.createParagraph();
			}

			XWPFRun runHeaderDefault1 = headerParagraphDefault.createRun();
			runHeaderDefault1.setText("Policy Review");
			headerParagraphDefault.setAlignment(ParagraphAlignment.CENTER);

			// Add underline after the default header
			XWPFParagraph underlineParagraph = defaultHeader.createParagraph();
			underlineParagraph.setBorderBottom(Borders.SINGLE);

			// Add content to the footer
			XWPFParagraph underlineFooterParagraph = defaultFooter.createParagraph();
//			XWPFRun runUnderlineFooter = underlineFooterParagraph.createRun();
			underlineFooterParagraph.setBorderBottom(Borders.SINGLE);

			XWPFParagraph footerParagraphDefault = defaultFooter.createParagraph();
			footerParagraphDefault.setAlignment(ParagraphAlignment.CENTER);
			XWPFRun runFooterDefaultCentered = footerParagraphDefault.createRun();

			Year thisYear = Year.now();
			runFooterDefaultCentered
					.setText("CPT copyright {" + thisYear + "} American Medical Association. All rights reserved.");
			XWPFRun newMessageRun = footerParagraphDefault.createRun();
			newMessageRun.addBreak(); // This creates a line break
			newMessageRun.setText("Confidential and Proprietary.");

			// Right-aligned text (page number)
			XWPFParagraph footerParagraphRight = defaultFooter.createParagraph();
			footerParagraphRight.setAlignment(ParagraphAlignment.RIGHT);

			XWPFRun runFooterRight = footerParagraphRight.createRun();
			runFooterRight.setText("Page ");
			CTSimpleField pageNumber = footerParagraphRight.getCTP().addNewFldSimple();
			pageNumber.setInstr("PAGE");
			
		

		    // Adding " of "
		    runFooterRight = footerParagraphRight.createRun();
		    runFooterRight.setText(" of ");

		    // Total number of pages
		    CTSimpleField totalPageNumber = footerParagraphRight.getCTP().addNewFldSimple();
		    totalPageNumber.setInstr("NUMPAGES");
		   
		}
	}

	private boolean isFirstPage(XWPFDocument document) {
		// Check if the document contains a section property indicating the first page
		CTSectPr sectPr = document.getDocument().getBody().getSectPr();
		return sectPr != null && sectPr.isSetTitlePg();
	}

	private static void addDataToDocument(XWPFDocument document, PolicyViewExportDTO policyData, String buttonType) {
		if (!isEqual(currentMedicalDesc, policyData.getMedicalDesc())
				|| !isEqual(currentSubDesc, policyData.getSubDesc())
				|| !isEqual(currentReasonCodeDesc, policyData.getReasonCodeDesc())) {

			// If any of the descriptions is different, display the table
			displayTableAndRemainingFields(document, policyData, buttonType);

			// Update the current descriptions
			currentMedicalDesc = policyData.getMedicalDesc();
			currentSubDesc = policyData.getSubDesc();
			currentReasonCodeDesc = policyData.getReasonCodeDesc();
		} else {
			// If all descriptions are the same, only add the remaining fields to the
			// document
			addRemainingFieldsToDocument(document, policyData, buttonType);
		}
	}

	private static boolean isEqual(String str1, String str2) {
		return (str1 == null && str2 == null) || (str1 != null && str1.equals(str2));
	}

	// Method to set cell background color
	private static void setCellBackgroundColor(XWPFTableCell cell, String hexColor) {
		CTTc ctTc = cell.getCTTc();
		CTShd ctShd = ctTc.isSetTcPr() ? ctTc.getTcPr().addNewShd() : ctTc.addNewTcPr().addNewShd();
		ctShd.setFill(hexColor);
	}

	private static void displayTableAndRemainingFields(XWPFDocument document, PolicyViewExportDTO policyData,
			String buttonType) {

		if (buttonType.equals("Internal")) {

			XWPFTable table = document.createTable();

			// Specify table width (in twentieths of a point)
			int tableWidth = 12000; // Example width; adjust as needed

			// Set the width for the entire table
			CTTblPr tblPr = table.getCTTbl().addNewTblPr();
			CTTblWidth tblWidth = tblPr.addNewTblW();
			tblWidth.setType(STTblWidth.DXA);
			tblWidth.setW(BigInteger.valueOf(tableWidth));

			// Creating first Row
			XWPFTableRow row1 = table.getRow(0);
			row1.getCell(0).setText("Medical Policy");
			row1.addNewTableCell().setText(policyData.getMedicalDesc());
			setCellBackgroundColor(row1.getCell(0), "004f71");

			// Creating second Row
			XWPFTableRow row2 = table.createRow();
			row2.getCell(0).setText("Sub Policy");
			row2.getCell(1).setText(policyData.getSubDesc());
			setCellBackgroundColor(row2.getCell(0), "004f71");

			// create third row
			XWPFTableRow row3 = table.createRow();
			row3.getCell(0).setText("Reason Code");
			row3.getCell(1).setText(policyData.getReasonCodeDesc());
			setCellBackgroundColor(row3.getCell(0), "004f71");

			// Add remaining fields to the document
			addRemainingFieldsToDocument(document, policyData, buttonType);
		}
	}

	private static void ExportExternalFormat(XWPFDocument document, List<PolicyViewExportDTO> policyData) {
		XWPFTable table = null;
		// Add text to the main content paragraph
//		Map<String, Integer> wordPageOccurrences = new HashMap<>();
		if (uniqueMedicalDesc.size() > 0 || uniqueMedicalSummary.size() > 0 || uniqueSubDesc.size() > 0
				|| uniqueSubSummary.size() > 0 || uniqueReference.size() > 0) {
			uniqueMedicalDesc.clear();
			uniqueMedicalSummary.clear();
			uniqueSubDesc.clear();
			uniqueSubSummary.clear();
			uniqueReference.clear();
		}
		int count = 0;
		int subCount = 0;
//		XWPFParagraph paragraph = document.createParagraph();

		for (PolicyViewExportDTO policyDataExport : policyData) {
			if (uniqueMedicalDesc.add(policyDataExport.getMedicalDesc())) {
				count++;
				subCount = 0;
				if (table != null) {
					document.createParagraph().createRun().addBreak(BreakType.PAGE);
				}

				table = document.createTable();
				configureTableProperties(table, TABLE_WIDTH);

				XWPFTableRow headerRow = table.getRow(0);
				int headingLevel = 1;
				configureHeaderRow(document, headerRow, headingLevel, policyDataExport.getMedicalDesc(), "S.No",
						policyDataExport.getMedicalDesc(), "Reference");

				XWPFTableRow medicalPolicyRow = table.createRow();
				configureDataRow(document, medicalPolicyRow, Integer.toString(count),
						policyDataExport.getMedicalPolicySummary(), policyDataExport.getRefSourceDesc());

				document.createParagraph().createRun().addBreak();

			}
			if (table != null && uniqueSubDesc.add(policyDataExport.getSubDesc())) {
				subCount++;

				XWPFTableRow subPolicyRow = table.createRow();
				int headingLevel = 2;
				configureHeaderRow(document, subPolicyRow, headingLevel, policyDataExport.getSubDesc(), "S.No",
						policyDataExport.getSubDesc(), "Reference");

				XWPFTableRow subPolicyRowData = table.createRow();
				String subCountvalue = count + "." + subCount;

				configureDataRow(document, subPolicyRowData, subCountvalue, policyDataExport.getSubPolicySummary(),
						policyDataExport.getRefSourceDesc());
			}
		}
	}

	private static void addCustomHeadingStyle(XWPFDocument docxDocument, String strStyleId, int headingLevel) {

		CTStyle ctStyle = CTStyle.Factory.newInstance();
		ctStyle.setStyleId(strStyleId);

		CTString styleName = CTString.Factory.newInstance();
		styleName.setVal(strStyleId);
		ctStyle.setName(styleName);

		CTDecimalNumber indentNumber = CTDecimalNumber.Factory.newInstance();
		indentNumber.setVal(BigInteger.valueOf(headingLevel));

		// lower number > style is more prominent in the formats bar
		ctStyle.setUiPriority(indentNumber);

		CTOnOff onoffnull = CTOnOff.Factory.newInstance();
		ctStyle.setUnhideWhenUsed(onoffnull);

		// style shows up in the formats bar
		ctStyle.setQFormat(onoffnull);

		// style defines a heading of the given level
		CTPPr ppr = CTPPr.Factory.newInstance();
		ppr.setOutlineLvl(indentNumber);
//		ctStyle.setPPr(ppr);

		XWPFStyle style = new XWPFStyle(ctStyle);

		// is a null op if already defined
		XWPFStyles styles = docxDocument.createStyles();

		style.setType(STStyleType.PARAGRAPH);
		styles.addStyle(style);

	}

	private static void configureTableProperties(XWPFTable table, int tableWidth) {
		CTTbl tbl = table.getCTTbl();
		CTTblPr tblPr = tbl.getTblPr();
		CTTblWidth tblWidth = tblPr.addNewTblW();
		tblWidth.setType(STTblWidth.DXA);
		tblWidth.setW(BigInteger.valueOf(tableWidth));
		tblPr.addNewTblLayout().setType(STTblLayoutType.FIXED);
	}

	private static void configureHeaderRow(XWPFDocument document, XWPFTableRow row, int headingLevel, String desc,
			String... headers) {

		for (int i = 0; i < headers.length; i++) {
			XWPFTableCell cell = row.getCell(i);
			if (cell == null) {
				cell = row.createCell();
			}
			cell.setWidthType(TableWidthType.DXA);
			cell.setWidth(Integer.toString(i == 0 ? FIXED_COLUMN_WIDTH
					: i == 1 ? FIXED_FIRST_COLUMN_WIDTH : i == 2 ? FIXED_SECOND_COLUMN_WIDTH : 0));
			cell.setText(headers[i]);

			for (XWPFParagraph paragraph : cell.getParagraphs()) {

				if (i == 1 && headers[i] == desc) {
					String headingStyle = headingLevel == 1 ? "Heading 1" : "Heading 2";

					paragraph.setStyle(headingStyle);
					if (headingLevel == 1) {
						addCustomHeadingStyle(document, "Heading 1", 1);
					}
					if (headingLevel == 2) {
						addCustomHeadingStyle(document, "Heading 2", 2);
					}
				}
				paragraph.setAlignment(ParagraphAlignment.CENTER);

				setCellBackgroundColor(cell, "004f71");
			}
		}
	}

	private static void configureDataRow(XWPFDocument document, XWPFTableRow row, String count, byte[] fileBytesData,
			String reference) {
		int fixedColumnWidth = 11200;
		int fixedSecondColumnWidth = 1800;
		row.getCell(0).setWidth("1000");
		row.getCell(0).setText(count);
		for (int i = 1; i < row.getTableCells().size(); i++) {
			XWPFTableCell cell = row.getCell(i);
			// Create a new paragraph for the cell content
			XWPFParagraph paragraph = cell.addParagraph();
			cell.removeParagraph(0);
			paragraph.setAlignment(ParagraphAlignment.LEFT);
			cell.setWidthType(TableWidthType.DXA);
			cell.setWidth(i == 1 ? Integer.toString(fixedColumnWidth) : Integer.toString(fixedSecondColumnWidth));
			if (i == 1) {
				if (fileBytesData != null) {
					try (ByteArrayInputStream bais = new ByteArrayInputStream(fileBytesData)) {
						XWPFDocument embeddedDoc = new XWPFDocument(bais);
						for (IBodyElement element : embeddedDoc.getBodyElements()) {
							if (element instanceof XWPFParagraph) {
								XWPFParagraph embeddedParagraph = (XWPFParagraph) element;
								XWPFParagraph newParagraph = paragraph.getBody()
										.insertNewParagraph(paragraph.getCTP().newCursor());
								newParagraph.getCTP().set(embeddedParagraph.getCTP().copy());
							} else if (element instanceof XWPFTable) {
								XWPFTable embeddedTable = (XWPFTable) element;
								XWPFTable newTable = paragraph.getBody().insertNewTbl(paragraph.getCTP().newCursor());
								newTable.getCTTbl().set(embeddedTable.getCTTbl().copy());
							}
						}

					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} else if (i == 2) {
				String referenceData = truncateAtSemicolon(reference);
				setFontStyles(paragraph, "Arial", 10, referenceData);
//                paragraph.createRun().setText(referenceData);
				paragraph.setAlignment(ParagraphAlignment.LEFT);

//                paragraph.createRun().setFontSize(5);
//                paragraph.createRun().setFontFamily("Arial");
			}
		}
	}

	public static void setFontStyles(XWPFParagraph paragraph, String fontFamily, int fontSize, String data) {
		XWPFRun run = paragraph.createRun();
		run.setFontFamily(fontFamily);
		run.setText(data);
		run.setFontSize(fontSize);
	}

	private static String truncateAtSemicolon(String reference) {
		int semicolonIndex = reference.indexOf(';');
		if (semicolonIndex != -1) {
			return reference.substring(0, semicolonIndex);
		}
		return reference;
	}

	private XWPFParagraph addParagraph(XWPFDocument document, String text, int fontSize, ParagraphAlignment alignment) {
		XWPFParagraph paragraph = document.createParagraph();
		paragraph.setAlignment(alignment);
		setFontSizeForParagraph(paragraph, fontSize);
		paragraph.createRun().setText(text);
		return paragraph;
	}

	private static int count = 0;

	private static void addRemainingFieldsToDocument(XWPFDocument document, PolicyViewExportDTO policyData,
			String buttonType) {

		if (buttonType.equals("Internal")) {
			XWPFParagraph contentParagraph = document.createParagraph();
			count++;
			// Add text to the main content paragraph

			addBoldTextToParagraph(contentParagraph, +count + ")" + " Policy Number: ");
			contentParagraph.createRun().setText(policyData.getPolicyNumber().toString());
			contentParagraph.createRun().addCarriageReturn();

			addBoldTextToParagraph(contentParagraph, "• Category: ");
			contentParagraph.createRun().setText(policyData.getCategoryDesc());
			contentParagraph.createRun().addCarriageReturn();

			addBoldTextToParagraph(contentParagraph, "• Reference: ");
			contentParagraph.createRun().setText(policyData.getReference());
			contentParagraph.createRun().addCarriageReturn();

			addBoldTextToParagraph(contentParagraph, "• Claim Type: ");
			contentParagraph.createRun().setText(getClaimTypeDesc(policyData.getClaimType(), ClaimDesc));
			contentParagraph.createRun().addCarriageReturn();

			addBoldTextToParagraph(contentParagraph, "• Bill Type: ");
			contentParagraph.createRun().setText(policyData.getTaxDesc());
			contentParagraph.createRun().addCarriageReturn();

			addBoldTextToParagraph(contentParagraph, "• NPI: ");
			contentParagraph.createRun().setText(policyData.getNpiDesc());
			contentParagraph.createRun().addCarriageReturn();

			addBoldTextToParagraph(contentParagraph, "• Deactivated: ");
			contentParagraph.createRun().setText(policyData.getDeactivated().toString());
			contentParagraph.createRun().addCarriageReturn();

			addBoldTextToParagraph(contentParagraph, "• Rendering Provider Taxonomy: ");
			contentParagraph.createRun().setText(policyData.getTaxonomyDesc());
			contentParagraph.createRun().addCarriageReturn();

			addBoldTextToParagraph(contentParagraph, "• Min Age Desc: ");
			contentParagraph.createRun().setText(policyData.getMinAgeDesc());
			contentParagraph.createRun().addCarriageReturn();

			addBoldTextToParagraph(contentParagraph, "• Max Age Desc: ");
			contentParagraph.createRun().setText(policyData.getMaxAgeDesc());
			contentParagraph.createRun().addCarriageReturn();

			addBoldTextToParagraph(contentParagraph, "• Priority : ");
			contentParagraph.createRun().setText(policyData.getPriority().toString());
			contentParagraph.createRun().addCarriageReturn();

			addBoldTextToParagraph(contentParagraph, "• Policy Desc: ");
			contentParagraph.createRun().setText(policyData.getPolicyDesc());
			contentParagraph.createRun().addCarriageReturn();

			addBoldTextToParagraph(contentParagraph, "• Notes: ");
			contentParagraph.createRun().setText(policyData.getNotes());
			contentParagraph.createRun().addCarriageReturn();

			setFontSizeForParagraph(contentParagraph, 9);
		}
	}

	private static void setFontSizeForParagraph(XWPFParagraph paragraph, int fontSize) {
		for (XWPFRun run : paragraph.getRuns()) {
			run.setFontSize(fontSize);
		}
	}

	private static void addBoldTextToParagraph(XWPFParagraph paragraph, String text) {
		XWPFRun run = paragraph.createRun();
		run.setText(text);
		run.setBold(true);
	}

	public static String getClaimTypeDesc(String claimType, List<ClaimTypeLookUp> ClaimDesc) {

		String[] claimTypes = claimType.split(",");

		StringBuilder result = new StringBuilder();

		for (String id : claimTypes) {
			for (ClaimTypeLookUp dto : ClaimDesc) {
				if (id.equals(dto.getClaimType())) {
					if (result.length() > 0) {
						result.append(",");
					}
					result.append(claimType).append("-").append(dto.getDescription());
					break;
				}
			}
		}

		return result.toString();
	}

}