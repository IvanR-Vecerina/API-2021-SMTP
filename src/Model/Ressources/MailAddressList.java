package Model.Ressources;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class MailAddressList {
    static public ArrayList<String> getAddresses() throws IOException {
        return getAddresses("ressource/victims.utf8");
    }

    static public ArrayList<String> getAddresses(String filePath) throws IOException {
        File file = new File(filePath);
        return addressesFileReader(file);
    }

    static private ArrayList<String> addressesFileReader(File file) throws IOException {
        if(!file.exists()) {
            throw new FileNotFoundException("No file such file as: " + file.getPath());
        }

        return new ArrayList<String>(Files.readAllLines(Path.of(file.getPath()), StandardCharsets.UTF_8));
    }
}
