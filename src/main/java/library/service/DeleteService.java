package library.service;

import database.PostgresqlAccess;
import exception.InputException;
import library.ui.EtcUi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class DeleteService {

    public void removeBook() throws InputException {
        SelectService selectService = new SelectService();
        EtcUi etcUi = new EtcUi();

        System.out.print("지울 책의 번호를 입력해주세요: ");
        int bookNum = etcUi.inputNum();

        if (!selectService.isBookExist(bookNum)){
            System.out.println(bookNum + "번의 책이 존재하지 않습니다.");
            return;
        }

        String query = "DELETE FROM 도서목록 WHERE 책번호=?";
        try (Connection conn = PostgresqlAccess.setConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
        ) {
            conn.setAutoCommit(false);
            pstmt.setInt(1, bookNum);
            pstmt.executeUpdate();
            conn.commit();
            System.out.println(bookNum + "번 책이 제거되었습니다.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void returnBook(int bookNum) {
        String query = "DELETE FROM 대여 WHERE 책번호=?";
        try (Connection conn = PostgresqlAccess.setConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
        ) {
            conn.setAutoCommit(false);
            pstmt.setInt(1, bookNum);
            pstmt.executeUpdate();
            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    } // 책 반납하기
}
