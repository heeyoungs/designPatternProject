package library.service;

import database.PostgresqlAccess;
import exception.InputException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class DeleteService {

    private Scanner sc = new Scanner(System.in);

    public void removeUser(String phoneNum) throws InputException{

        System.out.print("탈퇴하려면 전화번호를 한 번 더 입력해주세요! : ");
        String rePhoneNum = sc.next();

        if  (phoneNum.equals(rePhoneNum)){
            System.out.println("회원 탈퇴됩니다.");
        }else{
            System.out.println("초기화면으로 돌아갑니다.");
        }

        String query = "DELETE FROM 손님 WHERE 전화번호=?";
        try (Connection conn = PostgresqlAccess.setConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
        ) {
            conn.setAutoCommit(false);
            pstmt.setString(1, phoneNum);
            pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeBook() throws InputException {
        SelectService selectService = new SelectService();

        System.out.print("지울 책의 번호를 입력해주세요: ");
        int bookNum;
        try {
            bookNum = sc.nextInt();
        }catch (Exception e){
            throw new InputException("숫자를 입력안함.");
        }

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
    }
}
