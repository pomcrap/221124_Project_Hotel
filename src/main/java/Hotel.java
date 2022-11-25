import java.util.ArrayList;
import java.util.List;

public class Hotel {
     private List<Room> room;
     private int income;
     private List<Book> totalBookList = new ArrayList<>();


     public Room getRoom(int no) {
          assert no > 0;
          assert no < 4;
          return this.room.get(no - 1);
     }

     public int getIncome() {
          return income;
     }

     public List<Book> getTotalBookList() {
          return totalBookList;
     }
}
