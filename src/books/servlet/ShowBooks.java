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

import com.alibaba.fastjson.JSON;

import books.dao.BookMapper;
import books.pojo.Book;
import books.utils.MyBatisUtil;
import books.utils.Page;

/**
 * Servlet implementation class ShowBooks
 */
@WebServlet("/ShowBooks")
public class ShowBooks extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowBooks() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		Object object=request.getSession().getAttribute("size");
		String from=request.getParameter("pageIndex");
		String size=request.getParameter("pageSize");
		int pageIndex=1;
		if(from!=null&&!from.equals("")){
			pageIndex=Integer.parseInt(from);
		}
		int pageSize=5;
		if(size!=null&&!size.equals("")){
			pageSize=Integer.parseInt(size);
			request.getSession().setAttribute("size", pageSize);
		}else if(object!=null&&!object.equals("")){
			int size1=Integer.parseInt(object.toString());
			pageSize=size1;
		}
		SqlSession sqlSession=null;
		sqlSession=MyBatisUtil.creatSqlSession();
		int count=sqlSession.getMapper(BookMapper.class).count();
		int totalPage=(count%pageSize==0?count/pageSize:count/pageSize+1);
		List<Book> bookList=sqlSession.getMapper(BookMapper.class).getAllBooks((pageIndex-1)*pageSize, pageSize);
		Page page=new Page();
		page.setBookList(bookList);
		page.setTotalPage(totalPage);
		page.setPageIndex(pageIndex);
		page.setCount(count);
		page.setPageSize(pageSize);
		request.getSession().setAttribute("page", page);
		request.getRequestDispatcher("books.jsp").forward(request, response);
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
