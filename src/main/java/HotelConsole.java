import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

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
        System.out.print("핸드폰번호( ex) 010-0000-0000 ) :");
        String phoneNumber = sc.nextLine();
        /** 여가   */
        boolean collectPhoneNumber = checkPhoneNumber(phoneNumber);
        while (!collectPhoneNumber) {
            System.out.println("양식에 맞지 않습니다. 다시 입력해주세요.");
            System.out.print("핸드폰번호( ex) 010-0000-0000 ) :");
            phoneNumber = sc.nextLine();
            collectPhoneNumber = checkPhoneNumber(phoneNumber);
        }
        System.out.print("얼마있냐?: ");
        int money = sc.nextInt();
        sc.nextLine();
        Guest guest = this.guestService.createGuest(name, phoneNumber, money);

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
                    List<Book> books = this.showBooks(guest);
                    System.out.println("예약 세부사항 보기");
                    int bookNo = sc.nextInt() - 1;
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
                            this.guestService.cancelBook(book, guest);
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
        Book target = this.guestService.findBookByBookId(book.getBookId());
        target.printDetailInfo();
    }

    private List<Book> showBooks(Guest guest) {
        /**   여기    */
        List<Book> books = this.guestService.getMyBookList(guest);
        if (books.isEmpty()) {
            System.out.println("예약리스트가 존재하지 않습니다");
            return null;
        }

        for (Book book : books) {
            System.out.println("==== 예약 정보 ====");
            book.printInfo();
        }

        return books;
    }

    private void showRooms(Guest guest) {
        Scanner sc = new Scanner(System.in);
        int cmd; // 예약하기, 뒤로가기, 닫기 선택용 변수
        System.out.println("체크인 시간은 오후 3시입니다.");
        System.out.println("예약을 원하는 날짜를 입력해주세여. ex) 2022-11-25");
        String dateStr = sc.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate checkInDate = LocalDate.parse(dateStr, formatter);
        boolean back = false;
        while (!back) {
            // 날짜가 유효한지 체크(프로그램 실행일 기준, 내일부터 예약가능)
            boolean collectBookDate = checkBookDate(checkInDate);
            while (!collectBookDate) {
                System.out.println("예약날짜는 내일부터만 예약 가능합니다.");
                System.out.println("예약을 원하는 날짜를 입력해주세여. ex) 2022-11-25");
                dateStr = sc.nextLine();
                checkInDate = LocalDate.parse(dateStr, formatter);
                collectBookDate = checkBookDate(checkInDate);
            }
            LocalTime checkInTime = LocalTime.of(15, 0, 0);
            LocalDateTime date = LocalDateTime.of(checkInDate, checkInTime);
            // 예약가능한 방 보여주는 메소드 (1. 방목록 조회 2. 해당방 정보조회)
            System.out.println("==== " + date + " 예약 가능한 방 ==== ");
            System.out.println(date);
            List<Room> bookableRoomList = guestService.searchBookableRoom(date);
            for (int i = 0; i < bookableRoomList.size(); i++) {
                String size = bookableRoomList.get(i).getSize();
                System.out.printf("%d.%s 사이즈\n", i + 1, size);
            }
            System.out.println("자세히 보고싶은 방을 선택하세요.");
            int no = sc.nextInt() - 1;
            String noSize = bookableRoomList.get(no).getSize();
            int noCharge = bookableRoomList.get(no).getCharge();
            System.out.printf("\n%s 사이즈, %d원\n", noSize, noCharge);
            Room wantRoom = new Room(noSize, noCharge);
            System.out.println("1. 예약하기 | 2. 뒤로가기 | 3. 닫기");
            cmd = sc.nextInt();
            sc.nextLine();
            switch (cmd) {
                case 1:
                    this.book(wantRoom, guest, date);
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


    private void book(Room room, Guest guest, LocalDateTime date) {
        this.guestService.bookRoom(room, guest, date);
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
                    List<Book> totalBookList = this.hotelService.getTotalBookList();
                    for (Book book: totalBookList) {
                        System.out.println("==== 예약 목록 ====");
                        book.printDetailInfo();
                    }
                    break;
                case 2:
                    int income = this.hotelService.getIncome();
                    System.out.printf("총 수입은%d 입니다.\n", income);
                    break;
                case 3:
                    end = true;
                    break;
            }
        } while (!end);
    }

    private boolean checkPhoneNumber(String phoneNumber) {
        String patternPhoneNumber = "^010-\\d{4}-\\d{4}$";
        return Pattern.matches(patternPhoneNumber, phoneNumber);
    }

    private boolean checkBookDate(LocalDate date) {
        LocalDate today = LocalDate.now();
        return date.isAfter(today);
    }
}

