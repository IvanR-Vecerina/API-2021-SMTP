package MailBot.Model;

import java.util.ArrayList;
import java.util.Arrays;

public class Mail {
    private ArrayList<String> to;
    private ArrayList<String> cc;
    private String from;
    private String subject;
    private String message;

    public Mail(String from, ArrayList<String> to, String[] cc, String subject, String message) {
        this.from    = from;
        this.to      = to;
        this.cc      = new ArrayList<>(Arrays.asList(cc));
        this.subject = subject.split(": ", 2)[1];
        this.message = message;
    }

    public String getFrom() {
        return from;
    }

    public ArrayList<String> getTo() {
        return to;
    }

    public ArrayList<String> getCc() {
        return cc;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }

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
