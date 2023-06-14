package Test;

public class Test {
    public static void main(String[] args) {
        for (int i = 1; i < 5; i++) {
            String sql = String.format("Insert Into JDBCTest values('컴소과%d',%d,'자바',%d)", i, i, (i+90));
            System.out.println(sql);
        }
    }
}
