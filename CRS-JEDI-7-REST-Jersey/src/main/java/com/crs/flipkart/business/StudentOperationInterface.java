/**
 * 
 */
package com.crs.flipkart.business;

import com.crs.flipkart.bean.Student;
import com.crs.flipkart.exception.UserAlreadyExistsException;

/**
 * @author aditya.gupta3
 *
 */
public interface StudentOperationInterface {
	/**
	 * Method to register a student
	 * @return 
	 * @throws UserAlreadyExistsException 
	 */
	public boolean registerStudent(Student student) throws UserAlreadyExistsException;

}