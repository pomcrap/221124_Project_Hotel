import java.util.ArrayList;
import java.util.List;

public class Hotel {
     private Room room;
     private int income;
     private List<Book> totalBookList = new ArrayList<>();


     public Room getRoom() {
          return room;
     }

     public int getIncome() {
          return income;
     }

     public List<Book> getTotalBookList() {
          return totalBookList;
     }
}

