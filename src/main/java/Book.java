import java.time.LocalDateTime;

public class Book implements Comparable<Book>{
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

    //Book의 생성자
    Book(){}
    Book(Room room, String bookId, Guest guest, LocalDateTime bookDate){
        this.room = room;
        this.bookId = bookId;
        this.guest = guest;
        this.bookDate = bookDate;
    }


    //bookDate를 사용해 totalBookList를 sort할수있게 Compareable을 implement해서 설정했습니다.
    //동작하는지는 확인해야합니다.
    @Override
    public int compareTo(Book book) {
        if(book.bookDate.isBefore(bookDate)){
            return 1;
        }else if(book.bookDate.isAfter(bookDate)){
            return -1;
        }
        return 0;
    }
}



