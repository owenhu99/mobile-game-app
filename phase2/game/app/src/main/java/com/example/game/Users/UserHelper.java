package com.example.game.Users;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserHelper {

    public static void add(User user, String filePath) {
        if (!new File(filePath).exists()) {
            create(user, filePath);
        } else {
            append(user, filePath);
        }
    }

    private static void create(User user, String filePath) {
        try (FileWriter fw = new FileWriter(filePath);
             CSVPrinter cp = new CSVPrinter(fw, CSVFormat.DEFAULT.withHeader("First Name", "Last Name", "User Name", "Stats"))) {
            cp.printRecord(user.getFirstName(), user.getLastName(), user.getUserName(), user.combineStats());
            cp.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void append(User user, String filePath) {
        try (FileWriter fw = new FileWriter(filePath, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(getLine(user));
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void update(User user, String filePath) {
        try (CSVParser csvParser = new CSVParser(new FileReader(filePath), CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {
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

            deleteSaveFile(filePath);

            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath, true));
            bufferedWriter.write("First Name,Last Name,User Name,Stats\n");
            for (String line : editedList) {
                bufferedWriter.write(line + "\n");
            }
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteUser(User user, String filePath) {
        try (CSVParser csvParser = new CSVParser(new FileReader(filePath), CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {
            List<CSVRecord> list = csvParser.getRecords();
            ArrayList<CSVRecord> editedList = new ArrayList<>();
            for (CSVRecord record : list) {
                if (!record.get("User Name").equals(user.getUserName())) {
                    editedList.add(record);
                }
            }

            deleteSaveFile(filePath);

            FileWriter fw = new FileWriter(filePath);
            CSVPrinter cp = new CSVPrinter(fw, CSVFormat.DEFAULT.withHeader("First Name", "Last Name", "User Name", "Stats"));
            for (CSVRecord record : editedList) {
                cp.printRecord(record);
            }
            cp.flush();
            cp.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getLine(User user) {
        return user.getFirstName() + "," + user.getLastName() + "," + user.getUserName() + "," + user.combineStats() + "\n";
    }

    public static void deleteSaveFile(String filePath) {
        new File(filePath).delete();
    }
}
