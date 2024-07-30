package mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mvc.dao.StudentDao;
import mvc.model.entities.Student;


/**
 * Database changes are either committed (saved permanently) or rolled back (canceled) as a single unit via Transactional
 */
@Service
@Transactional
public class StudentServiceImpl implements StudentService {
	
	/**
	 * Injecting Dependencies
	 */
	@Autowired
	private StudentDao studentDao;

	public StudentDao getStudentDao() {
		return studentDao;
	}

	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}

	public int insert(Student student) {
		return studentDao.insert(student);
	}

	
}
