package MailBot.SMTP;

import MailBot.Model.Mail;

import java.io.*;

/**
 * interface of SMTPClient class
 * @author Ivan Vecerina
 */
public interface ISMTPClient {
    // Connect
    public void startConnection() throws IOException;

    // Open session
    public void startSession()    throws IOException;

    // Send an Email
    public void sendMail(Mail m)  throws IOException;

    // Close session
    public void closeSession()    throws IOException;

    // Disconnect
    public void closeConnection() throws IOException;
}
