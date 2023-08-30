package navercafe;

import java.io.*;
import java.net.URLEncoder;
import java.util.List;


public class APIExamCafePostMultipart {


    public static void main(String[] args) {


        String token = "AAAANjARrFheyb3+6rEc5X6AebqUAGt6+r0Hz036PFCe8PxnOz63WSp+haYsyEizr7QBHyfp789V4tGtrrPC+L/kAv8=";//애플리케이션 클라이언트 아이디값";
        String header = "Bearer " + token; // Bearer 다음에 공백 추가
        try {
            // api url 설정
            String clubid = "28339939";// 카페의 고유 ID값 https://cafe.naver.com/apiexam
            String menuid = "1"; // 카페 게시판 id
            String apiURL = "https://openapi.naver.com/v1/cafe/"+ clubid+"/menu/" + menuid + "/articles";
            MultipartUtil mu = new MultipartUtil(apiURL);
            // 접큰 토큰 헤더 추가
            mu.addHeaderField("Authorization", header);
            mu.readyToConnect();
            // cafe 글쓰기 필수 요청변수 subject 추가
            String subject = URLEncoder.encode("네이버 multi-part 이미지 첨부 테스트", "UTF-8");
            mu.addFormField("subject", subject);
            // cafe 글쓰기 필수 요청변수 content 추가
            String content = URLEncoder.encode("<font color='red'>multi-part</font>로 첨부한 글입니다. <br> 이미지 첨부 <br>", "UTF-8");
            mu.addFormField("content", content);


            // [시작] image 첨부 로직 - 필요시 이미지수 만큼 반복
            File uploadFile1 = new File("prince1.jpg");
            mu.addFilePart("0", uploadFile1);
            File uploadFile2 = new File("prince2.jpg");
            mu.addFilePart("0", uploadFile2);
            // [종료] 이미지 첨부 로직 - 필요시 이미지수 만큼 반복


            // HTTP 호출 결과 수신
            List response = mu.finish();
            System.out.println("SERVER REPLIED:");


            for (String line : response) {
                System.out.println(line);
            }


        } catch (Exception e) {
            System.out.println(e);
        }
    }
}