import java.time.LocalDateTime;
import java.util.Scanner;

public class HotelService {
    Hotel hotel = new Hotel();  //Hotel class 의 인스턴스 선언.
    Book book = new Book();     //book class 의 인스턴스 선언 ('hotel.getTotalBookList()'가 'book' 의 값 사용)

    void getTotalBookList() {
        for (Book book : hotel.getTotalBookList()){
            System.out.println(book.getBookDate() + ", " + book.getRoom() + "," + book.getBookId() + ", " + book.getGuest());
        }
    } //전체예약리스트 조회.
}
