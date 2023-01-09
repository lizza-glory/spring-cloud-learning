package com.lizza.buyder;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.net.URLConnection;

public class UrlTests {

    @Test
    public void test1() throws Exception {
        URL url = new URL("http", "localhost", 8081, "/order-service/getOrders");
        URLConnection connection = url.openConnection();
        System.out.println(connection.getHeaderFields());
        connection.setDoOutput(true);
        connection.connect();
        InputStream is = connection.getInputStream();
        byte[] bytes = new byte[10];
        StringBuilder result = new StringBuilder();
        while (is.read(bytes) != -1) {
            result.append(new String(bytes));
        }
        System.out.println(result);
        is.close();
    }
}
