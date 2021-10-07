package library.service;

import database.PostgresqlAccess;
import exception.NumException;
import library.ui.EtcUi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteService {
    EtcUi etcUi = new EtcUi();

    public void removeBook() throws NumException {
        SelectService selectService = new SelectService();

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
    } // 책 제거하기

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
