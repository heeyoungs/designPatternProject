package library.service.ownerservice;

import exception.InputException;
import io.wisoft.jdbc.PostgresqlAccess;
import library.action.Action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.Date;

public class AddBookByOwner implements Action {

    private Scanner sc = new Scanner(System.in);

    @Override
    public void actionBook() throws InputException {
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
        }catch (Exception e){
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
            pstmt.setBoolean(6,true);
            pstmt.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            System.out.println(bookNum +"번의 책은 이미 있습니다.");
            e.printStackTrace();
        }
        System.out.println(bookNum + "번 책이 추가되었습니다.");
    }
}
