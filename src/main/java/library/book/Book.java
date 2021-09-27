package library.book;

public class Book {
    // 번호, 이름, 작가, 페이지, 대출가능 여부->(대출 한 사람 번호)
    private int bookNum;
    private String bookName;
    private String bookWriter;
    private int bookPage;
    private int bookBirth;
    private boolean isBookNow;

    public Book(int bookNum,String bookName, String bookWriter,int bookPage, int bookBirth, boolean isBookNow) {
        this.bookNum = bookNum;
        this.bookName = bookName;
        this.bookWriter = bookWriter;
        this.bookPage = bookPage;
        this.bookBirth = bookBirth;
        this.isBookNow = isBookNow;
    }

    // 동일한 책의 기준, 책 이름과 작가가 동일
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Book) {
            Book book = (Book) obj;
            return book.bookName.equals(bookName) && book.bookWriter.equals(bookWriter);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode(){
        return bookName.hashCode() + bookWriter.hashCode();
    }
}