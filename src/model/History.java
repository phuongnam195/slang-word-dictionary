package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class History<T> implements Serializable {
    private T data;
    private long time;

    public History(T data, long time) {
        this.data = data;
        this.time = time;
    }

    public T getData() {
        return data;
    }

    public String getTime() {
        SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy HH:mm:ss z");
        Date date = new Date(time);
        return formatter.format(date);
    }
}
