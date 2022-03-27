package repository;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import model.SearchHistory;
import model.SlangWord;

public class HistoryRepository {
    private static HistoryRepository instance;

    public static HistoryRepository getInstance() {
        if (instance == null) {
            instance = new HistoryRepository();
        }
        return instance;
    }

    private HistoryRepository() { }

    private ArrayList<SearchHistory<SlangWord>> _history;

    private static final String HISTORY_FILE_NAME = "history.dat";

    public ArrayList<SearchHistory<SlangWord>> get() {
        return _history;
    }

    public void load() {
        _history = new ArrayList<>();
        ObjectInputStream ois;
        try {
            InputStream is = new FileInputStream(HISTORY_FILE_NAME);
            ois = new ObjectInputStream(is);
            while (true) {
                try {
                    SearchHistory<SlangWord> his = (SearchHistory<SlangWord>) ois.readObject();
                    _history.add(his);
                } catch (EOFException e) {
                    break;
                }
            }
        ois.close();
        } catch (Exception e) {
        }
    }

    public void save() {
        ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(HISTORY_FILE_NAME));
            for (SearchHistory<SlangWord> log : _history) {
                oos.writeObject(log);
            }
            oos.close();
        } catch (Exception e) {
            System.out.println("TreeHelper -> saveTreeToFile(): " + e.toString());
        }
    }

    public void add(String keyword, ArrayList<SlangWord> result) {
        SearchHistory<SlangWord> log = new SearchHistory<SlangWord>(keyword, result, System.currentTimeMillis());
        _history.add(log);
    }
}
