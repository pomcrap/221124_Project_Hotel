import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

//삭제하려면 뭘 해야 할까?
// 1. 삭제할 항목 찾기
// 1.1 값이 틀리면 반환?
// 2. 해당 항목에 대한 권한
// 3. 해당 항목에 관한 모든 리스트 삭제
// 다른 값에 영향을 주지 않게 주의

// 1. 삭제할 항목의 id를 입력받아 bookId 값과 일치하지 않으면 재확인
// 2. 맞으면 bookIdList의 항목을 삭제?


public class GuestService {
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


    }
    }


