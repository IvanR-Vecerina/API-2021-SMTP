package Model;

import Model.Ressources.MailRessourcePool;

import java.io.IOException;
import java.util.ArrayList;

public class MailGenerator {
    static public ArrayList<Mail> generate(int nbMails, String[] witness) throws IOException {
        ArrayList<Mail>   mailList = new ArrayList<>();
        MailRessourcePool dataPool = new MailRessourcePool();

        String tempSender;
        ArrayList<String> tempRecipients;
        String[] tempMessage = new String[2];

        if (nbMails < dataPool.getAddressCount() % 4){
            throw new IOException("Exception: Number of victims is too low to create requested number of groups!");
        }

        if (dataPool.getMessagesCount() < 1){
            throw new IOException("Exception: No messages available!");
        }

        for (int i = nbMails; i > 0; i--) {
            tempSender = dataPool.getRandomAddress();
            tempRecipients = new ArrayList<String>();
            for (int j = 1; j < dataPool.getAddressCount()/i; ++j){
                tempRecipients.add(dataPool.getRandomAddress());
            }
            if (tempRecipients.size() > 0)
                tempMessage = dataPool.getRandomMessage();
                mailList.add(new Mail(tempSender, tempRecipients, witness, tempMessage[0], tempMessage[1]));
        }
        return mailList;
    }
}
