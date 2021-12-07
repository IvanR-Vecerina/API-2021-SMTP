package MailBot.Model.Ressources;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * MailAddressList
 * This abstract class extracts all the listed target addresses from the file, "ressource/victims.utf8" by default.
 * @author Ivan Vecerina
 */
public class MailAddressList {

    /**
     * File data extractor specific path
     * @return ArrayList of String containing all the addresses of our targets
     * @throws IOException if default file doesn't exist
     */
    static public ArrayList<String> getAddresses() throws IOException {
        return getAddresses("ressource/victims.utf8");
    }

    /**
     * File data extractor specific path
     * @param filePath specified file path
     * @return ArrayList of String containing all the addresses of our targets
     * @throws IOException if file doesn't exist
     */
    static public ArrayList<String> getAddresses(String filePath) throws IOException {
        File file = new File(filePath);
        return addressesFileReader(file);
    }

    /**
     * File data extractor
     * @param file to extract list from
     * @return String containing all the addresses of our targets
     * @throws IOException if file doesn't exist
     */
    static private ArrayList<String> addressesFileReader(File file) throws IOException {
        if(!file.exists()) {
            throw new FileNotFoundException("No file such file as: " + file.getPath());
        }

        return new ArrayList<String>(Files.readAllLines(Path.of(file.getPath()), StandardCharsets.UTF_8));
    }
}
