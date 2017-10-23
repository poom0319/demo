package books.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.alibaba.fastjson.JSON;

import books.dao.UserMapper;
import books.pojo.User;
import books.utils.MyBatisUtil;

/**
 * Servlet implementation class DoLogin
 */
@WebServlet("/DoLogin")
public class DoLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String opr=request.getParameter("opr");
		
		
		if(opr.equals("out")){
			request.getSession().invalidate();
			response.sendRedirect("login.jsp");
		}else{
			String name=request.getParameter("user");
			String password=request.getParameter("password");
			SqlSession sqlSession=null;
			sqlSession=MyBatisUtil.creatSqlSession();
			User user=new User();
			user.setName(name);
			user.setPassword(password);
			User user2=sqlSession.getMapper(UserMapper.class).login(user);
			MyBatisUtil.close(sqlSession);
			PrintWriter out=response.getWriter();
			String flag="";
			if(user2!=null){
				if(user2.getUserType()==1){
					request.getSession().setAttribute("login", user2.getName());
					flag="";
				}else{
					flag="您的操作权限不够，无法登陆！";
				}
			}else{
				flag="用户名或密码错误";
			}
			response.setContentType("text/json;charset=utf-8");
			String json=JSON.toJSONString(flag);
			out.print(json);
			out.flush();
			out.close();
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
