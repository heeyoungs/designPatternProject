package library.service;

import database.PostgresqlAccess;
import exception.InputException;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class UpdateService {
    private Scanner sc = new Scanner(System.in);

    public void updateBookBirth() throws InputException {
        int bookNum;
        Date bookBirth;
        try {
            System.out.print("갱신할 책의 번호를 입력하세요: ");
            bookNum = sc.nextInt();
            System.out.print("갱신할 출판년도를 입력해주세요: ");
            bookBirth = Date.valueOf(sc.next());
        }catch (Exception e){
            throw new InputException("잘못된 입력값이 들어옴.");
        }
        String query = "UPDATE 도서목록 SET 책출판년도=? WHERE 책번호 = ?";
        try (Connection conn = PostgresqlAccess.setConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)
        ) {
            conn.setAutoCommit(false);
            pstmt.setDate(1, bookBirth);
            pstmt.setInt(2,bookNum);
            pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(bookNum + "번의 책이 존재하지 않습니다.");
        }
        System.out.println(bookNum + "번 책의 출판년도가 갱신되었습니다.");
    }

    public void borrowBook(String phoneNum) throws InputException {
        SelectService selectService = new SelectService(); // 책의 존재 여부 확인을 위함
        InsertService insertService = new InsertService(); // 대여 테이블의 튜플 추가를 위함

        System.out.print("빌리고 싶은 책의 번호를 입력해주세요: ");
        int bookNum;
        try {
            bookNum = sc.nextInt();
        }catch (Exception e){
            throw new InputException("숫자를 입력안함.");
        }

        if (!selectService.isBookExist(bookNum)) {
            System.out.println("책이 없습니다!");
            return;
        }

        // 도서목록의 책 대출가능여부를 false로
        String query = "UPDATE 도서목록 SET 책대출가능여부=false WHERE 책번호=?";
        try (Connection conn = PostgresqlAccess.setConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)
        ) {
            conn.setAutoCommit(false);
            pstmt.setInt(1,bookNum);
            pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 대여 테이블에 대출여부 추가
        insertService.addBorrowInfo(bookNum,phoneNum);

        System.out.println(bookNum + "번 책을 빌렸습니다.");
    }

    public void returnBook(String phoneNum) throws InputException {
        SelectService selectService = new SelectService();
        DeleteService deleteService = new DeleteService();

        System.out.print("반납할 책의 번호를 입력해주세요: ");
        int bookNum;
        try {
            bookNum = sc.nextInt();
        }catch (Exception e){
            throw new InputException("숫자를 입력안함.");
        }

        if (!selectService.isBorrow(bookNum,phoneNum)){
            System.out.println("반납 대상이 아닙니다.");
            return;
        }

        String query = "UPDATE 도서목록 SET 책대출가능여부=true WHERE 책번호=?";
        try (Connection conn = PostgresqlAccess.setConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)
        ) {
            conn.setAutoCommit(false);
            pstmt.setInt(1,bookNum);
            pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        deleteService.returnBook(bookNum);

        System.out.println(bookNum + "번 책을 반납했습니다.");
    }
}
