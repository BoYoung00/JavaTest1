package Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

class DaoTest {
    Connection conn = null; // DB 연결용
    Statement stmt = null; // SQL 구문을 실행하는 역할
    ResultSet rs = null; // 값 넣는 곳
    PreparedStatement pstmt = null; // Statement 기능 향상 (? 쓸 수 있게 해줌)
    Scanner in = new Scanner(System.in);

    // 드라이버 로드
    public DaoTest() throws ClassNotFoundException, SQLException { 
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/database0613"
                + "?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false", "root", "rlaqhdud2@");
        stmt = conn.createStatement();
    }

    // 테이블 생성
    public void createTable() throws SQLException { 
        stmt.executeUpdate("create table JDBCTest(name varchar(20), age int)");
        System.out.println("JDBCTest 테이블 생성 완료");
    }
    
    // 원하는 값 추가
    public void insert() throws SQLException { 
        System.out.println("---------정보 추가----------");
        System.out.print("이름 : ");
        String name = in.next();
        System.out.print("나이 : ");
        int age = in.nextInt();

        String sql = "Insert Into JDBCTest values(?,?)";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, name);
        pstmt.setInt(2, age);

        int result = pstmt.executeUpdate();

        if (result < 0)
            System.out.println("정보 입력이 잘못 되었습니다.");
        else
            System.out.println("추가 되었습니다.");

    }
    
    // 테이블 전체 출력
    public void selectAll() throws SQLException {
        rs = stmt.executeQuery("select * from JDBCTest");
        boolean found = false;
        while (rs.next()) {
            System.out.println("name : " + rs.getString(1) + " age : " + rs.getInt("age"));
            found = true;
        }

        if (found != true)
            System.out.println("데이터가 없습니다.");

    }

    // 일치하는 값 출력
    public void select() throws SQLException {
        System.out.println("---------검색 정보----------");
        System.out.print("이름 : ");
        String name = in.next();
        System.out.print("나이 : ");
        int age = in.nextInt();

        pstmt = conn.prepareStatement("select name, age from JDBCTest where name=? and age = ?");
        pstmt.setString(1, name);
        pstmt.setInt(2, age);

        rs = pstmt.executeQuery();
        boolean found = false;

        while (rs.next()) {
            System.out.println("name : " + rs.getString("name") + " age : " + rs.getInt(2));
            found = true;
        }

        if (found != true)
            System.out.println("일치하는 정보가 없습니다.");

    }

    // 삭제
    public void delete() throws SQLException {
        System.out.println("---------삭제 정보----------");
        System.out.print("이름 : ");
        String name = in.next();
        System.out.print("나이 : ");
        int age = in.nextInt();

        pstmt = conn.prepareStatement("delete from JDBCTest where name=? and age=?");
        pstmt.setString(1, name);
        pstmt.setInt(2, age);

        int result = pstmt.executeUpdate();

        System.out.println(result);

        if (result > 0)
            System.out.println("삭제되었습니다.");
        else
            System.out.println("일치하는 정보가 없습니다.");

    }

    // 테이블 메모장에 저장
    public void saveTable() {
        String data = "C:\\Users\\Bo\\Desktop\\Test\\test.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(data));) {
            String sql = "SELECT * FROM JDBCTest";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String name = rs.getString(1);
                int age = rs.getInt(2);

                writer.write(name + " " + age + "\n");
            }

            System.out.println("테이블이 test.txt에 저장되었습니다.");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
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
