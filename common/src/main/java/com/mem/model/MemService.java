package com.mem.model;

import java.util.List;

public class MemService {
	private MemberDAO_interface dao;

	public MemService() {
		dao = new MemberDAO();
	}

	public MemberVO addmember(String memberAccount, String memberPassword, String memberName, String memberPhone,
			String memberNickname, String memberAddress, String memberEmail) {

		MemberVO MemberVO = new MemberVO();
		MemberVO.setMemberAccount(memberAccount);
		MemberVO.setMemberPassword(memberPassword);
		MemberVO.setMemberName(memberName);
		MemberVO.setMemberPhone(memberPhone);
		MemberVO.setMemberNickname(memberNickname);
		MemberVO.setMemberAddress(memberAddress);
		MemberVO.setMemberEmail(memberEmail);

		dao.insert(MemberVO);

		return MemberVO;
	}

	public MemberVO updateEmp(String memberAccount, Integer memberNo, String memberPassword, String memberName,
			String memberPhone, String memberNickname, String memberAddress, String memberEmail) {

		MemberVO MemberVO = new MemberVO();
		MemberVO.setMemberAccount(memberAccount);
		MemberVO.setMemberNo(memberNo);
		MemberVO.setMemberPassword(memberPassword);
		MemberVO.setMemberName(memberName);
		MemberVO.setMemberPhone(memberPhone);
		MemberVO.setMemberNickname(memberNickname);
		MemberVO.setMemberAddress(memberAddress);
		MemberVO.setMemberEmail(memberEmail);
		dao.update(MemberVO);
		return MemberVO;
	}

	public void deleteEmp(Integer empno) {
		dao.delete(empno);
	}

	public List<MemberVO> getAll() {
		return dao.getAll();
	}

	public MemberVO getOneMem(Integer memberNo) {
		return dao.findByPrimaryKey(memberNo);
	}

	public MemberVO Login(String memberAccount, String memberPassword) {
		return dao.selectForLogin(memberAccount,memberPassword);
	}

}
