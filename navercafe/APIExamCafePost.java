package navercafe;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


public class APIExamCafePost {


    public static void main(String[] args) {
        String token = "YOUR_ACCESS_TOKEN";// 네이버 로그인 접근 토큰;
        String header = "Bearer " + token; // Bearer 다음에 공백 추가
        try {
            String clubid = "CAFE_ID";// 카페의 고유 ID값
            String menuid = "MENU_ID"; // 카페 게시판 id (상품게시판은 입력 불가)
            String apiURL = "https://openapi.naver.com/v1/cafe/"+ clubid+"/menu/" + menuid + "/articles";
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Authorization", header);
            // post request
            // 해당 string은 UTF-8로 encode 후 MS949로 재 encode를 수행한 값
            String subject = URLEncoder.encode(URLEncoder.encode("카페 가입 인사", "UTF-8"), "MS949");;
            String content = URLEncoder.encode(URLEncoder.encode("카페 가입 인사 드립니다 by Cafe API", "UTF-8"), "MS949");
            String postParams = "subject="+subject + "&content="+ content;
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(postParams);
            wr.flush();
            wr.close();
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            System.out.println(response.toString());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}