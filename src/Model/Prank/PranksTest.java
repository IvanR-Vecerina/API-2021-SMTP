package Model.Prank;

import java.io.IOException;
import java.util.ArrayList;

public class PranksTest {
    public static void main(String[] args) throws IOException {
        ArrayList<Prank> test = new Pranks().getPrankList();

        for (Prank s : test){
            System.out.println(s);
            System.out.println("//////////////////////////////////////");
        }
    }
}
