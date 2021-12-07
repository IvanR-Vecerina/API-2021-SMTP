package MailBot.Model.Ressources;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MailMessagesList {

    static public ArrayList<String[]> getMessages() throws IOException {
        return getMessages("ressource/Spam Messages");
    }

    static public ArrayList<String[]> getMessages(String path) throws IOException {
        File directory = new File(path);
        return messageFilesReader(directory);
    }

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
