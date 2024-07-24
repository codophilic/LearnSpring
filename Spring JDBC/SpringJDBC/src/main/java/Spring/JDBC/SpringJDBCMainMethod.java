package Spring.JDBC;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Hello world!
 *
 */
public class SpringJDBCMainMethod 
{
    public static void main( String[] args )
    {
		ClassPathXmlApplicationContext factory = new ClassPathXmlApplicationContext("Spring/JDBC/springjdbc.xml");
		JdbcTemplate jdbcTemplate=factory.getBean("jdbcTemplate",JdbcTemplate.class);
		
		/**
		 * Insert Operation
		 */
		String sql="insert into student(id,name,city) values (?,?,?)";
		int result=jdbcTemplate.update(sql,1,"Harsh","Mumbai");
		System.out.println("Rows affected - "+result);
    }
}
