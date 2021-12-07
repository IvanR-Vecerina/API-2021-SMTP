package MailBot.Model.Ressources;

import java.io.IOException;
import java.util.ArrayList;

public class MailRessourcePool {
    private ArrayList<String[]> messagePool;
    private ArrayList<String>   addressPool;

    public MailRessourcePool() throws IOException {
        this.messagePool = MailMessagesList.getMessages();
        this.addressPool = MailAddressList.getAddresses();
    }

    public String getRandomAddress(){
        int addressId = (int)(Math.random() * Integer.MAX_VALUE) % this.addressPool.size();

        String s = this.addressPool.get(addressId);
        this.addressPool.remove(addressId);

        return s;
    }

    public String[] getRandomMessage(){
        return this.messagePool.get((int)(Math.random() * Integer.MAX_VALUE) % this.messagePool.size());
    }

    public int getAddressCount(){
        return this.addressPool.size();
    }

    public int getMessagesCount(){
        return this.messagePool.size();
    }
}
