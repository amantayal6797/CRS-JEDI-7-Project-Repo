/**
 * 
 */
package com.crs.flipkart.bean;

/**
 * @author aditya
 *
 */
public class Customer {
	private int id;
	private String name;
	private String address;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
    // Creating toString
    @Override
    public String toString()
    {
        return "Organisation [organisation_name="
            + name
            + ", description="
            + id
            + ", Employees="
            + address + "]";
    }
}
