package com.example.kirin.cs2340.Model;

import android.os.AsyncTask;
import android.util.Log;

import javax.mail.Authenticator;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 * Created by Kirin on 3/7/2017.
 * Sends mail for forgot password functionality
 */

public class GMailSender extends Authenticator {
    private final String user;
    private final String password;
    private final Session session;

    /**
     * Constructor for an email sender
     */
    public GMailSender() {
        this.user = "waterdonotreply@yahoo.com";
        this.password = "water12345";

        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.mail.yahoo.com");
        props.put("mail.smtp.port", "25");

        session = Session.getDefaultInstance(props, this);
    }

    /**
     * Gets current user of sender
     * @return current user of sender
     */
    public String getUser() {
        return this.user;
    }

    /**
     * Returns an object that authenticates user
     * @return object that authenticates user
     */
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(user, password);
    }

    /**
     * Sends mail to forgot password recipient
     * @param body text of email
     * @param sender sender of email
     * @param recipients recipients of email
     * @throws Exception Checked exception thrown by Transport
     */
    public synchronized void sendMail(String body, String sender, String recipients) throws Exception {
        String subject = "Water Password Reset";
        final MimeMessage message = new MimeMessage(session);
        DataHandler handler = new DataHandler(new ByteArrayDataSource(body.getBytes()));
        message.setSender(new InternetAddress(sender));
        message.setSubject(subject);
        message.setDataHandler(handler);
        if (recipients.indexOf(',') > 0)
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipients));
        else
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipients));
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected void onPreExecute() {
                Log.d("TAG", "onPreExecute()");
            }

            @Override
            protected Void doInBackground(Void... params) {
                Log.d("TAG", "doInBackground() -- Here is the download");
                try {
                    Transport.send(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void res) {
                Log.d("TAG", "onPostExecute()");
            }
        }.execute();
    }

    /**
     * Inner class that creates email text
     */
    public class ByteArrayDataSource implements DataSource {
        private final byte[] data;
        private final String type;

        /**
         * Constructor for ByteArrayDataSource
         * @param data the data to write to message
         */
        public ByteArrayDataSource(byte[] data) {
            super();
            this.data = data;
            this.type = "text/plain";
        }

        /**
         * Gets content type of message
         * @return content type of message
         */
        public String getContentType() {
            if (type == null)
                return "application/octet-stream";
            else
                return type;
        }

        /**
         * Gets new input stream object
         * @return input stream object
         * @throws IOException checked IOException thrown
         */
        public InputStream getInputStream() throws IOException {
            return new ByteArrayInputStream(data);
        }

        /**
         * Gets name of object
         * @return name of object
         */
        public String getName() {
            return "ByteArrayDataSource";
        }

        /**
         * Unsupported output stream method
         * @return nothing, as unsupported
         * @throws IOException to show as unsupported
         */
        public OutputStream getOutputStream() throws IOException {
            throw new IOException("Not Supported");
        }
    }
}
