package Model;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Sale {
    private int code;
    private LocalDate date;
    private int userID;

    public Sale(int code, LocalDate date, int userID) {
        this.code = code;
        this.date = date;
        this.userID = userID;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getUserID() {
        return userID;
    }

}
