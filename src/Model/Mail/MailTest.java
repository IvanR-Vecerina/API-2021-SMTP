package Model.Mail;

import java.io.IOException;

public class MailTest {
    public static void main(String[] args) throws IOException {
        Mails m = new Mails(4);

        for (int i = 0; i < m.mailList.size(); ++i){
            System.out.println(m.mailList.get(i));
        }
    }
}
