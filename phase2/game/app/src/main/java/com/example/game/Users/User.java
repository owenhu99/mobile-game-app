package com.example.game.Users;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.game.R;

public class User implements Parcelable {
    private String userName;
    private String firstName;
    private String lastName;
    private double totalTime;
    private int totalWins;
    private int totalGames;
    private int gold;


    public User(String userName, String firstName, String lastName) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.totalTime = 0;
        this.totalWins = 0;
        this.totalGames = 0;
        this.gold = 0;
    }

    /**
     * Updates all stats when a game finishes
     */
    public void updateStats(int wins, double time, int totalGames) {
        this.totalWins += wins;
        this.gold += wins * 10;
        this.totalTime += time;
        this.totalGames += totalGames;
    }

    /**
     * Print the current stats for displaying at the game menu
     */
    public String printStats() {
        return "Current User: " + userName + "\nTotal Games: " + totalGames + "\nTotal Wins: " + totalWins + "\nTotal Time: " + totalTime + "\nGold: " + gold;
    }

    /**
     * Parse the stats field of the csv save file
     *
     * @param stats 'Stats' field of the csv file, in the format of '<unique abbreviation>=<value>'
     *              separated with '&', for example: 'tt=0.0&tg=0&tw=0&g=0'
     * @return a 2D array of size <number of different stats> x 2, for example:
     * [ ["tt", "0.0"],
     * ["tg", "0"],
     * ["tw", "0"],
     * ["g", "0"]]
     */
    private String[][] parseStats(String stats) {
        String[] firstRound = stats.split("&");
        String[][] ret = new String[firstRound.length][2];
        int i = 0;
        for (String str : firstRound) {
            String[] secondRound = str.split("=", 2);
            ret[i][0] = secondRound[0];
            ret[i][1] = secondRound[1];
            i++;
        }
        return ret;
    }

    /**
     * Loading the User's stats according to the csv save file
     * @param stats concatenated string of all stats fields
     */
    public void setStatsFromCSV(String stats) {
        String[][] values = parseStats(stats);
        for (String[] subArr : values) {
            switch (subArr[0]) {
                case "tt":
                    totalTime = Double.parseDouble(subArr[1]);
                    break;
                case "tw":
                    totalWins = Integer.valueOf(subArr[1]);
                    break;
                case "tg":
                    totalGames = Integer.valueOf(subArr[1]);
                    break;
                case "g":
                    gold = Integer.valueOf(subArr[1]);
                    break;
            }
        }
    }

    /**
     * Decrease gold by a certain amount
     * @param gold amount of gold decreased
     */
    public void decreaseGold(int gold) {
        this.gold -= gold;
    }

    /**
     * Formats stats for storage as a csv field
     * Each stat must be in the format '<unique abbreviation>=<value>' separated with '&'
     *
     * @return a string of stats formatted for csv storage
     */
    String combineStats() {
        return "tt=" + totalTime + "&tw=" + totalWins + "&tg=" + totalGames + "&g=" + gold;
    }

    /**
     * Parcelable requirements
     */
    private User(Parcel in) {
        this.userName = in.readString();
        this.firstName = in.readString();
        this.lastName = in.readString();
        this.totalTime = in.readDouble();
        this.totalWins = in.readInt();
        this.totalGames = in.readInt();
        this.gold = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(this.userName);
        out.writeString(this.firstName);
        out.writeString(this.lastName);
        out.writeDouble(this.totalTime);
        out.writeInt(this.totalWins);
        out.writeInt(this.totalGames);
        out.writeInt(this.gold);
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };


    /**
     * Following are getter and setter methods for the class variables
     */
    public String getUserName() {
        return userName;
    }

    String getFirstName() {
        return firstName;
    }

    String getLastName() {
        return lastName;
    }

    public void setTotalGames(int totalGames) {
        this.totalGames = totalGames;
    }
}
