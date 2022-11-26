public class Room {

    private String size;
    private int charge;

    String getSize() {
        return size;
    }

    int getCharge() {
        return charge;
    }

    // Room의 생성자
    Room(String size, int charge){
        this.size = size;
        this.charge = charge;
    }

}
