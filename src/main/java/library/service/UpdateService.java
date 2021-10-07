package library.service;

import database.PostgresqlAccess;
import exception.DateException;
import exception.NumException;
import library.ui.EtcUi;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateService {
    EtcUi etcUi = new EtcUi();

    public void updateBookBirth() throws NumException, DateException {

        System.out.print("갱신할 책의 번호를 입력하세요: ");
        int bookNum = etcUi.inputNum();
        System.out.print("갱신할 출판년도를 입력해주세요: ");
        Date bookBirth = etcUi.inputDate();
        String query = "UPDATE 도서목록 SET 책출판년도=? WHERE 책번호 = ?";
        try (Connection conn = PostgresqlAccess.setConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)
        ) {
            conn.setAutoCommit(false);
            pstmt.setDate(1, bookBirth);
            pstmt.setInt(2, bookNum);
            pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(bookNum + "번의 책이 존재하지 않습니다.");
        }
        System.out.println(bookNum + "번 책의 출판년도가 갱신되었습니다.");
    } // 책 출판년도 갱신

    public void borrowBook(String phoneNum) throws NumException {
        SelectService selectService = new SelectService(); // 책의 존재 여부 확인을 위함
        InsertService insertService = new InsertService(); // 대여 테이블의 튜플 추가를 위함

        System.out.print("빌리고 싶은 책의 번호를 입력해주세요: ");
        int bookNum = etcUi.inputNum();

        if (!selectService.isBookExist(bookNum)) {
            System.out.println("책이 없습니다!");
            return;
        }

        // 도서목록의 책 대출가능여부를 false로
        updateBookBorrowFalse(bookNum);

        // 대여 테이블에 대출여부 추가
        insertService.addBorrowInfo(bookNum, phoneNum);

        System.out.println(bookNum + "번 책을 빌렸습니다.");
    }

    public void returnBook(String phoneNum) throws NumException {
        SelectService selectService = new SelectService();
        DeleteService deleteService = new DeleteService();

        System.out.print("반납할 책의 번호를 입력해주세요: ");
        int bookNum = etcUi.inputNum();

        if (!selectService.isBorrowed(bookNum, phoneNum)) {
            System.out.println("반납 대상이 아닙니다.");
            return;
        }

        updateBookBorrowTrue(bookNum);

        deleteService.returnBook(bookNum);

        System.out.println(bookNum + "번 책을 반납했습니다.");
    }

    private void updateBookBorrowFalse(int bookNum) {
        String query = "UPDATE 도서목록 SET 책대출가능여부=false WHERE 책번호=?";
        try (Connection conn = PostgresqlAccess.setConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)
        ) {
            conn.setAutoCommit(false);
            pstmt.setInt(1, bookNum);
            pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateBookBorrowTrue(int bookNum) {
        String query = "UPDATE 도서목록 SET 책대출가능여부=true WHERE 책번호=?";
        try (Connection conn = PostgresqlAccess.setConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)
        ) {
            conn.setAutoCommit(false);
            pstmt.setInt(1, bookNum);
            pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
