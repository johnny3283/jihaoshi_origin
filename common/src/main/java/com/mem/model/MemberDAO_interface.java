package com.mem.model;

import java.util.List;

public interface MemberDAO_interface {
	public void insert(MemberVO memberVO);

	public void update(MemberVO memberVO);

	public void delete(Integer memberNo);

	public MemberVO findByPrimaryKey(Integer memberNo);

	public List<MemberVO> getAll();

	public MemberVO selectForLogin(String mamberAccount, String mamberPassword);


}
