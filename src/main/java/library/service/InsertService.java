package library.service;

import database.PostgresqlAccess;
import exception.InputException;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertService {

    private Scanner sc = new Scanner(System.in);

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
    }

    public void addBook() throws InputException {
        int bookNum, bookPage;
        String bookName, bookWriter;
        Date bookBirth;
        System.out.println("추가할 책의 정보를 입력해주세요.");

        try {
            System.out.print("책 번호: ");
            bookNum = sc.nextInt();
            System.out.print("책 이름: ");
            bookName = sc.next();
            System.out.print("책 작가: ");
            bookWriter = sc.next();
            System.out.print("책 페이지: ");
            bookPage = sc.nextInt();
            System.out.print("책 출판년도: ");
            bookBirth = Date.valueOf(sc.next());
        } catch (Exception e) {
            throw new InputException("잘못된 입력값이 들어옴.");
        }

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
    }

    public void addBorrowInfo(int bookNum, String phoneNum) {
        final String query = "INSERT INTO 대여 VALUES (?,?)";
        try (Connection connection = PostgresqlAccess.setConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            connection.setAutoCommit(false);

            pstmt.setInt(1, bookNum);
            pstmt.setString(2, phoneNum);
            pstmt.executeUpdate();
            connection.commit();
        }catch (SQLException e){
            e.printStackTrace();;
        }
    }
}
