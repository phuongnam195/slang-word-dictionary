package repository;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import extension.AppendableObjectOutputStream;
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

    private HistoryRepository() {
    }

    private static final String HISTORY_FILE_NAME = "history.dat";

    public ArrayList<SearchHistory<SlangWord>> loadAllHistory() {
        ArrayList<SearchHistory<SlangWord>> result = new ArrayList<>();
        ObjectInputStream ois;
        try {
            InputStream is = new FileInputStream(HISTORY_FILE_NAME);
            ois = new ObjectInputStream(is);
            while (true) {
                try {
                    SearchHistory<SlangWord> his = (SearchHistory<SlangWord>) ois.readObject();
                    result.add(his);
                } catch (EOFException e) {
                    break;
                }
            }
        ois.close();
        } catch (Exception e) {}
        return result;
    }

    public boolean addLog(String keyword, ArrayList<SlangWord> result) {
        SearchHistory<SlangWord> log = new SearchHistory<SlangWord>(keyword, result, System.currentTimeMillis());
        try {
            File file = new File(HISTORY_FILE_NAME);
            ObjectOutputStream oOut;
            if (file.exists()) {
                oOut = new AppendableObjectOutputStream(new FileOutputStream(file));
            } else {
                oOut = new ObjectOutputStream(new FileOutputStream(file));
            }
            oOut.writeObject(log);
            oOut.close();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
