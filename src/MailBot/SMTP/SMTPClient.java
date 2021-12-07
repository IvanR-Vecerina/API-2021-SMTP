package MailBot.SMTP;

import MailBot.Model.Mail;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.logging.Logger;

/**
 * SMTPClient
 * Class offering all actions needed to make basic SMTP transactions
 * @author Ivan Vecerina
 */
public class SMTPClient implements ISMTPClient{
    private static final Logger LOG = Logger.getLogger(SMTPClient.class.getName());

    private String smtpServerAddress;
    private int smtpServerPort;

    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;

    private String line;

    /**
     * Constructor of client based on target SMTP server information
     * @param smtpServerAddress target SMTP server address
     * @param smtpServerPort    target SMTP server port
     */
    public SMTPClient(String smtpServerAddress, int smtpServerPort){
        LOG.info("Creating MailBot.SMTP Client");
        this.smtpServerAddress = smtpServerAddress;
        this.smtpServerPort    = smtpServerPort;
    }

    /**
     * Function establishing connection with the SMTP server
     * @throws IOException in case of mishap
     */
    public void startConnection() throws IOException {
        LOG.info("Strating connection with server");
        socket = new Socket(smtpServerAddress, smtpServerPort);
        writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
        line   = reader.readLine();
        LOG.info(line);
        LOG.info("Connection established.");
    }

    /**
     * Message transaction to open a session with server
     * @throws IOException in case of mishap
     */
    public void startSession() throws IOException {
        LOG.info("Opening session with server");
        writer.printf("EHLO localhost\r\n");
        line = reader.readLine();
        LOG.info(line);

        if (!line.startsWith("250")) {
            throw new IOException("MailBot.SMTP error: " + line);
        }
        while (line.startsWith("250-")){
            line = reader.readLine();
            LOG.info(line);
        }
        LOG.info("Session open");
    }

    /**
     * Message transaction to send a given Email supporting UTF-8
     * @param m Email to send
     * @throws IOException in case of mishap
     */
    public void sendMail(Mail m) throws IOException {
        LOG.info("Sending message");

        // Submit Sender
        writer.write("MAIL FROM:");
        writer.write(m.getFrom());
        writer.write("\r\n");
        writer.flush();
        line = reader.readLine();
        LOG.info(line);

        // Submit Recipients
        for (String to : m.getTo()) {
            writer.write("RCPT TO:");
            writer.write(to);
            writer.write("\r\n");
            writer.flush();
            line = reader.readLine();
            LOG.info(line);
        }

        // Submit witnesses in cc
        for (String cc : m.getCc()) {
            writer.write("RCPT TO:");
            writer.write(cc);
            writer.write("\r\n");
            writer.flush();
            line = reader.readLine();
            LOG.info(line);
        }

        // Start data block
        writer.write("DATA");
        writer.write("\r\n");
        writer.flush();
        line = reader.readLine();
        LOG.info(line);

        // Content type header
        writer.write("Content-Type: text/plain; charset=\"utf-8\"\r\n");

        // From header
        writer.write("From: " + m.getFrom() + "\r\n");

        // To header
        writer.write("To: " + m.getTo().get(0));
        for (int i = 1; i < m.getTo().size(); i++) {
            writer.write(", " + m.getTo().get(i));
        }
        writer.write("\r\n");

        // Cc header
        writer.write("Cc: " + m.getCc().get(0));
        for (int i = 1; i < m.getCc().size(); i++) {
            writer.write(", " + m.getCc().get(i));
        }
        writer.write("\r\n");

        // Subject header (UTF-8)
        writer.write("Subject: " );
        writer.write("=?utf-8?B?" + Base64.getEncoder().encodeToString(m.getSubject().getBytes(StandardCharsets.UTF_8)) + "?=");
        writer.write("\r\n");
        writer.flush();

        // Message body (UTF-8)
        writer.write(m.getMessage());
        writer.write("\r\n");
        writer.write(".");
        writer.write("\r\n");
        // End data block

        writer.flush();
        line = reader.readLine();
        LOG.info(line);
        LOG.info("Message sent");
    }

    /**
     * Message transaction to close the session with server
     * @throws IOException in case of mishap
     */
    public void closeSession() throws IOException {
        LOG.info("Closing session");
        writer.write("QUIT");
        writer.write("\r\n");
        writer.flush();
        line = reader.readLine();
        LOG.info(line);
        LOG.info("Session closed");
    }

    /**
     * Function terminating connection with the SMTP server
     * @throws IOException  in case of mishap
     */
    public void closeConnection() throws IOException {
        LOG.info("Closing connection");
        writer.close();
        reader.close();
        socket.close();
        LOG.info("Connection closed");
    }
}
