import java.util.ArrayList;
import java.util.List;

public class Guest {

    private String name;
    private String phoneNum;
    private int money;
    private List<String> bookIdList = new ArrayList<>();

    public Guest(String name, String phoneNum, int money, List<String> bookIdList) {
        this.name = name;
        this.phoneNum = phoneNum;
        this.money = money;
        this.bookIdList = bookIdList;
    }

    public Guest() {

    } //Guest 생성자

    public static GuestBuilder builder() {
        return new GuestBuilder();
    }

    public static class GuestBuilder {
        private String name;
        private String phoneNum;
        private int money;
        private List<String> bookIdList = new ArrayList<>();

        public GuestBuilder name(String name) {
            this.name = name;
            return this;
        }

        public GuestBuilder phoneNum(String phoneNum) {
            this.phoneNum = phoneNum;
            return this;
        }
        public GuestBuilder money(int money) {
            this.money = money;
            return this;
        }
        public GuestBuilder bookIdList(List<String> bookIdList) {
            this.bookIdList = bookIdList;
            return this;
        }

        public Guest build() {
            return new Guest(this.name, this.phoneNum, this.money, this.bookIdList);
        }
    }

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
