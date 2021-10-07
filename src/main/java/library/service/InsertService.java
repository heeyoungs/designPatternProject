package library.service;

import database.PostgresqlAccess;
import exception.DateException;
import exception.NumException;
import library.ui.EtcUi;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertService {
    EtcUi etcUi = new EtcUi();

    public void joinUser(String phoneNumber, String userName) {
        final String query = "INSERT INTO 손님 VALUES (?,?)";
        try (Connection connection = PostgresqlAccess.setConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            connection.setAutoCommit(false);

            pstmt.setString(1, phoneNumber);
            pstmt.setString(2, userName);
            pstmt.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            System.out.println("이미 등록된 전화번호 입니다.");
            return;
        }
        System.out.println("등록이 완료되었습니다.");
    } // 회원 등록하기

    public void addBook() throws NumException, DateException {
        System.out.println("추가할 책의 정보를 입력해주세요.");

        System.out.print("책 번호: ");
        int bookNum = etcUi.inputNum();
        System.out.print("책 이름: ");
        String bookName = etcUi.inputString();
        System.out.print("책 작가: ");
        String bookWriter = etcUi.inputString();
        System.out.print("책 페이지: ");
        int bookPage = etcUi.inputNum();
        System.out.print("책 출판년도: ");
        Date bookBirth = etcUi.inputDate();

        final String query = "INSERT INTO 도서목록 VALUES (?,?,?,?,?,?)";
        try (Connection connection = PostgresqlAccess.setConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            connection.setAutoCommit(false);

            pstmt.setInt(1, bookNum);
            pstmt.setString(2, bookName);
            pstmt.setString(3, bookWriter);
            pstmt.setInt(4, bookPage);
            pstmt.setDate(5, bookBirth);
            pstmt.setBoolean(6, true);
            pstmt.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            System.out.println(bookNum + "번의 책은 이미 있습니다.");
            e.printStackTrace();
        }
        System.out.println(bookNum + "번 책이 추가되었습니다.");
    } // 책 추가하기

    public void addBorrowInfo(int bookNum, String phoneNum) {
        final String query = "INSERT INTO 대여 VALUES (?,?)";
        try (Connection connection = PostgresqlAccess.setConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            connection.setAutoCommit(false);

            pstmt.setInt(1, bookNum);
            pstmt.setString(2, phoneNum);
            pstmt.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } // 대여 테이블에 정보 추가
}
