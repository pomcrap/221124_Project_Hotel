import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GuestService {
    Hotel hotel = new Hotel();  //Hotel class 의 인스턴스 선언.
//    Book book = new Book();  이유는 모르겠지만 생성 안해도 사용이 가능하다?

    void findBookByBookId() {

    } //id로 예약조회

    /**
     * 궁준님 테스트용 코드 그대로 있습니다.
     * */
    void cancelBook () {
//    public static void main(String[] args) {  //테스트용

        List<Integer> list = new ArrayList<Integer>(Arrays.asList(5, 4, 3, 2, 1)); //테스트용 리스트


        String UUID = "1234"; //임시 패스워드 값. Book에서 UUID값 가져오기?
        String Id;             //유저가 입력할 아이디 값


        Scanner scanId = new Scanner(System.in); //입력
        do {
            System.out.println("id 입력 : ");
            Id = scanId.next();
            if (!Id.equals(UUID)) {
                System.out.println("잘못된 입력");
                System.out.println("1.백 2.재시도");
                Scanner scan = new Scanner(System.in);
                int fork = scan.nextInt();
                if (fork == 1) {
                    return;
                }
            }else {
                list.remove(1); //값 삭제 -> Book list 가져와서 삭제?

                System.out.println(list); //결과 테스트
                break;
            }

        }
        while (true);


    }//예약 취소

    void getMyBookList() {
        for (Book book : hotel.getTotalBookList()){
//     getName() 은 String 이라서 == 로 비교가 불가능합니다. "동등성"과 "동일성"에 대해 검색해보세요.
//            if(guest.getName()==0) {
//                System.out.println(book.getBookDate() + ", " + book.getRoom() + "," + book.getBookId() + ", " + book.getGuest());
//            }
        }


    } //당사자 예약리스트 조회

    void bookRoom(Room room, Guest guest, LocalDateTime date) { ///여기에 함수인수 뭐지..?) { //이게맞아? 이거야..????
        while (true) {
            // book 인스턴스에 필드값 넣기
            // book 인스턴스에 넣을 필드값 room, guest, date 는 hotelconsle에서 받음.
            // book 인스턴스에 필드값 넣기 - bookId
            String bookId = UUID.randomUUID().toString(); //랜덤UUID생성

            // Book의 객체 생성, room의 charge 만큼 Hotel의 income 상승, guest의 money 하락.
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

        }//while문 끝


    } //방예약 메소드

    void createGuest() {

    }//고객정보 생성메소드


}// GuestService 클래스의 끝

