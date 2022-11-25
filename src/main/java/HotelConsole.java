import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class HotelConsole {
    private final HotelService hotelService;
    private final GuestService guestService;

    public HotelConsole(HotelService hotelService, GuestService guestService) {
        this.hotelService = hotelService;
        this.guestService = guestService;
    }

    public void startConsole() {
        boolean stop = false;
        while (!stop) {
            System.out.println("1. 관리자모드 | 2. 고객모드 | 3. 종료");
            Scanner sc = new Scanner(System.in);
            int mode = sc.nextInt();
            sc.nextLine();
            switch (mode) {
                case 1:
                    this.accessAdmin();
                    break;
                case 2:
                    this.accessGuest();
                    break;
                case 3:
                    System.out.println("실행이 종료됩니다");
                    stop = true;
                    break;
            }
        }
    }

    private void accessGuest() {
        Scanner sc = new Scanner(System.in);
        System.out.println("고객 정보를 입력해주세요");
        System.out.print("이름: ");
        String name = sc.nextLine();
        System.out.print("핸드폰번호: ");
        String phoneNumber = sc.nextLine();
        System.out.print("얼마있냐?: ");
        int money = sc.nextInt();
        sc.nextLine();
//        this.guestService.createGuest();
        Guest guest = Guest.builder()
                .name(name)
                .phoneNum(phoneNumber)
                .money(money)
                .build();
        boolean end = false;
        do {
            System.out.printf("환영합니다. %s고객님!\n", guest.getName());
            System.out.println("1. 방목록조회 | 2. 예약조회 | 3. 종료");
            int cmd = sc.nextInt();
            sc.nextLine();
            switch (cmd) {
                case 1:
                    this.showRooms(guest);
                    break;
                case 2:
                    List<Book> books = this.showBooks();
                    System.out.println("예약 세부사항 보기");
                    int bookNo = sc.nextInt();
                    sc.nextLine();
                    assert bookNo > 0;
                    assert bookNo <= books.size();
                    Book book = books.get(bookNo);
                    this.showBook(book);
                    System.out.println("1. 예약취소 | 2. 뒤로가기");
                    cmd = sc.nextInt();
                    sc.nextLine();
                    switch (cmd) {
                        case 1:
                            this.guestService.cancelBook();
                            break;
                        case 2:
                            break;
                    }
                    break;
                case 3:
                    System.out.printf("안녕히가세요 %s\n", guest.getName());
                    end = true;
                    break;
            }
        } while (!end);
    }

    private void showBook(Book book) {
//        Book book = this.guestService.findBookByBookId();
//        book을 출력
//         ex) 1. <uuid> <방 사이즈> <가격> <예약자이름> <번호>
//
    }

    private List<Book> showBooks() {
//                    List<Book> books = this.guestService.getMyBookList(guest);
//                    books == 나의 예약 리스트를 전부 출력
//                    ex) 1. <uuid> <방 사이즈>
//                    ex) 2. <uuid> <방 사이즈>
        return null;
    }

    private void showRooms(Guest guest) {
        Scanner sc = new Scanner(System.in);
        int cmd;
        System.out.println("예약을 원하는 날짜를 입력해주세여. ex) 2022-11-25");
        String dateStr = sc.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dateStr, formatter);
                    /*
                       위 날짜에 예약가능한 방을 조회 -
                       1. 방목록조회
                       2. 해당방의 정보 조회
                       3. 예약하기
                     */
//                                방 리스트를 보여줄 메소드 필요합니다.
        boolean back = false;
        while (!back) {
            System.out.println("==== "+ date +" ==== ");
//                                List<Room> rooms = this.hotelService.getRooms();

//                                 rooms 출력
            System.out.println("1. 추가정보 확인");
            int no = sc.nextInt();
            sc.nextLine();
//                                사용자가 방하나를 고르면 고른 번호 == no
                        Room room = null;
//                                Room room = this.hotelService.getRoom(no);
//                                 room 출력 size, 가격
            System.out.println("1. 예약하기 | 2. 뒤로가기 | 3. 닫기");
            cmd = sc.nextInt();
            sc.nextLine();
            switch (cmd) {
                case 1:
                    this.book(room, guest, date);
                    back = true;
                    break;
                case 2:
                    System.out.println("이전 화면으로 이동");
                    break;
                case 3:
                    back = true;
                    break;
            }
        }
    }

    private void book(Room room, Guest guest, LocalDate date) {
//        this.guestService.bookRoom(room, guest, date);
    }

    private void accessAdmin() {
        Scanner sc = new Scanner(System.in);
        System.out.println("==== 관리자모드 ====");
        boolean end = false;
        do {
            System.out.println("1. 예약목록조회 | 2. 자산조회 | 3. 종료");
            int cmd = sc.nextInt();
            sc.nextLine();
            switch (cmd) {
                case 1:
                    this.hotelService.getTotalBookList();
                    break;
                case 2:
//                                의논해보기
//                                this.hotelService.getIncome();
                    break;
                case 3:
                    end = true;
                    break;
            }
        } while (!end);
    }
}
