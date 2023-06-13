import java.sql.SQLException;
import java.util.Scanner;

class MainTest {
    public static void main(String[] args) {
        DaoTest dao = null;
        FileInOut f = new FileInOut();
        Scanner in = new Scanner(System.in);

        try {
            dao = new DaoTest();
            //dao.createTable();

            while (true) {
                System.out.println("-----------------------------");
                System.out.println("1. 데이터 추가 \t 2. 모두 출력 \t 3. 선택 출력");
                System.out.println("4. 선택 삭제 \t 5. 값 메모장에 저장 \t 6. 메모장 출력");
                System.out.print("(0. 종료) 동작 : ");
                int menu = in.nextInt();

                switch (menu) {
                    case 0: return;
                    case 1: dao.insert(); break;
                    case 2: dao.selectAll(); break;
                    case 3: dao.select(); break;
                    case 4: dao.delete(); break;
                    case 5: dao.saveTable(); break;
                    case 6: f.readList(); break;
                }
            } // while

        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            try {
                dao.dbClose();
            } catch (SQLException e2) {}
            System.out.println("프로그램 종료");
        } //finally
    }
}
