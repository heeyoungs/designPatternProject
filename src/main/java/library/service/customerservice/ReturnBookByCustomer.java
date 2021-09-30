package library.service.customerservice;

import exception.InputException;
import library.action.Update;

import io.wisoft.jdbc.PostgresqlAccess;
import library.person.Customer;
import library.service.CheckBookList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ReturnBookByCustomer implements Update {

    private Scanner sc = new Scanner(System.in);

    @Override
    public void updateBook() throws InputException {
        System.out.print("반납할 책의 번호를 입력해주세요: ");
        int bookNum;
        try {
            bookNum = sc.nextInt();
        }catch (Exception e){
            throw new InputException("숫자를 입력안함.");
        }
        if (!Customer.borrowList.contains(bookNum)) {
            System.out.println("반납 대상이 아닙니다.");
            return;
        }

        String query = "UPDATE 도서목록 SET 책대출가능여부=true WHERE 책번호=?";
        try (Connection conn = PostgresqlAccess.setConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)
        ) {
            conn.setAutoCommit(false);
            pstmt.setInt(1,bookNum);
            Customer.borrowList.remove(bookNum);
            pstmt.executeUpdate();
            conn.commit();
            System.out.println(bookNum + "번 책을 반납했습니다.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
