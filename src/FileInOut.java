import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

class FileInOut {
    String data = "C:\\Users\\Bo\\Desktop\\Test\\test.txt";
    ArrayList<String> list = new ArrayList<>();

    Scanner in = new Scanner(System.in);

    public void readList() { // 파일 전체 읽어서 list에 저장
        try (BufferedReader br = new BufferedReader(new FileReader(data));){
            String line = br.readLine();

			while (line != null) {
                list.add(line);
                line = br.readLine();
            }
            printToken();
        } catch (IOException e) {
            // TODO: handle exception
        }
        System.out.println("읽어오기 완료");
    }

    public void printToken() { // list를 토근으로 나눠 출력
        StringTokenizer st;

        for (int i = 0; i < list.size(); i++) {
            st = new StringTokenizer(list.get(i), " ");

            while (st.hasMoreTokens()) {
                System.out.print("이름 : " + st.nextToken());
                System.out.print("\t 나이 : " + st.nextToken() + "\n");
            }
        } // for
    }

    public void Tablewriter() { // DB 테이블 값 추가 (저장)
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(data));){
            String s = "abcdefg";   //출력할 문자열
            bw.write(s+"\n");   //버퍼에 있는 값 전부 출력
            bw.flush();   //남아있는 데이터를 모두 출력시킴
        } catch (IOException e) {
            // TODO: handle exception
        }

        System.out.println("쓰기 완료");

    }

    public static void main(String[] args) {
        FileInOut f = new FileInOut();
        f.readList();
    }
}
