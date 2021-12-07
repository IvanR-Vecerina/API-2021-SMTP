package MailBot.Model;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Mail
 * Class containing all the data related to the email to send
 * @author Ivan Vecerina
 */
public class Mail {
    private ArrayList<String> to;
    private ArrayList<String> cc;
    private String from;
    private String subject;
    private String message;

    /**
     * Constructor
     * @param from Sender address
     * @param to   Targets addresses
     * @param cc   Witness addresses
     * @param subject Email subject
     * @param message Email content/message
     */
    public Mail(String from, ArrayList<String> to, String[] cc, String subject, String message) {
        this.from    = from;
        this.to      = to;
        this.cc      = new ArrayList<>(Arrays.asList(cc));
        this.subject = subject.split(": ", 2)[1];
        this.message = message;
    }

    /**
     * Sender address getter
     * @return Sender address
     */
    public String getFrom() {
        return from;
    }

    /**
     * Targets addresses getter
     * @return Targets addresses
     */
    public ArrayList<String> getTo() {
        return to;
    }

    /**
     * Witness addresses getter
     * @return Witness addresses
     */
    public ArrayList<String> getCc() {
        return cc;
    }

    /**
     * Email subject getter
     * @return Email subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Email content/message getter
     * @return Email content/message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Mail displayer used for testing only
     * @return String of the Email
     */
    @Override
    public String toString() {

        String tmpTo = "";
        String tmpCc = "";

        for (String s : this.to)
            tmpTo += s + ",";

        for (String s : this.cc)
            tmpCc += s + ",";

        return "Mail:\n" +
                "{\n" +
                "sender:     " + from    + "\n" +
                "recipients: " + tmpTo   + "\n" +
                "cc:         " + tmpCc   + "\n" +
                "subject:    " + subject + "\n" +
                "content:\n"   + message + "\n" +
                '}';
    }
}
