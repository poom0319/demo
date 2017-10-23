package books.utils;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {
	private static SqlSessionFactory factory;
	 static{
		 try {
			InputStream is=Resources.getResourceAsStream("mybatis-config.xml");
			factory=new SqlSessionFactoryBuilder().build(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	 }
	 
	 public static SqlSession creatSqlSession(){
		 return factory.openSession(false);
	 }
	 
	 public static void close(SqlSession sqlSession){
		 if(null!=sqlSession){
			 sqlSession.close();
		 }
	 }
}
