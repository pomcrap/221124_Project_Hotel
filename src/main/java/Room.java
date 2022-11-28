public class Room {

    private String size;
    private int charge;

    private String detailInformation;

    String getSize() {
        return size;
    }

    int getCharge() {
        return charge;
    }

    String getDetailInformation() { return detailInformation;}

    // Room의 생성자
    Room(String size, int charge, String detailInformation){
        this.size = size;
        this.charge = charge;
        this.detailInformation = detailInformation;
    }

}
