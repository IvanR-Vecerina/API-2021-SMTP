package MailBot.Model.Ressources;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * MailMessageList
 * This abstract class extracts all the messages from the directory, "ressource/Spam Messages" by default.
 * @author Ivan Vecerina
 */
public class MailMessagesList {

    /**
     * Directory files data extractor from default path
     * @return Arraylist of String array containing subject and message body.
     * @throws IOException if issue with file or directory
     */
    static public ArrayList<String[]> getMessages() throws IOException {
        return getMessages("ressource/Spam Messages");
    }

    /**
     * Directory files data extractor from specified path
     * @param path specified directory to explore
     * @return Arraylist of String array containing subject and message body.
     * @throws IOException if issue with file or directory
     */
    static public ArrayList<String[]> getMessages(String path) throws IOException {
        File directory = new File(path);
        return messageFilesReader(directory);
    }

    /**
     * Directory files data extractor
     * @param directory to explore and extract messages from (1 file = 1 message)
     * @return Arraylist of String array containing subject and message body.
     * @throws IOException if issue with file or directory
     */
    static private ArrayList<String[]> messageFilesReader(File directory) throws IOException {
        ArrayList<String[]> messageList = new ArrayList<>();

        if(directory.exists()){
            File[] files = directory.listFiles();

            if (files != null) {
                for (File file : files) {
                    if (file.isFile()){
                        String[] temp = Files.readString(Path.of(file.getPath()), StandardCharsets.UTF_8).split("\n", 2);
                        messageList.add(temp);
                    }
                }
            } else {
                throw new FileNotFoundException("No files in 'ressource/Spam Messages' directory!");
            }
        } else {
            throw new FileNotFoundException("Directory 'ressource/Spam Messages' doesn't exist!");
        }

        return messageList;
    }
}
