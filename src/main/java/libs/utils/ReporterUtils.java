package libs.utils;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import libs.BaseTest;

public class ReporterUtils {
//    private static final String TD_TAG_END = "</td>";
//    private static final String TR_TAG_END = "</tr>";
//    private static final String NEW_LINE_SEPARATOR = System.lineSeparator();
//    private static final String[] TD_COLOR_ARR = { "#ebf2e6", "#f2f2f2" };
//    private static final String WARNING_TABLE_COLOR = "#FFFF00; color:black";
//    private static final String ERROR_TABLE_COLOR = "#FF0000; color:black";

    private ReporterUtils() {}

    public static void writeReqAndResLogsToReport(Status status, String reqLogs, String resLogs) {
        ExtentTest extentTest = BaseTest.getExtentTest(Thread.currentThread().getId());

        ExtentTest reqAndResLogsNode = extentTest.createNode("Request & Response Logs");
        reqAndResLogsNode.log(status, MarkupHelper.createCodeBlock(reqLogs, resLogs));
    }

    public static void writeStatusToReportWithMsg(Status status, String messageToWrite) {
        ExtentTest extentTest = BaseTest.getExtentTest(Thread.currentThread().getId());
        ExtentColor ex = ExtentColor.BLACK;
        if (status == Status.PASS)
            ex = ExtentColor.GREEN;
        else if (status == Status.FAIL)
            ex = ExtentColor.RED;
        else if (status == Status.SKIP)
            ex = ExtentColor.AMBER;
        else if (status == Status.INFO)
            ex = ExtentColor.BROWN;
        extentTest.log(status, MarkupHelper.createLabel("<p align=\"left\">" + messageToWrite + "</p>", ex));
    }

    public static void writeMessageToReportWithImage(Status status, String messageToWrite, String imagePath) {
        ExtentTest extentTest = BaseTest.getExtentTest(Thread.currentThread().getId());
        ExtentColor ex = ExtentColor.BLACK;
        if (status == Status.PASS)
            ex = ExtentColor.GREEN;
        else if (status == Status.FAIL)
            ex = ExtentColor.RED;
        else if (status == Status.SKIP)
            ex = ExtentColor.AMBER;
        else if (status == Status.INFO)
            ex = ExtentColor.BROWN;
        else if (status == Status.WARNING)
            ex = ExtentColor.ORANGE;
        extentTest.pass(MarkupHelper.createLabel("<p align=\"left\">" + messageToWrite + "</p>", ex).getMarkup(),
                MediaEntityBuilder.createScreenCaptureFromPath(imagePath).build());
    }

//    public static void writeHTableToReport(String tableHeader, ExtentReportTableList dataToWriteList) {
//        writeHTableToReport(tableHeader, dataToWriteList, Status.PASS.toString());
//    }

//    public static void writeHTableToReport(String tableHeader, ExtentReportTableList dataToWriteList, String level) {
//        String tableHeaderColor;
//        switch (level.toLowerCase()) {
//            case "warning":
//                tableHeaderColor = WARNING_TABLE_COLOR;
//                break;
//            case "error":
//                tableHeaderColor = ERROR_TABLE_COLOR;
//                break;
//            default:
//                tableHeaderColor = "#4CAF50";
//        }
//
//        String[][] dataToWrite = StringUtils.convertStringListToArray(dataToWriteList);
//
//        ExtentTest testInfo = BaseTest.getTestInfo(Thread.currentThread().getId());
//        String newLineSeparator = System.lineSeparator();
//
//        StringBuilder strBuilder = new StringBuilder();
//        strBuilder.append("<div style=\"overflow:auto;height:200px;\">");
//        strBuilder.append(newLineSeparator);
//        strBuilder.append(
//                "<table style=\"font-family:'Trebuchet MS', Arial, Helvetica, sans-serif;border-collapse: collapse;"
//                        + "width: 100%;\">");
//        strBuilder.append(newLineSeparator);
//
//        // adding header to table
//        strBuilder.append("<tr style=\"background-color:  " + tableHeaderColor + ";\">" + newLineSeparator);
//        strBuilder.append("<td colspan=\"" + dataToWrite[0].length
//                + "\" style=\"font-weight:bold;border: 1px solid #ddd;padding: 8px;padding-top: 12px;padding-bottom: "
//                + "12px;text-align: left;color: black;\">");
//        strBuilder.append(tableHeader.toUpperCase());
//        strBuilder.append(TD_TAG_END).append(newLineSeparator).append(TR_TAG_END).append(newLineSeparator);
//        // end of adding header
//
//        for (int counterRow = 0; counterRow < dataToWrite.length; counterRow++) {
//            if (counterRow == 0) {
//                strBuilder.append("<tr style=\"background-color: #ebf2e6;\">");
//                strBuilder.append(newLineSeparator);
//                for (int counterColumn = 0; counterColumn < dataToWrite[counterRow].length; counterColumn++) {
//                    if (counterColumn != dataToWrite[counterRow].length - 1) {
//                        strBuilder
//                                .append("<td style=\"font-weight:bold;border: 1px solid #ddd;padding: 8px;color:black;"
//                                        + "width:1%; white-space:nowrap;\">");
//                    } else {
//                        strBuilder.append("<td style=\"font-weight:bold;border: 1px solid #ddd;padding: 8px;color:black;"
//                                + "width:1%; white-space:nowrap;\">");
//                    }
//                    strBuilder.append(dataToWrite[counterRow][counterColumn]);
//                    strBuilder.append(TD_TAG_END);
//                    strBuilder.append(newLineSeparator);
//                }
//            } else {
//                strBuilder.append("<tr style=\"background-color: #ebf2e6;\">");
//                for (int counterColumn = 0; counterColumn < dataToWrite[counterRow].length; counterColumn++) {
//                    if (counterColumn != dataToWrite[counterRow].length - 1) {
//                        strBuilder.append("<td style=\"border: 1px solid #ddd;\r\n"
//                                + "    padding: 8px;color:black; width:1%; white-space:nowrap;\">");
//                    } else {
//                        strBuilder
//                                .append("<td style=\"border: 1px solid #ddd;\r\n" + "    padding: 8px;color:black;\">");
//                    }
//                    strBuilder.append(dataToWrite[counterRow][counterColumn]);
//                    strBuilder.append(TD_TAG_END);
//                    strBuilder.append(newLineSeparator);
//                }
//            }
//            strBuilder.append(TR_TAG_END);
//            strBuilder.append(newLineSeparator);
//        }
//        strBuilder.append("</table>");
//        strBuilder.append("</div>");
//        if ("warning".equals(level.toLowerCase()))
//            testInfo.warning(strBuilder.toString());
//        else
//            testInfo.pass(strBuilder.toString());
//    }
//
//    public static void writeMessageInTableFormat(String tableHeader, ExtentReportTableList dataToWriteTable) {
//        writeMessageInTableFormat(tableHeader, dataToWriteTable, Status.PASS.toString());
//    }
//    public static void writeMessageInTableFormat(String tableHeader, ExtentReportTableList dataToWriteTable, String level) {
//        String bgColor;
//        String borderColor;
//        String textColor;
//        switch (level.toLowerCase()) {
//            case   "warning"  : bgColor = "#ffc107"; textColor = "black"; borderColor = "#C7AB58"; break;
//            case   "error"       : bgColor = "#FF0000"; textColor = "black"; borderColor = "#FAAAAA"; break;
//            case   "skip"    : bgColor = "#FFFF00"; textColor = "black"; borderColor = "#FAAAAA"; break;
//            case   "info"        : bgColor = "#795548"; textColor = "white"; borderColor = "#AB887C"; break;
//            default             : bgColor = "#4CAF50"; textColor = "white"; borderColor = "#95D597";
//        }
//
//        String[][] dataToWrite = StringUtils.convertStringListToArray(dataToWriteTable);
//
//        ExtentTest testInfo = BaseTest.getTestInfo(Thread.currentThread().getId());
//        String newLineSeparator = System.lineSeparator();
//
//        StringBuilder strBuilder = new StringBuilder();
//        strBuilder.append("<span class=\"badge\" style=\"background-color: ").append(bgColor).append("; color: ").append(textColor).append(";\">");
//        strBuilder.append("<p2 style=\"font-size: 15px; line-height: 2.8; margin-bottom: 10px;").append("\">").append(tableHeader).append("</p2>");
//        strBuilder.append("<table style=\"font-family:'Trebuchet MS', Arial, Helvetica, sans-serif;\">");
//        strBuilder.append("<tbody>");
//        for (int i = 0; i < dataToWrite.length; i++) {
//            strBuilder.append("<tr>").append(newLineSeparator);
//            for (String data : dataToWrite[i]) {
//                strBuilder.append("<td style=\"font-size: 13px; border: 1px solid ").append(borderColor).append("; ");
//                if (i == 0)
//                    strBuilder.append("font-weight:bold; ");
//                strBuilder.append("padding: 8px; width:1%; white-space:nowrap;\">");
//                strBuilder.append(data).append(TD_TAG_END).append(newLineSeparator);
//            }
//            strBuilder.append(TR_TAG_END).append(newLineSeparator);
//        }
//        strBuilder.append("</tbody>");
//        strBuilder.append("</table>");
//        strBuilder.append("</span>");
//        testInfo.pass(strBuilder.toString());
//    }
//
//    public static void writeVTableToReport(String tableHeader, ExtentReportTableList dataToWriteList) {
//        writeVTableToReport(tableHeader, dataToWriteList, Status.PASS.toString());
//    }
//
//    /**
//     * THis method will be used for writing V table to ExtentReport with Warning or
//     * Error type
//     *
//     * @param tableHeader
//     * @param dataToWriteList
//     * @param level           - Level will be passed from
//     *                        MyEnumContainer.ExtentReportTableType
//     */
//    public static void writeVTableToReport(String tableHeader, ExtentReportTableList dataToWriteList, String level) {
//        String tableHeaderColor;
//        switch (level.toLowerCase()) {
//            case "warning":
//                tableHeaderColor = WARNING_TABLE_COLOR;
//                break;
//            case "error":
//                tableHeaderColor = ERROR_TABLE_COLOR;
//                break;
//            default:
//                tableHeaderColor = "#4CAF50";
//        }
//
//        String[][] dataToWrite = StringUtils.convertStringListToArray(dataToWriteList);
//
//        ExtentTest testInfo = BaseTest.getTestInfo(Thread.currentThread().getId());
//
//        StringBuilder strBuilder = new StringBuilder();
//        strBuilder.append("<div style=\"overflow:auto;height:200px;\">");
//        strBuilder.append(NEW_LINE_SEPARATOR);
//        strBuilder.append(
//                "<table style=\"font-family:'Trebuchet MS', Arial, Helvetica, sans-serif;border-collapse: collapse;"
//                        + "width: 100%;\">");
//        strBuilder.append(NEW_LINE_SEPARATOR);
//
//        // adding header to table
//        strBuilder.append("<tr style=\"background-color: " + tableHeaderColor + ";\">").append(NEW_LINE_SEPARATOR);
//        strBuilder.append("<td colspan=\"").append(dataToWrite[0].length).append(
//                        "\" style=\"font-weight:bold;border: 1px solid #ddd;padding: 8px;padding-top: 12px;padding-bottom: ")
//                .append("12px;text-align: left;\">");
//        strBuilder.append(tableHeader.toUpperCase());
//        strBuilder.append(TD_TAG_END).append(NEW_LINE_SEPARATOR).append(TR_TAG_END).append(NEW_LINE_SEPARATOR);
//        // end of adding header
//
//        for (int counterRow = 0; counterRow < dataToWrite.length; counterRow++) {
//            strBuilder.append("<tr style=\"background-color: #ebf2e6;\">");
//            strBuilder.append(NEW_LINE_SEPARATOR);
//            for (int counterColumn = 0; counterColumn < dataToWrite[counterRow].length; counterColumn++) {
//                String boldFontWeight = (counterColumn == 0) ? "font-weight:bold;" : "";
//                String widthCSS = counterColumn == 0 ? "width:1%; white-space:nowrap;" : "";
//                strBuilder.append("<td style=\"").append(boldFontWeight).append(widthCSS)
//                        .append("border: 1px solid #ddd;padding: 8px;color:black;").append(";\">");
//                strBuilder.append(dataToWrite[counterRow][counterColumn]);
//                strBuilder.append(TD_TAG_END);
//                strBuilder.append(NEW_LINE_SEPARATOR);
//            }
//
//            strBuilder.append(TR_TAG_END);
//            strBuilder.append(NEW_LINE_SEPARATOR);
//        }
//        strBuilder.append("</table>");
//        strBuilder.append("</div>");
//        testInfo.pass(strBuilder.toString());
//    }

//    public static void writeAssertionMsgToReport(String message) {
//        Reporter.log("Assertion @ " + message);
//    }
//
//    public static void writeWarningMsgToReport(String message) {
//        Reporter.log("Warning @ " + message);
//    }

//    public static void writeInfoLogToReport(Long threadID, Map<?, ?> map) {
//        writeInfoLogToReport(threadID, new JSONObject(map));
//    }
//
//    public static void writeInfoLogToReport(Long threadID, JSONObject jsonObject) {
//        BaseTest.getTestInfo(threadID)
//                .info(MarkupHelper.createCodeBlock(String.valueOf(jsonObject), CodeLanguage.JSON));
//    }
}

