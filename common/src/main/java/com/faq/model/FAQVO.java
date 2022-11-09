package com.faq.model;

public class FAQVO implements java.io.Serializable {
	private Integer faqNo;
	private String faqQue;
	private String faqAns;
	private String faqClass;
	public Integer getFaqNo() {
		return faqNo;
	}
	public void setFaqNo(Integer faqNo) {
		this.faqNo = faqNo;
	}
	public String getFaqQue() {
		return faqQue;
	}
	public void setFaqQue(String faqQue) {
		this.faqQue = faqQue;
	}
	public String getFaqAns() {
		return faqAns;
	}
	public void setFaqAns(String faqAns) {
		this.faqAns = faqAns;
	}
	public String getFaqClass() {
		return faqClass;
	}
	public void setFaqClass(String faqClass) {
		this.faqClass = faqClass;
	}
}