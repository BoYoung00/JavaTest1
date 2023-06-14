package JavaTest;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

class SubjectsDao {
    private Connection conn = null; // DB 연결용
    private Statement stmt = null; // SQL 구문을 실행하는 역할
    private ResultSet rs = null; // 값 넣는 곳
    private PreparedStatement pstmt = null; // Statement 기능 향상 (? 쓸 수 있게 해줌)
    private Scanner in = new Scanner(System.in);
    
    public SubjectsDao() throws ClassNotFoundException, SQLException { // 드라이버 로드
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/database0614"
                + "?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false", "root", "rlaqhdud2@");
        stmt = conn.createStatement();
    }

    // 테이블 생성
    public void createTable() throws SQLException {
        stmt.executeUpdate("create table javaTest(department varchar(50), grade int, subject varchar(50), credit int)");
        String sql = null;
        for (int i = 1; i < 5; i++) {
            sql = String.format("Insert Into javaTest values('컴소과%d',%d,'자바',%d)", i, i, (i+90));
            stmt.executeUpdate(sql);
            sql = null;
        }
        System.out.println("javaTest 테이블 생성 완료");
    }


    public List<String> getKey() throws SQLException {
        LinkedList list = new LinkedList();
        rs = stmt.executeQuery("select distinct department from javaTest");
        while (rs.next()) {
            list.add(rs.getString(1));
        }
        return list;
    }

    public List<Subject> getSubjectList(String department) throws SQLException {
        int gr; String sub; int cre;
        LinkedList<Subject> list = new LinkedList<>();

        pstmt = conn.prepareStatement("select grade, subject, credit from javaTest where department = ?");
        pstmt.setString(1, department);

        rs = pstmt.executeQuery();

        boolean found = false;
        while (rs.next()) {
            gr = rs.getInt("grade");
            sub = rs.getString("subject");
            cre = rs.getInt("credit");

            list.add(new Subject(gr, sub, cre));
            found = true;
        }

        if (found != true) {
            System.out.println("일치하는 정보가 없습니다.");
            return null;
        }

        return list;
    }

    public void dbClose() throws SQLException {
        if (conn != null)
            conn.close();

        if (stmt != null)
            stmt.close();

        if (rs != null)
            rs.close();

        if (pstmt != null)
            rs.close();
    }


}
