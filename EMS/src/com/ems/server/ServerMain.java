package com.ems.server;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class ServerMain {

    public static void main(String[] args) throws IOException {
        // Initialize Database (H2)
        com.ems.util.DatabaseInitializer.initialize();

        int port = 9090;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        // Core Contexts
        server.createContext("/", new StaticFileHandler()); // UI
        server.createContext("/api/auth", new AuthHandler()); // Login
        server.createContext("/api/employee", new EmployeeHandler()); // Data

        server.setExecutor(Executors.newCachedThreadPool()); // Multi-threaded
        server.start();

        System.out.println("=================================================");
        System.out.println("   EMS Server Started Successfully!  🚀");
        System.out.println("=================================================");
        System.out.println("URL: http://localhost:" + port + "/index.html");
        System.out.println("Keep this window open to keep the server running.");
        System.out.println("=================================================");
    }
}
