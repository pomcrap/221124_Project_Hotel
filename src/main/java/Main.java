public class Main {
    public static void main(String[] args) {
        HotelConsole hotelConsole = new HotelConsole(new HotelService(), new GuestService());
        hotelConsole.startConsole();
    }
}

