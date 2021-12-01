package Model.Victim;

import java.io.IOException;
import java.util.ArrayList;

public class VictimsTest {
    public static void main(String[] args) throws IOException {
        ArrayList<String> test = new Victims().getVictims();

        for (String s : test){
            System.out.println(s);
        }
    }
}
