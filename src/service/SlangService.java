package service;

import java.util.ArrayList;

import model.SlangWord;
import repository.HistoryRepository;
import repository.SlangRepository;
import util.Utils;

public class SlangService {
    private static SlangRepository repository;

    public static void start() {
        repository = SlangRepository.getInstance();
        HistoryRepository.getInstance().load();
    }

    public static void stop() {
        repository.save();
        HistoryRepository.getInstance().save();
    }

    public static SlangWord findByWord(String word) {
        ArrayList<SlangWord> result = new ArrayList<>();
        result.add(repository.getTree().findLeaf(Utils.stringToCharList(word)));
        if (result != null && !result.isEmpty()) {
            HistoryRepository.getInstance().add(word, result);
        }
        return result.get(0);
    }

    public static ArrayList<SlangWord> findByDefinition(String words) {
        ArrayList<SlangWord> result = repository.getTreeDef().filter(Utils.stringToWordList(words));
        if (result != null && !result.isEmpty()) {
            HistoryRepository.getInstance().add(words, result);
        }
        return result;
    }

    public static void addSlangWord(SlangWord newSW) {
        repository.add(newSW);
    }

    public static boolean updateSlangWord(SlangWord newSW) {
        return repository.update(newSW);
    }

    public static boolean removeSlangWord(String word) {
        return repository.remove(word);
    }

    public static SlangWord getRandomWord() {
        return repository.getRandomWord();
    }

    public static void resetSlangWord() {
        repository.reset();
    }
}
