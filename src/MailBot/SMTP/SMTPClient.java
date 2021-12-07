package MailBot.SMTP;

import MailBot.Model.Mail;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.logging.Logger;

public class SMTPClient implements ISMTPClient{
    private static final Logger LOG = Logger.getLogger(SMTPClient.class.getName());

    private String smtpServerAddress;
    private int smtpServerPort;

    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;

    private String line;

    public SMTPClient(String smtpServerAddress, int smtpServerPort){
        LOG.info("Creating MailBot.SMTP Client");
        this.smtpServerAddress = smtpServerAddress;
        this.smtpServerPort    = smtpServerPort;
    }

    public void startConnection() throws IOException {
        LOG.info("Strating connection with server");
        socket = new Socket(smtpServerAddress, smtpServerPort);
        writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
        line   = reader.readLine();
        LOG.info(line);
        LOG.info("Connection established.");
    }

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

    public void sendMail(Mail m) throws IOException {
        LOG.info("Sending message");


        writer.write("MAIL FROM:");
        writer.write(m.getFrom());
        writer.write("\r\n");
        writer.flush();
        line = reader.readLine();
        LOG.info(line);

        for (String to : m.getTo()) {
            writer.write("RCPT TO:");
            writer.write(to);
            writer.write("\r\n");
            writer.flush();
            line = reader.readLine();
            LOG.info(line);
        }

        for (String cc : m.getCc()) {
            writer.write("RCPT TO:");
            writer.write(cc);
            writer.write("\r\n");
            writer.flush();
            line = reader.readLine();
            LOG.info(line);
        }

        writer.write("DATA");
        writer.write("\r\n");
        writer.flush();
        line = reader.readLine();
        LOG.info(line);
        writer.write("Content-Type: text/plain; charset=\"utf-8\"\r\n");
        writer.write("From: " + m.getFrom() + "\r\n");

        writer.write("To: " + m.getTo().get(0));
        for (int i = 1; i < m.getTo().size(); i++) {
            writer.write(", " + m.getTo().get(i));
        }
        writer.write("\r\n");

        writer.write("Cc: " + m.getCc().get(0));
        for (int i = 1; i < m.getCc().size(); i++) {
            writer.write(", " + m.getCc().get(i));
        }
        writer.write("\r\n");

        writer.write("Subject: " );
        writer.write("=?utf-8?B?" + Base64.getEncoder().encodeToString(m.getSubject().getBytes(StandardCharsets.UTF_8)) + "?=");
        writer.write("\r\n");
        writer.flush();

        writer.write(m.getMessage());
        writer.write("\r\n");
        writer.write(".");
        writer.write("\r\n");
        writer.flush();
        line = reader.readLine();
        LOG.info(line);
        LOG.info("Message sent");
    }

    public void closeSession() throws IOException {
        LOG.info("Closing session");
        writer.write("QUIT");
        writer.write("\r\n");
        writer.flush();
        line = reader.readLine();
        LOG.info(line);
        LOG.info("Session closed");
    }

    public void closeConnection() throws IOException {
        LOG.info("Closing connection");
        writer.close();
        reader.close();
        socket.close();
        LOG.info("Connection closed");
    }
}
