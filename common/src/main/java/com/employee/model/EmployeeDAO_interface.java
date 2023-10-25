package com.employee.model;

import java.util.List;

public interface EmployeeDAO_interface {
	public void insert(EmployeeVO EmployeeVO);

	public void update(EmployeeVO EmployeeVO);

	public EmployeeVO findByPrimaryKey(Integer managerNo);

	public List<EmployeeVO> getAll();

	public EmployeeVO selectForLogin(String managerAccount, String managerPassword);

	public List<Integer> GetAuthority(Integer managerNo);

}
