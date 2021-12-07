package MailBot.Model;

import MailBot.Model.Ressources.MailResourcesPool;

import java.io.IOException;
import java.util.ArrayList;

/**
 * MailGenerator
 * Abstract class used to generate a given number of mails by grouping target addresses.
 * @author Ivan Vecerina
 */
public class MailGenerator {
    /**
     * function generating list of email for groups picked from data pool
     * @param nbMails number of mails/groups to make
     * @param witness witnesses to be in ccc
     * @return list of mails to send
     * @throws IOException if anything wrong pops up
     */
    static public ArrayList<Mail> generate(int nbMails, String[] witness) throws IOException {
        // Create return object and initialise the pool.
        ArrayList<Mail>   mailList = new ArrayList<>();
        MailResourcesPool dataPool = new MailResourcesPool();

        // Temp variables
        String tempSender;
        ArrayList<String> tempRecipients;
        String[] tempMessage = new String[2];

        // Check if requested nb of groups is possible
        if (nbMails < dataPool.getAddressCount() % 4){
            throw new IOException("Exception: Number of victims is too low to create requested number of groups!");
        }

        // Check if any messages to be sent
        if (dataPool.getMessagesCount() < 1){
            throw new IOException("Exception: No messages available!");
        }

        // For nb of groups
        for (int i = nbMails; i > 0; i--) {
            // pick a random Sender
            tempSender = dataPool.getRandomAddress();

            // pick group size random Recipients
            tempRecipients = new ArrayList<String>();
            for (int j = 1; j < dataPool.getAddressCount()/i; ++j){
                tempRecipients.add(dataPool.getRandomAddress());
            }
            if (tempRecipients.size() > 0) {
                // pick a subject/message pair
                tempMessage = dataPool.getRandomMessage();

                // create and add mail to list
                mailList.add(new Mail(tempSender, tempRecipients, witness, tempMessage[0], tempMessage[1]));
            }
        }
        return mailList;
    }
}
