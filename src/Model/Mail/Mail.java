package Model.Mail;

import Model.Prank.Prank;

import java.util.ArrayList;

public class Mail {
    public String sender;
    public ArrayList<String> recipients;
    public String subject;
    public String content;

    public Mail(ArrayList<String> group, Prank prank) {
        this.subject = prank.getSubject();
        this.content = prank.getBody();

        int senderId = (int)(Math.random() * Integer.MAX_VALUE) % group.size();

        this.sender = group.get(senderId);
        group.remove(senderId);

        this.recipients = group;
    }

    @Override
    public String toString() {

        String tmp = "";

        for (String s : this.recipients)
            tmp+= s + ",";

        return "Mail:\n" +
                "{\n" +
                "sender:     " + sender + "\n" +
                "recipients: " + tmp + "\n" +
                "subject:    " + subject + "\n" +
                "content:\n" + content + "\n" +
                '}';
    }
}
