package com.github.rsrulloda;

public class Word {
    // Private variables
    private int ID;
    private String japanese;
    private String furigana;
    private String english;
    private String pos; // Part of Speech

    // Constructor
    public Word(int ID, String japanese, String furigana, String english, String pos) {
        this.ID = ID;
        this.japanese = japanese;
        this.furigana = furigana;
        this.english = english;
        this.pos = pos;
    }

    // Getters & Setters
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getJapanese() {
        return japanese;
    }

    public void setJapanese(String japanese) {
        this.japanese = japanese;
    }

    public String getFurigana() {
        return furigana;
    }

    public void setFurigana(String furigana) {
        this.furigana = furigana;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }
}
