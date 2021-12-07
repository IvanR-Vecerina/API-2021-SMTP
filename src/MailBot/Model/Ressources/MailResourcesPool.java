package MailBot.Model.Ressources;

import java.io.IOException;
import java.util.ArrayList;

/**
 * MailRessourcePool
 * Class storing the pool of target addresses and messages
 * @author Ivan Vecerina
 */
public class MailResourcesPool {
    private ArrayList<String[]> messagePool;
    private ArrayList<String>   addressPool;

    /**
     * Constructor gathering the data and making the pools
     * @throws IOException if anything goes wrong getting the data
     */
    public MailResourcesPool() throws IOException {
        this.messagePool = MailMessagesList.getMessages();
        this.addressPool = MailAddressList.getAddresses();
    }

    /**
     * Random target address getter and removes that address from pool
     * @return random target address
     */
    public String getRandomAddress(){
        int addressId = (int)(Math.random() * Integer.MAX_VALUE) % this.addressPool.size();

        String s = this.addressPool.get(addressId);
        this.addressPool.remove(addressId);

        return s;
    }

    /**
     * Random message getter
     * @return random subject with message
     */
    public String[] getRandomMessage(){
        return this.messagePool.get((int)(Math.random() * Integer.MAX_VALUE) % this.messagePool.size());
    }

    /**
     * Getter of size of address pool
     * @return address pool size
     */
    public int getAddressCount(){
        return this.addressPool.size();
    }

    /**
     * Getter of size of message pool
     * @return message pool size
     */
    public int getMessagesCount(){
        return this.messagePool.size();
    }
}
