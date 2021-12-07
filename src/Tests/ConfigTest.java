package Tests;

import Config.ConfigManager;

import java.io.IOException;
import java.util.Arrays;

public class ConfigTest {
    public static void main(String[] args) throws IOException {
        ConfigManager cfg = new ConfigManager();

        System.out.println(cfg.getServerAddress());
        System.out.println(cfg.getServerPort());
        System.out.println(cfg.getNbGroups());
        System.out.println(Arrays.toString(cfg.getWitnessEmail()));
    }
}
