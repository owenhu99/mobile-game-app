package com.example.game;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class UserUpdateHelper {
    private static String FILEPATH = "/data/user/0/com.example.game/files/users.csv";

    public static void add(User user) {
        if (!new File(FILEPATH).exists()) {
            create(user);
        } else {
            append(user);
        }
    }

    private static void create(User user) {
        try (FileWriter fw = new FileWriter(FILEPATH);
             CSVPrinter cp = new CSVPrinter(fw, CSVFormat.DEFAULT.withHeader("First Name", "Last Name", "User Name"))) {
            cp.printRecord(user.getFirstName(), user.getLastName(), user.getUserName());
            cp.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void append(User user) {

    }

    public static void update(User user) {

    }
}
