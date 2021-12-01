package Model.Prank;

import java.io.IOException;
import java.util.ArrayList;

public class PranksTest {
    public static void main(String[] args) throws IOException {
        Pranks test = new Pranks();
        ArrayList<String> tmp = test.getPrankList();

        for (String s : tmp){
            System.out.println(s);
            System.out.println("//////////////////////////////////////");
        }
    }
}
