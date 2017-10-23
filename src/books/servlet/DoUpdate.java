package books.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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
 * Servlet implementation class DoUpdate
 */
@WebServlet("/DoUpdate")
public class DoUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		String opr=request.getParameter("opr");
		SqlSession sqlSession=null;
		sqlSession=MyBatisUtil.creatSqlSession();
		if(opr.equals("read")){
			String bookid=request.getParameter("id");
			int id=Integer.parseInt(bookid);
			Book book=sqlSession.getMapper(BookMapper.class).getBookById(id);
			request.getSession().setAttribute("book", book);
			request.getRequestDispatcher("change.jsp").forward(request, response);
		}else if(opr.equals("change")){
			String bookid=request.getParameter("id");
			int id=Integer.parseInt(bookid);
			String bookName=request.getParameter("bookName");
			String author=request.getParameter("author");
			String publish=request.getParameter("publish");
			String publishDate=request.getParameter("publishDate");
			int page=Integer.parseInt(request.getParameter("page"));
			int price=Integer.parseInt(request.getParameter("price"));
			String content=request.getParameter("content");
			Book book=new Book();
			book.setId(id);
			book.setAuthor(author);
			book.setBookName(bookName);
			book.setContent(content);
			book.setPage(page);
			book.setPrice(price);
			book.setPublish(publish);
			book.setPublishDate(publishDate);
			response.setContentType("text/html;charset=utf-8");
			int count=0;
			try {
				count=sqlSession.getMapper(BookMapper.class).updateBook(book);
				sqlSession.commit();
			} catch (Exception e) {
				e.printStackTrace();
				sqlSession.rollback();
				count=0;
			}
			if(count>0){
				out.print("<script type='text/javascript'>alert('修改成功，返回图书信息列表');location.href='ShowBooks';</script>");
			}else{
				out.print("<script type='text/javascript'>alert('修改失败，返回图书信息列表');location.href='ShowBooks';</script>");
			}
		}else if(opr.equals("del")){
			String bookid=request.getParameter("id");
			int id=Integer.parseInt(bookid);
			response.setContentType("text/html;charset=utf-8");
			int count=0;
			try {
				count=sqlSession.getMapper(BookMapper.class).deleteBookById(id);
				sqlSession.commit();
			} catch (Exception e) {
				e.printStackTrace();
				sqlSession.rollback();
				count=0;
			}
			if(count>0){
				out.print("<script type='text/javascript'>alert('删除成功，返回图书信息列表');location.href='ShowBooks';</script>");
			}else{
				out.print("<script type='text/javascript'>alert('删除失败，返回图书信息列表');location.href='ShowBooks';</script>");
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doGet(request, response);
	}

}
