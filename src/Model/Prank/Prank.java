package Model.Prank;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class Prank {
    private final String prank;

    public Prank(File file) throws IOException {
        this.prank = Files.readString(Path.of(file.getPath()), StandardCharsets.UTF_8);
    }

    public String getPrank() {
        return prank;
    }
}
