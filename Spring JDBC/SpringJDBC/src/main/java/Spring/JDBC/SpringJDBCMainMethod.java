package Spring.JDBC;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import Spring.JDBC.dao.DAOInterface;

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
		 * Insert Operation example
		 */
		String sql="insert into student(id,name,city) values (?,?,?)";
		int result=jdbcTemplate.update(sql,1,"Harsh","Mumbai");
		System.out.println("Rows affected - "+result);
		
		DAOInterface stdao=factory.getBean("daolayer",DAOInterface.class);
		
		/**
		 * This below values will come from a service layer 
		 * when we build a web application
		 */
		Student st=new Student();
		
		/**
		 * Insert Operation via DAO Layer
		 */
		st.setId(2);
		st.setName("Meet");
		st.setCity("Mumbai");
		System.out.println("Insert Operation is performed, rows affected - "+stdao.create(st));
		
		/**
		 * Update Operation for row id 2
		 */
		st.setId(2);
		st.setName("Meet Pandya");
		st.setCity("Mumbai Suburban");
		System.out.println("Update Operation is performed, rows affected - "+stdao.update(st));
		
		/**
		 * Delete Operation for row id 2
		 */
		st.setId(2);
		System.out.println("Delete Operation is performed, rows affected - "+stdao.delete(st));
		
		/**
		 * Select Single row
		 */
		st.setId(1);
		System.out.println("Single row data for student class - "+stdao.selectSingleRow(st).toString());
		
		/**
		 * Select All the rows
		 */
		
		System.out.println("All row data for student class");
		List<Student> allstudents=stdao.allStudentData();
		for(Student s: allstudents) {
			System.out.println(s.toString());
		}
		
    }
}
