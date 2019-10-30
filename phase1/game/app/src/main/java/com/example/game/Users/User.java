package com.example.game.Users;

import android.os.Parcel;
import android.os.Parcelable;

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

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setTotalTime(double totalTime) {
        this.totalTime = totalTime;
    }

    public double getTotalTime() {
        return totalTime;
    }

    public void setTotalWins(int wins) {
        this.totalWins = wins;
    }

    public int getTotalWins() {
        return totalWins;
    }

    public int getTotalGames() {
        return totalGames;
    }

    public void setTotalGames(int totalGames) {
        this.totalGames = totalGames;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getGold() {
        return gold;
    }

    public void clearStats() {
        this.totalGames = 0;
        this.totalTime = 0;
        this.totalWins = 0;
        this.gold = 0;
    }

    public void update() {
        UserHelper.update(this);
    }

    // increases the user's total wins and increases gold equal to 10 gold per win, increase total time played

    public void updateUserStats(int wins, double time, int totalGames) {
        this.totalWins += wins;
        this.gold += wins * 10;
        this.totalTime += time;
        this.totalGames += totalGames;
    }

    public String[][] parseStats(String stats) {
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

    // decreases the user's gold when they buy something
    public void decreaseGold(int gold) {
        this.gold -= gold;
    }

    /**
     * Formats all the stats for storage as a csv field
     * Each stat must be in the format '<unique abbreviation>=<value>' separated with '&'
     *
     * @return a string of stats formatted for csv storage
     */
    public String combineStats() {
        return "tt=" + totalTime + "&tw=" + totalWins + "&tg=" + totalGames + "&g=" + gold;
    }

    /**
     * Parcelable requirements
     */
    public User(Parcel in) {
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

}
