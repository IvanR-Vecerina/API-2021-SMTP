package Config;

import java.io.IOException;

public class testConfig {
    public static void main(String[] args) throws IOException {
        Config cfg = new Config();

        System.out.println(cfg.getServerAddress());
        System.out.println(cfg.getServerPort());
        System.out.println(cfg.getNbGroups());
        System.out.println(cfg.getWitnessEmail());
    }
}
