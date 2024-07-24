package Spring.JDBC.dao;

import Spring.JDBC.Student;

public interface DAOInterface {
	
	/**
	 * Declaring all the CRUD methods.
	 * 
	 * Create,update and delete will always return an integer value which states that
	 * how many rows are affected post operation
	 */
	
	public int create(Student student);
	public int update(Student student);
	public int delete(Student student);

}
