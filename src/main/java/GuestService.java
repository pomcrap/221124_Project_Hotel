
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

//삭제하려면 뭘 해야 할까?
// 1. 삭제할 항목 찾기
// 1.1 값이 틀리면 반환?
// 2. 해당 항목에 대한 권한
// 3. 해당 항목에 관한 모든 리스트 삭제
// 다른 값에 영향을 주지 않게 주의

// 1. 삭제할 항목의 id를 입력받아 bookId 값과 일치하지 않으면 재확인
// 2. 맞으면 bookIdList의 항목을 삭제?


public class GuestService {
    //        void cancelBook () {
    public static void main(String[] args) {  //테스트용

        List<Integer> list = new ArrayList<Integer>(Arrays.asList(5, 4, 3, 2, 1));


        String bookId = "1234"; //임시 패스워드 값
        String uuid;        //유저가 입력할 패스워드 값

//        System.out.println("1.리스트 2.삭송이");
//        Scanner scanner = new Scanner(System.in);
//        int answer = scanner.nextInt();
//
//        switch (answer) {
//            case 1:
//                System.out.println(list);
//                return;
//
//            case 2:
                Scanner scan = new Scanner(System.in); //입력
                do {
                    System.out.println("id 입력 : ");
                    uuid = scan.next();
                    if (!uuid.equals(bookId))
                        System.out.println("잘못된 입력");
                     else {
                        list.remove(1); //값 삭제

                        System.out.println(list); //결과 테스트
                        break;
                    }

                }
                while (true);

 //       }

    }
    }


