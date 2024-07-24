package Spring.JDBC.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import Spring.JDBC.Student;

public class DAOImpl implements DAOInterface{
	
	/**
	 * To execute SQL queries we require JDBC template
	 * the bean for this will be injected via config xml
	 */
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int create(Student student) {
		String sql="insert into student(id,name,city) values (?,?,?)";
		int result=jdbcTemplate.update(sql,student.getId(),student.getName(),student.getCity());
		return result;
	}

	@Override
	public int update(Student student) {
		String sql="update student set name=? , city=? where id=?";
		int result=jdbcTemplate.update(sql,student.getName(),student.getCity(),student.getId());
		return result;		
	}

	@Override
	public int delete(Student student) {
		String sql="delete from student where id=?";
		int result=jdbcTemplate.update(sql,student.getId());
		return result;			
	}

}
