package com.example.game;

import android.util.Log;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class UserHelper {
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
             CSVPrinter cp = new CSVPrinter(fw, CSVFormat.DEFAULT.withHeader("First Name", "Last Name", "User Name", "Stats"))) {
            cp.printRecord(user.getFirstName(), user.getLastName(), user.getUserName(), user.combineStats());
            cp.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void append(User user) {
        try (FileWriter fw = new FileWriter(FILEPATH, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(getLine(user));
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void update(User user) {
        try (CSVParser csvParser = new CSVParser(new FileReader(FILEPATH), CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {
            List<CSVRecord> list = csvParser.getRecords();
            String[] editedList = new String[list.size()];
            int i = 0;
            for (CSVRecord record : list) {
                if (record.get("User Name").equals(user.getUserName())) {
                    editedList[i] = getLine(user);
                } else {
                    editedList[i] = record.get("First Name") + "," + record.get("Last Name") + "," + record.get("User Name") + "," + record.get("Stats");
                }
                i++;
            }

            deleteSaveFile();

            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(FILEPATH, true));
            bufferedWriter.write("First Name,Last Name,User Name,Stats\n");
            for (String line : editedList) {
                bufferedWriter.write(line + "\n");
            }
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static String getLine(User user) {
        return user.getFirstName() + "," + user.getLastName() + "," + user.getUserName() + "," + user.combineStats() + "\n";
    }

    public static void deleteSaveFile() {
        new File(FILEPATH).delete();
    }
}
