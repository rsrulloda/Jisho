package com.github.rsrulloda;

public class Word {
    private String word; // In English
    private String kanji;
    private String hiragana;
    private String description;
    private String pos; // Part of Speech

    public Word(String word, String kanji, String hiragana, String description, String pos) {
        this.word = word;
        this.kanji = kanji;
        this.hiragana = hiragana;
        this.description = description;
        this.pos = pos;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getKanji() {
        return kanji;
    }

    public void setKanji(String kanji) {
        this.kanji = kanji;
    }

    public String getHiragana() {
        return hiragana;
    }

    public void setHiragana(String hiragana) {
        this.hiragana = hiragana;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }
}
