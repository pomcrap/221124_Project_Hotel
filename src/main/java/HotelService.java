import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HotelService {
private final Hotel hotel;
    public  HotelService (Hotel hotel) {  //HotelService를 만들때 기존 Hotel 정보를 받아 옴
        this.hotel=hotel;
    }
    public List<Book> getTotalBookList() {
        return hotel.getTotalBookList();
    } //전체예약리스트 조회.

    public int getIncome() {
        return hotel.getIncome() ;
    }
}
