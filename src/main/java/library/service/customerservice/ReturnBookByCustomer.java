package library.service.customerservice;

import library.action.Update;

import io.wisoft.jdbc.PostgresqlAccess;
import library.service.CheckBookList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class ReturnBookByCustomer implements Update {

    private Scanner sc = new Scanner(System.in);

    @Override
    public void updateBook() {
        System.out.print("반납할 책의 번호를 입력해주세요: ");
        int bookNum = sc.nextInt();

        CheckBookList checkBookList = new CheckBookList();

        if (checkBookList.isBook(bookNum)) {
            System.out.println("그런 책 빌려가신적 없습니다!");
            return;
        }

        String query = "UPDATE 도서목록 SET 책대출가능여부=true WHERE 책번호=bookNum";
        try (Connection conn = PostgresqlAccess.setConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)
        ) {
            conn.setAutoCommit(false);
            conn.commit();
            System.out.println(bookNum + "번 책을 반납했습니다.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
