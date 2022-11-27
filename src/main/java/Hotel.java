import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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

        Room room;
        Room standard = new Room("Standard", 150_000);
        Room deluxe = new Room("Deluxe", 300_000);
        Room suit = new Room("Suite", 500_000);

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
        return income;
    }

    public List<Book> getTotalBookList() {
        return totalBookList;
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

