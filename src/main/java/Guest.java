import java.util.ArrayList;
import java.util.List;

public class Guest {

    private String name;
    private String phoneNum;
    private int money;

    private List<String> bookIdList = new ArrayList<>();


    public String getName() {
        return name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public int getMoney() {
        return money;
    }

    public List<String> getBookIdList() {
        return bookIdList;
    }
}
