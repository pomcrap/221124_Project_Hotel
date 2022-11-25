import java.time.LocalDateTime;

public class Book {
    private Room room;
    private String bookId; //UUID 양식 사용.
    private Guest guest;
    private LocalDateTime bookDate; //ISO8601 UTC 시간

    public Room getRoom() {
        return room;
    }

    public String getBookId() {
        return bookId;
    }

    public Guest getGuest() {
        return guest;
    }

    public LocalDateTime getBookDate() {
        return bookDate;
    }

}



