package Tests;

import Model.Ressources.MailMessagesList;

import java.io.IOException;
import java.util.ArrayList;

public class MailMessageListTest {
    public static void main(String[] args) throws IOException {
        ArrayList<String[]> test = MailMessagesList.getMessages();

        for (String[] s : test){
            System.out.println(s[0] + s[1]);
            System.out.println("//////////////////////////////////////");
        }
    }
}
