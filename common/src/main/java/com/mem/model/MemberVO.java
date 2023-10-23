package com.mem.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberVO implements java.io.Serializable{
	private Integer memberNo;
	private String memberAccount;
	private String memberPassword;
	private String memberName;
	private String memberPhone;
	private String memberNickname;
	private String memberAddress;
	private String memberEmail;
	private Integer memberState;
	

///	public MemberVO(Integer member_no,String member_account, String member_password,
//			String member_name, String member_phone, String member_nickname,String member_address,
//			String member_email,Integer member_state) {
//	}
	public MemberVO() {
		// TODO Auto-generated constructor stub
	}

}