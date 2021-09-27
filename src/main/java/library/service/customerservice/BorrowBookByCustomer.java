package library.service.customerservice;

import io.wisoft.jdbc.PostgresqlAccess;
import library.action.Update;
import library.service.CheckBookList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class BorrowBookByCustomer implements Update {

    private Scanner sc = new Scanner(System.in);

    @Override
    public void updateBook() {
        System.out.print("빌리고 싶은 책의 번호를 입력해주세요: ");
        int bookNum = sc.nextInt();

        CheckBookList checkBookList = new CheckBookList();

        if (!checkBookList.isBook(bookNum)) {
            System.out.println("책이 없습니다!");
            return;
        }

        String query = "UPDATE 도서목록 SET 책대출가능여부=false WHERE 책번호=bookNum";
        try (Connection conn = PostgresqlAccess.setConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)
        ) {
            conn.setAutoCommit(false);
            conn.commit();
            System.out.println(bookNum + "번 책을 빌렸습니다.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
