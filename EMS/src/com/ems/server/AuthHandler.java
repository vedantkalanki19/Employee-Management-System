package com.ems.server;

import com.ems.util.DBConnection;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class AuthHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        if ("POST".equalsIgnoreCase(method)) {
            handleLogin(exchange);
        } else if ("GET".equalsIgnoreCase(method) && "action=logout".equals(exchange.getRequestURI().getQuery())) {
            redirect(exchange, "/thankyou.html");
        } else {
            sendResponse(exchange, 405, "Method Not Allowed");
        }
    }

    private void handleLogin(HttpExchange exchange) throws IOException {
        // Simple Form Parsing
        String formData = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
        Map<String, String> params = parseFormData(formData);

        String username = params.get("username");
        String password = params.get("password");
        String role = params.get("role");

        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM users WHERE username = ? AND password = ? AND role = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, role);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                // Determine redirect URL
                String redirectUrl = "ADMIN".equals(role) ? "/dashboard_admin.html" : "/dashboard_employee.html";
                redirect(exchange, redirectUrl);
            } else {
                redirect(exchange, "/login.html?error=invalid");
            }
        } catch (Exception e) {
            e.printStackTrace();
            sendResponse(exchange, 500, "Internal Server Error");
        }
    }

    private void redirect(HttpExchange exchange, String location) throws IOException {
        exchange.getResponseHeaders().set("Location", location);
        exchange.sendResponseHeaders(302, -1);
    }

    private void sendResponse(HttpExchange exchange, int statusCode, String response) throws IOException {
        exchange.sendResponseHeaders(statusCode, response.length());
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(response.getBytes());
        }
    }

    private Map<String, String> parseFormData(String formData) {
        Map<String, String> map = new HashMap<>();
        if (formData == null || formData.isEmpty())
            return map;

        String[] pairs = formData.split("&");
        for (String pair : pairs) {
            String[] keyValue = pair.split("=");
            if (keyValue.length == 2) {
                // Note: Should use URLDecoder here for real apps
                map.put(keyValue[0], keyValue[1]);
            }
        }
        return map;
    }
}
