package service;

import model.SlangWord;
import repository.AppRepository;
import util.Utils;

public class SlangService {
    private static AppRepository repository = new AppRepository();

    public static SlangWord findByWord(String word) {
        return repository.getTree().find(Utils.stringToCharList(word));
    }
}
