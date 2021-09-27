package library.service.ownerservice;

import io.wisoft.jdbc.PostgresqlAccess;
import library.action.Remove;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class RemoveBookByOwner implements Remove {

    private Scanner sc = new Scanner(System.in);

    @Override
    public void removeBook() {
        System.out.print("지울 책의 번호를 입력해주세요: ");
        int bookNum = sc.nextInt();
        String query = "Delete FROM 도서목록 where 책번호=bookNum";
        try (Connection conn = PostgresqlAccess.setConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
        ) {
            conn.setAutoCommit(false);
            conn.commit();
            System.out.println(bookNum + "번 책이 제거되었습니다.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
