package library.service.ownerservice;

import io.wisoft.jdbc.PostgresqlAccess;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class UpdateBookByOwner {

    private Scanner sc = new Scanner(System.in);

    public void updateEMP_OPDATE() {
        System.out.print("갱신할 책의 번호를 입력하세요: ");
        int bookNum = sc.nextInt();
        System.out.print("갱신할 출판년도를 입력해주세요: ");
        Date bookBirth = Date.valueOf(sc.next());
        String query = "UPDATE 도서목록 SET 책출판년도=bookBirth WHERE 책번호 IS bookNum";
        try (Connection conn = PostgresqlAccess.setConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)
        ) {
            conn.setAutoCommit(false);
            conn.commit();
            System.out.println(bookNum + "번 책의 출판년도가 갱신되었습니다.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
