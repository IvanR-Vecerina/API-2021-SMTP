package MailBot.Config;

import java.io.*;
import java.util.Properties;

public class ConfigManager implements IConfigManager {
    private String   serverAddress;
    private int      serverPort;
    private int      nbGroups;
    private String[] witnessEmail;

    public ConfigManager() throws IOException {
        try {
            Properties  config         = new Properties();
            String      configFileName = "config/config.properties";
            InputStream is             = new FileInputStream(configFileName);

            if (is != null) {
                config.load(is);
            } else {
                throw new FileNotFoundException("propreties file '" + configFileName + "' not found!");
            }

            this.serverAddress = config.getProperty("smtpServerAddress");
            this.serverPort    = Integer.parseInt(config.getProperty("smtpServerPort"));
            this.nbGroups      = Integer.parseInt(config.getProperty("numberOfGroups"));
            this.witnessEmail  = config.getProperty("witnessesToCC").split(",");

            is.close();
        } catch (Exception e) {
            throw new IOException("Exception: " + e);
        }
    }

    public String getServerAddress() {
        return serverAddress;
    }

    public int getServerPort() {
        return serverPort;
    }

    public int getNbGroups() {
        return nbGroups;
    }

    public String[] getWitnessEmail() {
        return witnessEmail;
    }
}
