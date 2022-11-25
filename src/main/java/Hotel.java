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


    public Room getRoom(int no) {
        assert no > 0;
        assert no < 4;
        return this.rooms.get(no - 1);
    }

    public int getIncome() {
        return income;
    }

    public List<Book> getTotalBookList() {
        return totalBookList;
    }

    Hotel(){
         this.rooms = new ArrayList<>();

         Room standard = new Room("Standard", 150_000);
         Room deluxe = new Room("Deluxe", 300_000);
         Room suit = new Room("Suite", 500_000);

         rooms.add(standard);
         rooms.add(deluxe);
         rooms.add(suit);
    }

}

