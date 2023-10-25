package com.employee.model;

import java.util.List;

public class EmployeeService {
	private EmployeeDAO_interface dao;

	public EmployeeService() {
		dao = new EmployeeDAO();
	}

	public EmployeeVO addMember(String managerName, String managerAccount, String managerPassword) {

		EmployeeVO empVO = new EmployeeVO();
		empVO.setManagerName(managerName);
		empVO.setManagerAccount(managerAccount);
		empVO.setManagerPassword(managerPassword);
		

		dao.insert(empVO);

		return empVO;
	}

	public EmployeeVO updateMember(Integer managerNo,String managerAccount,String managerPassword,
							   String managerName,Integer managerStatus) {
		EmployeeVO empVO = new EmployeeVO();
		empVO.setManagerNo(managerNo);
		empVO.setManagerAccount(managerAccount);
		empVO.setManagerPassword(managerPassword);
		empVO.setManagerName(managerName);
		empVO.setManagerStatus(managerStatus);
		dao.update(empVO);
		return empVO;
	}
	public List<EmployeeVO> getAll() {
		return dao.getAll();
	}

	public EmployeeVO getOneMember(Integer managerNo) {
		return dao.findByPrimaryKey(managerNo);
	}

	public EmployeeVO Login(String managerAccount, String managerPassword) {
		return dao.selectForLogin(managerAccount,managerPassword);
	}	
	public List<Integer> getAuthority(Integer managerNo) {
		return dao.GetAuthority(managerNo);
	}
}
