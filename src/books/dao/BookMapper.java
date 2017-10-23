package books.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import books.pojo.Book;

public interface BookMapper {
	public int count();
	public List<Book> getAllBooks(@Param("from")Integer from,@Param("pageSize")Integer pageSize);
	public int addBook(Book book);
	public int updateBook(Book book);
	public Book getBookById(@Param("id")Integer id);
	public int deleteBookById(@Param("id")Integer id);
}
