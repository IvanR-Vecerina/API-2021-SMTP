package Tests;

import Model.Mail;
import Model.MailGenerator;

import java.io.IOException;
import java.util.ArrayList;

public class MailTest {
    public static void main(String[] args) throws IOException {
        ArrayList<Mail> m = MailGenerator.generate(4, new String[]{"test", "test2"});

        for (Mail mail : m) {
            System.out.println(mail);
        }
    }
}
