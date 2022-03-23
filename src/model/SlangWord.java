package model;

import java.io.Serializable;
import java.util.ArrayList;

public class SlangWord implements Serializable {
    private String word;
    private ArrayList<String> definitions;

    public SlangWord() {
        definitions = new ArrayList<String>();
    }

    public SlangWord(String word) {
        this.word = word;
        definitions = new ArrayList<String>();
    }

    public SlangWord(String word, ArrayList<String> definitions) {
        this.word = word;
        this.definitions = definitions;
    }

    public static SlangWord fromString(String line) {
        SlangWord slangWord = new SlangWord();
        String[] tokens = line.split("`");
        slangWord.setWord(tokens[0].trim());
        String[] defs = tokens[1].split("\\|");
        for (int i = 0; i < defs.length; i++) {
            slangWord.addDefinition(defs[i].trim());
        }
        return slangWord;
    }

    public String getWord() {
        return this.word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public ArrayList<String> getDefinitions() {
        return this.definitions;
    }

    public void setDefinitions(ArrayList<String> definitions) {
        this.definitions = definitions;
    }

    public void addDefinition(String newDefinition) {
        this.definitions.add(newDefinition);
    }

    @Override
    public String toString() {
        return "SlangWord{word=" + word + ", definitions=" + definitions + "}";
    }
}
