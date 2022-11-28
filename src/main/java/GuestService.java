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
    void getMyBookList(Guest guest) {
        for (Book book : hotel.getTotalBookList()) {
//     getName() 은 String 이라서 == 로 비교가 불가능합니다. "동등성"과 "동일성"에 대해 검색해보세요.
            if (book.getGuest().getName().equals(guest.getName())) {
                System.out.println(book.getBookDate() + ", " + book.getRoom() + "," + book.getBookId() + ", " + book.getGuest());
            }
        }
    } //당사자 예약리스트 조회

    void bookRoom (Room room, Guest guest, LocalDateTime date){ ///여기에 함수인수 뭐지..?) { //이게맞아? 이거야..????
        if(room.getCharge() > guest.getMoney()){
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

        //while문 끝


    } //방예약 메소드

    void createGuest () {

    }//고객정보 생성메소드

    //예약가능한방 서치 후 보여주는 메소드
    List<Room> searchBookableRoom (LocalDateTime date){
        // 1. hotel.TotalBookList에서, date와 bookDate 값이 일치하는 book이 있는지 확인
        //1-1. date날짜에 예약된 방 리스트 / 예약가능한 방 리스트 두개 생성.
        //1-2. hotel의 TotalBookList의 복제 리스트 생성.
        List<Room> bookedRoomList = new ArrayList<>(); // date 날짜에 예약된 방을 담는 리스트
        List<Room> bookableRoomList = new ArrayList<>(); // date 날짜에 예약가능한 방을 담는 리스트
        List<Book> totalBookList = new ArrayList<>(hotel.getTotalBookList().size());
        Collections.copy(totalBookList, hotel.getTotalBookList());
        //1-3. totalbooklist 내용물 book을 for문 돌면서 매개변수 date와 bookDate가 일치하면 그 room을 bookedRoomList에 담기.
        for (int i = 0; i < totalBookList.size(); i++) {
            if (totalBookList.get(i).getBookDate() == date) {
                String matchSize = totalBookList.get(i).getRoom().getSize();
                int matchCharge = totalBookList.get(i).getRoom().getCharge();
                Room bookedRoom = new Room(matchSize, matchCharge);

                bookedRoomList.add(bookedRoom); // 예약된 방을 bookeRoomList에 담는다.

            }//if문 끝
        }//for문 끝
        // 3. hotel의 rooms 리스트의 내용물과 bookedRoomList 내용물 비교.
        // 4. hotel.rooms - bookedRoomList 일치하지 않는 room을 bookablelist 에 담음.
        for (int i = 0; i < hotel.getRooms().size(); i++) {
            for (int k = 0; k < bookedRoomList.size(); k++) {
                if (hotel.getRooms().get(i) != bookedRoomList.get(k)) {
                    String matchSize = hotel.getRooms().get(i).getSize();
                    int matchCharge = hotel.getRooms().get(i).getCharge();
                    Room bookableRoom = new Room(matchSize, matchCharge);

                    bookableRoomList.add(bookableRoom);
                }//if문 끝
            }//내부 for문 끝


        }//외부for문 끝
        return bookableRoomList;



    }//searchBookableRoom() 끝
}// GuestService 클래스의 끝

