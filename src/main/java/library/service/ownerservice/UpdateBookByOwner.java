package library.service.ownerservice;

import exception.InputException;
import io.wisoft.jdbc.PostgresqlAccess;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class UpdateBookByOwner {

    private Scanner sc = new Scanner(System.in);

    public void updateEMP_OPDATE() throws InputException {
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
}
