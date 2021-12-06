package Model.Mail;

import java.io.IOException;
import java.util.ArrayList;

public class Mails {
    public ArrayList<Mail> mailList;

    public Mails(int nbGroups) throws IOException {
        this.mailList = new ArrayList<Mail>();
        MailDataPool dataPool = new MailDataPool();
        ArrayList<String> group;

        if (nbGroups < dataPool.victimsPool.size() % 4){
            throw new IOException("Exception: Number of victims is too low to create requested number of groups!");
        }

        for (int i = nbGroups; i > 0; i--) {
            group = new ArrayList<String>();
            for (int j = 0; j < dataPool.victimsPool.size()/i; ++j){
                group.add(dataPool.getRandomVictim());
            }
            if (group.size() > 0)
                this.mailList.add(new Mail(group, dataPool.getRandomPrank()));
        }
    }
}
