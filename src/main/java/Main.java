public class Main {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        HotelConsole hotelConsole = new HotelConsole(new HotelService(hotel), new GuestService(hotel));
        hotelConsole.startConsole();
    }
}

