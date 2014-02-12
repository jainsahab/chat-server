package com.prateekj;

import java.io.IOException;

public class ChatServer {
    public void run(){
        MessageServerObserver messageServerObserver = new MessageServerObserver() {
            @Override
            public void onMessage(String message) {
                System.out.println(message);
            }

            @Override
            public void onError(Exception e) {
                throw new RuntimeException("Gaya Paani Taalab mein",e);
            }
        };
        final MessageServer server = new MessageServer(messageServerObserver);
        UserInputReaderObserver observer = new UserInputReaderObserver() {
            @Override
            public void onQuit() {
                server.stop();
                System.exit(0);
            }
        };

        UserInputReader userInputReader = new UserInputReader(new SystemInputScanner(), observer);
        userInputReader.start();
        server.start();

        System.out.println("started server:");
    }
}
