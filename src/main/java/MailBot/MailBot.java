package MailBot;

import MailBot.Config.ConfigManager;
import MailBot.Model.Mail;
import MailBot.Model.MailGenerator;
import MailBot.SMTP.SMTPClient;

import java.io.IOException;
import java.util.ArrayList;

public class MailBot {
    public static void main(String[] args) throws IOException {
        ConfigManager conf = new ConfigManager();
        ArrayList<Mail> mailList = MailGenerator.generate(conf.getNbGroups(), conf.getWitnessEmail());

        SMTPClient client = new SMTPClient(conf.getServerAddress(), conf.getServerPort());
        client.startConnection();
        client.startSession();
        for (Mail mail : mailList){
            client.sendMail(mail);
        }
        client.closeSession();
        client.closeConnection();
    }
}
