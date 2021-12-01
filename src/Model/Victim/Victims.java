package Model.Victim;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Victims {
    private ArrayList<String> victims;

    public Victims() throws IOException {
        this.victims = new ArrayList<String>();
        this.victims.addAll(Files.readAllLines(Paths.get("ressource/victims.utf8"), StandardCharsets.UTF_8));
    }

    public ArrayList<String> getVictims() {
        return victims;
    }
}
