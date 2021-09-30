package library.service.ownerservice;

import exception.InputException;
import io.wisoft.jdbc.PostgresqlAccess;
import library.action.Action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class RemoveBookByOwner implements Action {

    private Scanner sc = new Scanner(System.in);

    @Override
    public void actionBook() throws InputException {
        System.out.print("지울 책의 번호를 입력해주세요: ");
        int bookNum;
        try {
            bookNum = sc.nextInt();
        }catch (Exception e){
            throw new InputException("숫자를 입력안함.");
        }
        String query = "DELETE FROM 도서목록 WHERE 책번호=?";
        try (Connection conn = PostgresqlAccess.setConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
        ) {
            conn.setAutoCommit(false);
            pstmt.setInt(1, bookNum);
            pstmt.executeUpdate();
            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(bookNum + "번의 책이 존재하지 않습니다.");
        }
        System.out.println(bookNum + "번 책이 제거되었습니다.");
    }
}
