package MailBot.Config;

/**
 * Interface of ConfigManager
 * @author Ivan Vecerina
 */
public interface IConfigManager {

    // Getter for witnesses email addresses
    String[] getWitnessEmail();

    // Getter SMTP server addresses
    String   getServerAddress();

    // Getter SMTP server port number
    int      getServerPort();

    // Getter number of emails/groups to send/make
    int      getNbGroups();

}
