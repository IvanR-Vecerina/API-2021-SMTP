package Model.Mail;

import Model.Prank.Prank;
import Model.Prank.Pranks;
import Model.Victim.Victims;

import java.io.IOException;
import java.util.ArrayList;

public class MailDataPool {
    public ArrayList<Prank>  pranksPool;
    public ArrayList<String> victimsPool;

    public MailDataPool() throws IOException {
        this.pranksPool  = new Pranks().getPrankList();
        this.victimsPool = new Victims().getVictims();
    }

    public String getRandomVictim(){
        int victimId = (int)(Math.random() * Integer.MAX_VALUE) % this.victimsPool.size();

        String s = this.victimsPool.get(victimId);
        this.victimsPool.remove(victimId);

        return s;
    }

    public Prank getRandomPrank(){
        int prankId = (int)(Math.random() * Integer.MAX_VALUE) % this.pranksPool.size();

        return this.pranksPool.get(prankId);
    }
}
