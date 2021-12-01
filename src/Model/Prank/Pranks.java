package Model.Prank;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


public class Pranks {
    private ArrayList<String> prankList;

    public Pranks() throws IOException {
        File prankDir = new File("ressource/Spam Messages");
        this.prankList = new ArrayList<String>();

        if(prankDir.exists()){
            File[] files = prankDir.listFiles();

            if (files != null) {
                for (File file : files) {
                    if (file.isFile()){
                        Prank temp = new Prank(file);
                        this.prankList.add(temp.getPrank());
                    }
                }
            } else {
                throw new FileNotFoundException("No files in 'ressource/Spam Messages' directory!");
            }
        } else {
            throw new FileNotFoundException("Directory 'ressource/Spam Messages' doesn't exist!");
        }

    }

    public ArrayList<String> getPrankList() {
        return prankList;
    }
}
