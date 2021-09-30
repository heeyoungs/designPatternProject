package library.service;

import io.wisoft.jdbc.PostgresqlAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IsBook {
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
            return false;
        }
        return false;
    }

    public boolean isBorrow(int bookNum) {
        final String query = "SELECT 책번호 FROM 빌려간책목록 WHERE 책번호=?";
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
            return false;
        }
        return false;
    }
}
