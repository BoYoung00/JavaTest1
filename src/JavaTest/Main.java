package JavaTest;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
            SubjectsDao dao = null;
            SearchSubject subject = null;
        try {
            dao = new SubjectsDao();
            //dao.createTable();
            subject = new SearchSubject(dao);
            subject.constructMap();
            subject.printValue("컴소과1");

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
