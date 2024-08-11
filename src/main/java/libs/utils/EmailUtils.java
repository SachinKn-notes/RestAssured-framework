package libs.utils;

import jakarta.mail.*;
import jakarta.mail.internet.MimeMultipart;
import jakarta.mail.search.*;
import libs.BaseTest;
import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;
import java.util.function.Predicate;

public class EmailUtils extends BaseTest
{
    private static Folder folder;
    private static Session session;
    private static Store store;

    public static void connectToStoreAndOpenFolder(String folderName, String emailUserName, String emailPassWord) {
        Properties props = System.getProperties();
        props.setProperty("mail.store.protocol", "imap");
        props.put("mail.imap.ssl.enable", "true");
        props.put("mail.mime.base64.ignoreerrors", "true");
        props.put("mail.imap.timeout", "900000");

        session = Session.getDefaultInstance(props, null);
        try {
            store = session.getStore("imap");
            store.connect("outlook.office365.com", emailUserName, emailPassWord);

            folder = store.getFolder(folderName);
            if (!folder.isOpen())
                folder.open(Folder.READ_WRITE);
        } catch (MessagingException e) {
            System.out.println("Error while connecting to Email Store " + e.getMessage());
        }
    }

    /**
     * This method will create Session connect to store and Open Inbox folder
     *
     * @param emailUserName
     * @param emailPassWord
     * @throws MessagingException
     */
    public static void connectToStoreAndOpenFolder(String emailUserName, String emailPassWord) {
        connectToStoreAndOpenFolder("INBOX", emailUserName, emailPassWord);
    }

    public static void closeFolderAndDisconnect() {
        try {
            if (folder != null && folder.isOpen())
                folder.close();
            if (store != null && store.isConnected())
                store.close();
        } catch (MessagingException e) {
//            LoggerUtils.logError("Error while closing folder and session " + e.getMessage());
            System.out.println("Error while closing folder and session " + e.getMessage());
        }

    }

    /**
     * The main goal of this method is to login into given username and password and
     * it checks for the new arrived mail and search for that mail also based on
     * agency confirmation number.
     *
     * @param searchSubject - email subject that needs to be searched
     * @throws Exception - Exception
     */
    public static Message getEmailBySubject(String searchSubject) throws Exception {
        if (folder == null) {
            throw new Exception("Folder is not opened. Please Check LoggerUtils log regarding opening connection");
        }
        if (!folder.isOpen()) {
            folder.open(Folder.READ_WRITE);
        }
        Message[] messages;
        for (int i = 0; i < 36; i++) {
            Thread.sleep(5000);
            SearchTerm searchTerm = new AndTerm(
                    new SearchTerm[] { new SubjectTerm(searchSubject.replaceAll("[^\\p{ASCII}]", "")),
                            new ReceivedDateTerm(ComparisonTerm.EQ, new Date()) });
            messages = folder.search(searchTerm, folder.getMessages());
            ArrayUtils.reverse(messages);
            Arrays.sort(messages, (m1, m2) -> {
                try {
                    return m2.getReceivedDate().compareTo(m1.getReceivedDate());
                } catch (MessagingException e) {
//                    LoggerUtils.logError("Error thrown while sorting emails based on received date", e);
                    System.out.println("Error thrown while sorting emails based on received date: " + e);
                }
                return 0;
            });
            if (messages.length >= 1) {
                return messages[0];
            }
        }
        throw new Exception("Waited for 3 minutes for email having Subject - " + searchSubject);
    }

    /**
     * The main goal of this method is to login into given username and password and
     * it checks for the new arrived mail and search for that mail also based on
     * agency confirmation number.
     *
     * @param searchSubject - email subject that needs to be searched
     * @throws Exception - Exception
     */
    public static Message getEmailBySubject(String searchSubject, String bodySearchTerm) throws Exception {
        if (folder == null) {
            throw new Exception("Folder is not opened. Please Check LoggerUtils log regarding opening connection");
        }
        if (!folder.isOpen()) {
            folder.open(Folder.READ_WRITE);
        }
        Message[] messages;
        for (int i = 0; i < 36; i++) {
            Thread.sleep(5000);
//            LoggerUtils.logInfo("Tried for getting email with search term " + bodySearchTerm);
            System.out.println("Tried for getting email with search term " + bodySearchTerm);
            SearchTerm searchTerm = new AndTerm(
                    new SearchTerm[] { new SubjectTerm(searchSubject.replaceAll("[^\\p{ASCII}]", "")),
                            new ReceivedDateTerm(ComparisonTerm.EQ, new Date()) });
            messages = folder.search(searchTerm, folder.getMessages());
            ArrayUtils.reverse(messages);
            Predicate<Message> messagePredicate = m -> {
                try {
                    return getTextFromMessage(m, true).contains(bodySearchTerm);
                } catch (MessagingException | IOException e) {
//                    LoggerUtils.logError("Error thrown while filtering message based on bodySearch term", e);
                    System.out.println("Error thrown while filtering message based on bodySearch term: " + e);
                }
                return false;
            };
            boolean messageFilterResult = Arrays.stream(messages).anyMatch(messagePredicate);
            if (messageFilterResult) {
                return Arrays.stream(messages).filter(messagePredicate).findFirst().get();
            }
        }
        throw new Exception("Waited for 3 minutes for email having Subject - " + searchSubject + " and bodySearch - "
                + bodySearchTerm);
    }

    public static String getTextFromMessage(Message message) throws MessagingException, IOException {
        return getTextFromMessage(message, false);
    }

    public static String getTextFromMessage(Message message, boolean isHTMLReq) throws MessagingException, IOException {
        if (message.isMimeType("text/plain")) {
            return message.getContent().toString();
        } else if (message.isMimeType("text/html")) {
            return message.getContent().toString();
        } else if (message.isMimeType("multipart/*")) {
            MimeMultipart multipart = (MimeMultipart) message.getContent();
            return getTextFromMimeMultipart(multipart, isHTMLReq);
        }
        return "";
    }

    private static String getTextFromMimeMultipart(MimeMultipart mimeMultipart) throws MessagingException, IOException {
        return getTextFromMimeMultipart(mimeMultipart, false);
    }

    private static String getTextFromMimeMultipart(MimeMultipart mimeMultipart, boolean isHTMLReq)
            throws MessagingException, IOException {
        StringBuilder result = new StringBuilder();
        int count = mimeMultipart.getCount();
        for (int i = 0; i < count; i++) {
            BodyPart bodyPart = mimeMultipart.getBodyPart(i);
            if (bodyPart.isMimeType("text/plain")) {
                result.append("\n").append(bodyPart.getContent());
            } else if (bodyPart.isMimeType("text/html")) {
                String html = (String) bodyPart.getContent();
//                result.append("\n").append(isHTMLReq ? html : org.jsoup.Jsoup.parse(html).text());
            } else if (bodyPart.getContent() instanceof MimeMultipart) {
                result.append(getTextFromMimeMultipart((MimeMultipart) bodyPart.getContent()));
            }
        }
        return result.toString();
    }

    public static void deleteMessage(Message message) throws Exception {
        if (folder == null) {
            throw new Exception("Folder is not opened. Please Check LoggerUtils log regarding opening connection");
        }
        message.setFlag(Flags.Flag.DELETED, true);
    }

}
