/**
 * 
 */
package com.crs.flipkart.business;

import com.crs.flipkart.bean.Student;
import com.crs.flipkart.dao.StudentDaoOperation;
import com.crs.flipkart.dao.StudentDaoOperationInterface;
import com.crs.flipkart.dao.UserDaoOperation;
import com.crs.flipkart.dao.UserDaoOperationInterface;
import com.crs.flipkart.exception.UserAlreadyExistsException;

/**
 * @author aditya.gupta3
 *
 */
public class StudentOperation implements StudentOperationInterface {

	UserDaoOperationInterface userDaoOperation = new UserDaoOperation();
	StudentDaoOperationInterface studentDaoOperation = new StudentDaoOperation();
	@Override
	public boolean registerStudent(Student student) throws UserAlreadyExistsException {
		if (userDaoOperation.getUser(student.getUserId())!=null) {
			throw new UserAlreadyExistsException(student.getUserId());
		}
		
		studentDaoOperation.registerStudent(student);
		return true;
	}

}
