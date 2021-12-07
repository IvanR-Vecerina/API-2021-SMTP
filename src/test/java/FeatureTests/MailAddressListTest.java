package FeatureTests;

import MailBot.Model.Ressources.MailAddressList;

import java.io.IOException;
import java.util.ArrayList;

public class MailAddressListTest {
    public static void main(String[] args) throws IOException {
        ArrayList<String> test = MailAddressList.getAddresses();

        for (String s : test){
            System.out.println(s);
        }
    }

}
