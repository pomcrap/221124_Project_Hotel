import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GuestService {
    private final Hotel hotel;  //Hotel class 의 인스턴스 선언.

    public GuestService(Hotel hotel) {
        this.hotel = hotel;
    }

    void findBookByBookId() {
        for (Book book : hotel.getTotalBookList()) {
            if (book.getGuest().getName().equals(book.getBookId())) {
                System.out.println(
                        book.getBookDate() + ", " + book.getRoom() + "," + book.getBookId() + ", " + book.getGuest());
            }

        } //id로 예약조회
    }

    //        /**
//         * 궁준님 테스트용 코드 그대로 있습니다.
//         */
//        void cancelBook () {
////    public static void main(String[] args) {  //테스트용
//
//            List<Integer> list = new ArrayList<Integer>(Arrays.asList(5, 4, 3, 2, 1)); //테스트용 리스트
//
//
//            String UUID = "1234"; //임시 패스워드 값. Book에서 UUID값 가져오기?
//            String Id;             //유저가 입력할 아이디 값
//
//
//            Scanner scanId = new Scanner(System.in); //입력
//            do {
//                System.out.println("id 입력 : ");
//                Id = scanId.next();
//                if (!Id.equals(UUID)) {
//                    System.out.println("잘못된 입력");
//                    System.out.println("1.백 2.재시도");
//                    Scanner scan = new Scanner(System.in);
//                    int fork = scan.nextInt();
//                    if (fork == 1) {
//                        return;
//                    }
//                } else {
//                    list.remove(1); //값 삭제 -> Book list 가져와서 삭제?
//
//                    System.out.println(list); //결과 테스트
//                    break;
//                }
//
//
//                while (true) ;
//
//
//            }//예약 취소
    public List<Book> getMyBookList(Guest guest) {
        List<Book> result = new ArrayList<>();

        for (Book book : hotel.getTotalBookList()) {
            if (book.getGuest().getName().equals(guest.getName()) && book.getGuest().getPhoneNum().equals(guest.getPhoneNum())) {
                result.add(book);
            }
        }
        return result;
    } //당사자 예약리스트 조회

    void bookRoom(Room room, Guest guest, LocalDateTime date) { ///여기에 함수인수 뭐지..?) { //이게맞아? 이거야..????
        if (room.getCharge() > guest.getMoney()) {
            System.out.println("소지금이 부족합니다.");
        }
        String bookId = UUID.randomUUID().toString(); //랜덤UUID생성

        Book book = new Book(room, bookId, guest, date);
        hotel.earnIncome(room.getCharge());
        guest.useMoney(room.getCharge());

        //생성한 book인스턴스를 Hotel의 totalBookList에 넣기
        hotel.getTotalBookList().add(book); //이게..get으로하면되나?

        //새 book을 넣은 totalBookList를 날짜순 정렬하기.
        // 1) 그냥 소팅이 안되니까, 다꺼내서 정렬하고, 다시넣기
        // 2) 그냥 그자체 소팅하는법 찾기 [ v 도전]
        // Book 클래스에 compareable을 implements 했습니다. 동작확인필요.
        Collections.sort(hotel.getTotalBookList());

        // guest의 bookIdList 에 지금 생성한 book인스턴스의 bookID 넣기
        guest.getBookIdList().add(bookId);
        System.out.println("예약완료");

        //while문 끝


    } //방예약 메소드

    void createGuest() {

    }//고객정보 생성메소드

    //예약가능한방 서치 후 보여주는 메소드
    List<Room> searchBookableRoom(LocalDateTime date) {
        List<Room> bookedRoomList = new ArrayList<>(); // date 날짜에 예약된 방을 담는 리스트
        List<Room> bookableRoomList = new ArrayList<>(); // date 날짜에 예약가능한 방을 담는 리스트
        List<Book> totalBookList = hotel.getTotalBookList();

        for (Book book : totalBookList) {
            if (book.getBookDate().isEqual(date)) {
                String matchSize = book.getRoom().getSize();
                int matchCharge = book.getRoom().getCharge();
                Room bookedRoom = new Room(matchSize, matchCharge);

                bookedRoomList.add(bookedRoom); // 예약된 방을 bookeRoomList에 담는다.

            }
        }
        if (bookedRoomList.isEmpty()) {
            return hotel.getRooms(); // bookedRoomList가 빈 경우 그냥 rooms를 출력 (이게 없어서그랬어!)
        }
        // bookedRoomList가 비어있지 않은 경우
        bookableRoomList = new ArrayList<>(hotel.getRooms());
        for (Room value : bookedRoomList) {
            for (Room room : bookableRoomList) {
                if (room.getSize().equals(value.getSize())) {
                    bookableRoomList.remove(room);
                    break;
                }
            }
        }

        return bookableRoomList;


    }//searchBookableRoom() 끝
}// GuestService 클래스의 끝

