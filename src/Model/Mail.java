package Model;

import java.util.ArrayList;

public class Mail {
    private ArrayList<String> to;
    private String from;
    private String cc;
    private String subject;
    private String message;

    public Mail(String from, ArrayList<String> to, String cc, String subject, String message) {
        this.from    = from;
        this.to      = to;
        this.cc      = cc;
        this.subject = subject;
        this.message = message;
    }

    public String getFrom() {
        return from;
    }

    public ArrayList<String> getTo() {
        return to;
    }

    public String getCc() {
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

        String tmp = "";

        for (String s : this.to)
            tmp+= s + ",";

        return "Mail:\n" +
                "{\n" +
                "sender:     " + from + "\n" +
                "recipients: " + tmp + "\n" +
                "cc:         " + cc + "\n" +
                "subject:    " + subject + "\n" +
                "content:\n"   + message + "\n" +
                '}';
    }
}
