package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SearchHistory<T> implements Serializable {
    private String keyword;
    private ArrayList<T> result;
    private long time;

    public SearchHistory(String keyword, ArrayList<T> result, long time) {
        this.keyword = keyword;
        this.result = result;
        this.time = time;
    }

    public SearchHistory(String keyword, T result, long time) {
        this.keyword = keyword;
        this.result = new ArrayList<>();
        this.result.add(result);
        this.time = time;
    }

    public String getKeyword() {
        return keyword;
    }

    public ArrayList<T> getResult() {
        return result;
    }

    public String getTime() {
        SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy HH:mm:ss z");
        Date date = new Date(time);
        return formatter.format(date);
    }
}
