package model;

import java.util.HashSet;

public class SlangWord {
    private String word;
    private HashSet<String> definitions;

    public SlangWord() {
        definitions = new HashSet<String>();
    }

    public SlangWord(String word, HashSet<String> definitions) {
        this.word = word;
        this.definitions = definitions;
    }

    public static SlangWord fromString(String line) {
        SlangWord slangWord = new SlangWord();
        String[] tokens = line.split("`");
        slangWord.setWord(tokens[0].trim());
        String[] defs = tokens[1].split("|");
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

    public HashSet<String> getDefinitions() {
        return this.definitions;
    }

    public void setDefinitions(HashSet<String> definitions) {
        this.definitions = definitions;
    }

    public void addDefinition(String newDefinition) {
        this.definitions.add(newDefinition);
    }
}
