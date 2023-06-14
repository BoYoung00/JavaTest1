package Test;

import java.sql.SQLException;
import java.util.Scanner;

public class MenuTest {
    Scanner in = new Scanner(System.in);
    DaoTest dao;

    public MenuTest(DaoTest dao) {
        this.dao = dao;
    }

    public int TandM() {
        System.out.println("-----------------------");
        System.out.println("1. 테이블 관련");
        System.out.println("2. 메모장 관련");
        System.out.printf("동작 : ");
        return in.nextInt();
    }

    public void TableMenu() throws SQLException { // 테이블 관련 메뉴
        System.out.println("1. 추가 \n 2. 삭제 \n 3. 출력");
        int menu = in.nextInt();

        switch (menu) {
            case 1:
                dao.insert(); break;
            case 2:
                break;
            case 3:
                System.out.println("1. 전체 출력");
                System.out.println("2. 메모장 관련");
                System.out.printf("동작 : ");
        }
    }



}
