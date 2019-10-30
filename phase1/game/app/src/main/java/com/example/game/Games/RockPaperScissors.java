package com.example.game.Games;


import java.util.Random;

public class RockPaperScissors {
    private String[] choices = {"Rock", "Paper", "Scissors"};

    private String currentBotChoice;
    private String currentUserChoice;

    public RockPaperScissors(String userChoice) {
        this.currentUserChoice = userChoice;
    }

    // sets the user's choice for this round
    public void setUserChoice(String choice) {
        this.currentUserChoice = choice;
    }


    // The bot chooses string in array, choices. Returns that choice.
    public void setBotChoice() {
        Random rand = new Random();
        int indx = rand.nextInt(3);
        this.currentBotChoice = choices[indx];
    }

    //returns bot choice
    public String getBotChoice() {
        return currentBotChoice;
    }

    // returns who wins that round and tie if there's a tie
    public String findResult() {
        String result = "If you see this, there's an error in get_result in RPS";

        switch (this.currentUserChoice) {
            case "Rock":
                if (this.currentBotChoice.equals("Paper")) {
                    result = "bot";
                } else if (this.currentBotChoice.equals("Scissors")) {
                    result = "user";
                } else {
                    result = "tie";
                }
                break;
            case "Paper":
                if (this.currentBotChoice.equals("Rock")) {
                    result = "user";
                } else if (this.currentBotChoice.equals("Scissors")) {
                    result = "bot";
                } else {
                    result = "tie";
                }
                break;

            case "Scissors":
                if (this.currentBotChoice.equals("Paper")) {
                    result = "user";
                } else if (this.currentBotChoice.equals("Rock")) {
                    result = "bot";
                } else {
                    result = "tie";
                }
                break;
        }

        return result;
    }
}
