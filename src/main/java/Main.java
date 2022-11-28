public class Main {
    public static void main(String[] args) {
        Hotel Hotel = new Hotel();
        HotelConsole hotelConsole = new HotelConsole(new HotelService(Hotel), new GuestService(Hotel));
        hotelConsole.startConsole();
    }
}

