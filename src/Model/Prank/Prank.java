package Model.Prank;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class Prank {
    private final String subject;
    private final String body;

    public Prank(File file) throws IOException {
        String[] fileData = Files.readString(Path.of(file.getPath()), StandardCharsets.UTF_8).split("\n", 2);
        this.subject = fileData[0];
        this.body    = fileData[1];
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        return subject + "\n" + body;
    }
}
