package service;

import model.SlangWord;
import repository.HistoryRepository;
import repository.SlangRepository;
import util.Utils;

public class SlangService {
    private static SlangRepository repository;

    public static void start() {
        repository = SlangRepository.getInstance();
    }

    public static void stop() {
        repository.save();
    }

    public static SlangWord findByWord(String word) {
        SlangWord result = repository.getTree().find(Utils.stringToCharList(word));
        if (result != null) {
            HistoryRepository.getInstance().addLog(result);
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
