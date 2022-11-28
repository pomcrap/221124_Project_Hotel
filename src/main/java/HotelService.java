import java.time.LocalDateTime;
import java.util.Scanner;

public class HotelService {
    public HotelService(Hotel hotel) {
        this.hotel = hotel;
    }

    private final Hotel hotel;  //Hotel class 의 인스턴스 선언.

    void getTotalBookList() {
        for (Book book : hotel.getTotalBookList()){
            System.out.println(book.getBookDate() + ", " + book.getRoom() + "," + book.getBookId() + ", " + book.getGuest());
        }
    } //전체예약리스트 조회.
}
