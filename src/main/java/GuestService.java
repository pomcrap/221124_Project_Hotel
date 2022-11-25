import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.UUID;

public class GuestService {
    Hotel hotel = new Hotel();  //Hotel class 의 인스턴스 선언.
//    Book book = new Book();  이유는 모르겠지만 생성 안해도 사용이 가능하다?
    Guest guest = new Guest();
    void findBookByBookId() {

    } //id로 예약조회

    void cancelBook() {

    } //예약 취소

    void getMyBookList() {
        for (Book book : hotel.getTotalBookList()){

            if(guest.getName()==0) {
                System.out.println(book.getBookDate() + ", " + book.getRoom() + "," + book.getBookId() + ", " + book.getGuest());
            }
        }


    } //당사자 예약리스트 조회

    void bookRoom(Guest guest, Hotel hotel, LocalDateTime date) { ///여기에 함수인수 뭐지..?) { //이게맞아? 이거야..????
        while (true) {
            // book 인스턴스에 필드값 넣기 - room
            Room room = null; // this.room; //받아온값.

            // book 인스턴스에 필드값 넣기 - bookId
            String bookId = UUID.randomUUID().toString(); //랜덤UUID생성

            // Book의 객체 생성
            Book book = new Book(room, bookId, guest, date);

            //생성한 book인스턴스를 Hotel의 totalBookList에 넣기
            hotel.getTotalBookList().add(book); //이게..get으로하면되나?

            //새 book을 넣은 totalBookList를 날짜순 정렬하기.
            // 1) 그냥 소팅이 안되니까, 다꺼내서 정렬하고, 다시넣기
            // 2) 그냥 그자체 소팅하는법 찾기 [ v 도전]
            // Book 클래스에 compareable을 implements 했습니다. 동작확인필요.
            Collections.sort(hotel.getTotalBookList());



        }//while문 끝


    } //방예약 메소드

    void createGuest() {

    }//고객정보 생성메소드


}// GuestService 클래스의 끝

