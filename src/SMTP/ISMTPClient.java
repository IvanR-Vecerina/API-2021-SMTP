package SMTP;

import Model.Mail;

import java.io.*;

public interface ISMTPClient {
    public void startConnection() throws IOException;
    public void startSession()    throws IOException;
    public void sendMail(Mail m)  throws IOException;
    public void closeSession()    throws IOException;
    public void closeConnection() throws IOException;
}
