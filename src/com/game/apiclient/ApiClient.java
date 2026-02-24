package com.game.apiclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.game.dto.HTTPResponseDTO;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiClient {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static HTTPResponseDTO post(String urlString, Object body) throws Exception {

        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);
        // send JSON body
        String jsonBody = mapper.writeValueAsString(body);
        System.out.println("jsonBody : " + jsonBody);
        try (OutputStream os = conn.getOutputStream()) {
            os.write(jsonBody.getBytes());
        }

        // read response
        BufferedReader br;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() < 300) {
            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        StringBuilder response = new StringBuilder();
        String line;

        while ((line = br.readLine()) != null) {
            response.append(line);
        }
        br.close();

        return HTTPResponseDTO.builder()
                .message(response.toString())
                .statusCode(conn.getResponseCode())
                .build();
    }
}
