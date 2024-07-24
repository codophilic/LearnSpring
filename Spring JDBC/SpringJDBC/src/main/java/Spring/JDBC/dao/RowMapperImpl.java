package Spring.JDBC.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import Spring.JDBC.Student;

public class RowMapperImpl implements RowMapper<Student> {
	
	/**
	 * RowMapper is an interface which has a default mapRow abstract method in it.
	 * So here we need to set that which all columns needs to be mapped 
	 * with the Student instance variable.
	 */

	@Override
	public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
		Student st=new Student();
		st.setId(rs.getInt(1));
		st.setName(rs.getString(2));
		st.setCity(rs.getString(3));
		return st;
	}

}
