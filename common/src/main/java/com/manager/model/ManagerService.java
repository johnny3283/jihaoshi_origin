package com.manager.model;

import java.util.List;

public class ManagerService {
	private ManagerDAO_interface dao;

	public ManagerService() {
		dao = new ManagerDAO();
	}

	public ManagerVO addmember(String managerName, String managerIp, String managerAccount, String managerPassword) {

		ManagerVO ManagerVO = new ManagerVO();
		ManagerVO.setManagerName(managerName);
		ManagerVO.setManagerIp(managerIp);
		ManagerVO.setManagerAccount(managerAccount);
		ManagerVO.setManagerPassword(managerPassword);
		

		dao.insert(ManagerVO);

		return ManagerVO;
	}

	public ManagerVO updateEmp(Integer managerNo,String managerName, String managerIp,
			String managerAccount, String managerPassword,Integer managerStatus) {

		ManagerVO ManagerVO = new ManagerVO();
		ManagerVO.setManagerNo(managerNo);
		ManagerVO.setManagerName(managerName);
		ManagerVO.setManagerIp(managerIp);
		ManagerVO.setManagerAccount(managerAccount);
		ManagerVO.setManagerPassword(managerPassword);
		ManagerVO.setManagerStatus(managerStatus);
		dao.update(ManagerVO);
		return ManagerVO;
	}

	public void deleteEmp(Integer managerNo) {
		dao.delete(managerNo);
	}

	public List<ManagerVO> getAll() {
		return dao.getAll();
	}

	public ManagerVO getOneMem(Integer managerNo) {
		return dao.findByPrimaryKey(managerNo);
	}

	public ManagerVO Login(String managerAccount, String managerPassword) {
		return dao.selectForLogin(managerAccount,managerPassword);
	}

}
