import java.time.LocalDateTime;
import java.util.*;

public class Hotel {
    private List<Room> rooms;
    // 방종류
    private int income;
    private List<Book> totalBookList = new ArrayList<>();
    /**
     * 생정자는 필드밑에 작성하시는게 코드컨벤션입니다.
     * */
    Hotel(){
        this.rooms = new ArrayList<>();

        String standardDetail = "전망 : 빌딩뷰 | 욕조 : 없음 | 부가서비스 : 없음 | 와이파이 : 있음";
        String deluxeDetail = "전망 : 시티뷰 | 욕조 : 있음 | 부가서비스 : 넷플릭스 무료, 웰컴드링크 제공 | 와이파이 : 있음";
        String suitDetail = "전망 : 오션뷰 | 욕조 : 있음 | 부가서비스 : 넷플릭스 무료, 웰컴드링크 제공, 전용 온수풀 | 와이파이 : 있음";

        Room standard = new Room("Standard", 150_000, standardDetail);
        Room deluxe = new Room("Deluxe", 300_000,deluxeDetail);
        Room suit = new Room("Suite", 500_000,suitDetail);

        rooms.add(standard);
        rooms.add(deluxe);
        rooms.add(suit);
    }


    public Room getRoom(int no) {
        assert no > 0;
        assert no < 4;
        return this.rooms.get(no - 1);
    }
    public List<Room> getRooms(){
        //GuestService > searchBookableRoom()에서 사용합니다.
        return rooms;
    }
    public int getIncome() {
        return this.income;
    }

    public List<Book> getTotalBookList() {
        return totalBookList;
    }

    public Book getBook(String bookId) {
        return this.getTotalBookList().stream()
                .filter(book -> Objects.equals(book.getBookId(), bookId))
                .findFirst()
                .get();
    }

    // Hotel의 income 하락 메소드 입니다.
    public int loseIncome(int incomeLose) {
        this.income -= incomeLose;
        return income;
    }

    // Hotel의 income 상승 메소드 입니다.
    public int earnIncome(int incomeEarn){
        this.income += incomeEarn;
        return income;
    }

}

