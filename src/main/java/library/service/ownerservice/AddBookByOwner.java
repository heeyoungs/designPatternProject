package library.service.ownerservice;

import io.wisoft.jdbc.PostgresqlAccess;
import library.action.Add;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.Date;

public class AddBookByOwner implements Add {
    private Scanner sc = new Scanner(System.in);

    @Override
    public void addBook() {
        int bookNum = 0, bookPage = 0, bookInfo = 0;
        String bookName = null, bookWriter = null, bookBirth = null;
        System.out.println("추가할 책의 정보를 입력해주세요.");
        System.out.print("책 번호: ");
        bookNum = sc.nextInt();
        System.out.print("책 이름: ");
        bookName = sc.next();
        System.out.print("책 작가: ");
        bookWriter = sc.next();
        System.out.print("책 페이지: ");
        bookPage = sc.nextInt();
        System.out.print("책 출판년도: ");
        bookBirth = sc.next();

        final String query = "INSERT INTO 도서목록 VALUES (?,?,?,?,?,1)";
        try (Connection connection = PostgresqlAccess.setConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            connection.setAutoCommit(false);

            pstmt.setString(1, String.valueOf(bookNum));
            pstmt.setString(2, bookName);
            pstmt.setString(3, bookWriter);
            pstmt.setString(4, String.valueOf(bookPage));
            pstmt.setString(5, String.valueOf(Date.valueOf(bookBirth)));

            connection.commit();
            System.out.println(bookNum + "번 책이 추가되었습니다.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
