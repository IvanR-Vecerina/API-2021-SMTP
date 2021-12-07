package MailBot;

import MailBot.Config.ConfigManager;
import MailBot.Model.Mail;
import MailBot.Model.MailGenerator;
import MailBot.SMTP.SMTPClient;

import java.io.IOException;
import java.util.ArrayList;

public class MailBot {
    public static void main(String[] args) throws IOException {
        // Get config
        ConfigManager conf = new ConfigManager();

        // Get email list from pool based on config
        ArrayList<Mail> mailList = MailGenerator.generate(conf.getNbGroups(), conf.getWitnessEmail());

        // Create client
        SMTPClient client = new SMTPClient(conf.getServerAddress(), conf.getServerPort());

        // Connect
        client.startConnection();

        // Open Session
        client.startSession();

        // Send Emails one after another
        for (Mail mail : mailList){
            client.sendMail(mail);
        }

        // Close session
        client.closeSession();

        //Close connection
        client.closeConnection();
    }
}
