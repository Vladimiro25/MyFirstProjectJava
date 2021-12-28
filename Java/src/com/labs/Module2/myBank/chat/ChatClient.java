package com.labs.Module2.myBank.chat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.*;
import java.io.*;

public class ChatClient {
    private JTextArea output;
    private JTextField input;
    private JButton sendButton;
    private JButton quitButton;

    private Socket connection;
    private BufferedReader serverIn;
    private PrintStream serverOut;

    private void doConnect()
    {
        String serverIP = System.getProperty("serverIP","192.0.0.1");
        String serverPort = System.getProperty("serverPort","2000");
        try
        {
            connection = new Socket(serverIP,Integer.parseInt(serverPort));
            InputStream is = connection.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            serverIn = new BufferedReader(isr);
            serverOut = new PrintStream(connection.getOutputStream());
            Thread t = new Thread(new RemoteReader());
            t.start();
        }
        catch (Exception ex)
        {
            System.err.println("ERROR: enable to connect to server!");
            ex.printStackTrace();
        }
    }

    public ChatClient() {
        this.output = new JTextArea(10,50);
        this.input = new JTextField(50);
        this.sendButton = new JButton("Send");
        this.quitButton = new JButton("Quit");
    }

    public void launchFrame()
    {
        JFrame frame = new JFrame("Bank Chat Room");
        frame.setLayout(new BorderLayout());

        frame.add(output,BorderLayout.CENTER);
        frame.add(input,BorderLayout.SOUTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2,1));
        buttonPanel.add(sendButton);
        buttonPanel.add(quitButton);

        frame.add(buttonPanel,BorderLayout.EAST);
        output.setEditable(false);

        input.addActionListener(new SendHadler());
        sendButton.addActionListener(new SendHadler());
        quitButton.addActionListener(e -> System.exit(0));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setAlwaysOnTop(true);
        frame.setLocationRelativeTo(null);
        frame.pack();

        frame.setVisible(true);

        doConnect();
    }

    public static void main(String[] args) {
        ChatClient myChat = new ChatClient();
        myChat.launchFrame();
    }
    private class RemoteReader implements Runnable {
        @Override
        public void run() {
            try
            {
                while (true)
                {
                    String nextLine = serverIn.readLine();
                    output.append(nextLine+"\n");

                }
            }
            catch (Exception ex)
            {
                System.err.println("ERROR: can't read from the server!");
                ex.printStackTrace();
            }
        }
    }

    private class SendHadler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            String message = input.getText();
            //output.append(message+"\n");
            serverOut.print("New message: "+message+"\n");
            input.setText("");
        }
    }


}
