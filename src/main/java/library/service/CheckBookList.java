package library.service;

import io.wisoft.jdbc.PostgresqlAccess;
import library.action.Check;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckBookList implements Check {
    @Override
    public void checkBook() {
        System.out.println("도서목록을 보여줍니다.");
        final String query = "SELECT * FROM 도서목록";
        try (Connection connection = PostgresqlAccess.setConnection();
             PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery();) {
            while (rs.next()) {
                System.out.println("[책 번호] " + rs.getString(1));
                System.out.print("[책 이름] " + rs.getString(2) + "||");
                System.out.print("[책 작가] " + rs.getString(3) + "||");
                System.out.print("[책 페이지]" + rs.getString(4) + "||");
                System.out.print("[책 출판년도]" + rs.getString(5) + "||");
                System.out.println("[책 대출 가능여부]" + rs.getString(6));
            }
        } catch (SQLException sqex) {
            System.out.println("SQLException: " + sqex.getMessage());
            System.out.println("SQLState: " + sqex.getSQLState());
        }
    }

    public boolean isBook(int bookNum) {
        final String query = "SELECT 책대출가능여부 FROM 도서목록 WHERE 책번호=?";
        try (Connection connection = PostgresqlAccess.setConnection();
             PreparedStatement pstmt = connection.prepareStatement(query);
             ) {
            pstmt.setInt(1, bookNum);
            ResultSet rs = pstmt.executeQuery();

            rs.next();
            if (rs.getBoolean(1)) {
                return true;
            }
        } catch (SQLException sqex) {
            System.out.println("SQLException: " + sqex.getMessage());
            System.out.println("SQLState: " + sqex.getSQLState());
        }
        return false;
    }
}
