package MailBot.Config;

import java.io.*;
import java.util.Properties;

/**
 * ConfigManager
 * This class extracts the config from the config.properties file.
 * @author Ivan Vecerina
 */
public class ConfigManager implements IConfigManager {
    private final String   serverAddress;
    private final int      serverPort;
    private final int      nbGroups;
    private final String[] witnessEmail;

    /**
     * Constructor of the class reading the data from config file
     * @throws IOException if file is empty
     */
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

            //
            this.serverAddress = config.getProperty("smtpServerAddress");
            this.serverPort    = Integer.parseInt(config.getProperty("smtpServerPort"));
            this.nbGroups      = Integer.parseInt(config.getProperty("numberOfGroups"));
            this.witnessEmail  = config.getProperty("witnessesToCC").split(",");

            is.close();
        } catch (Exception e) {
            throw new IOException("Exception: " + e);
        }
    }

    /**
     * Server address getter
     * @return String of the target server address
     */
    public String getServerAddress() {
        return serverAddress;
    }

    /**
     * Server port getter
     * @return Number of the target server port
     */
    public int getServerPort() {
        return serverPort;
    }

    /**
     * Number of emails/groups getter
     * @return Number of Emails/Groups to send/make
     */
    public int getNbGroups() {
        return nbGroups;
    }

    /**
     * Witness email addresses array
     * @return String array of Email addresses of witnesses
     */
    public String[] getWitnessEmail() {
        return witnessEmail;
    }
}
