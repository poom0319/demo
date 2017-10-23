package books.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import books.dao.BookMapper;
import books.pojo.Book;
import books.utils.MyBatisUtil;

/**
 * Servlet implementation class AddBook
 */
@WebServlet("/AddBook")
public class AddBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddBook() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bookName=request.getParameter("bookName");
		String author=request.getParameter("author");
		String publish=request.getParameter("publish");
		String publishDate=request.getParameter("publishDate");
		Integer page=Integer.parseInt(request.getParameter("page"));
		Integer price=Integer.parseInt(request.getParameter("price"));
		String content=request.getParameter("content");
		Book book=new Book();
		book.setAuthor(author);
		book.setBookName(bookName);
		book.setContent(content);
		book.setPage(page);
		book.setPrice(price);
		book.setPublish(publish);
		book.setPublishDate(publishDate);
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		SqlSession sqlSession=null;
		sqlSession=MyBatisUtil.creatSqlSession();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		int count=0;
		try {
			count=sqlSession.getMapper(BookMapper.class).addBook(book);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
			count=0;
		}
		if(count>0){
			out.print("<script type='text/javascript'>alert('添加成功，返回图书信息列表');location.href='ShowBooks';</script>");
		}else{
			out.print("<script type='text/javascript'>alert('添加失败，返回图书信息列表');location.href='ShowBooks';</script>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		this.doGet(request, response);
	}

}
