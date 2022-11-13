package com.faq.model;

import java.util.List;


public class FAQService {

	private FAQDAO_interface dao; //降低相依性

	public FAQService() {
		dao = new FAQDAO();
	}

	public FAQVO addFAQ(String faqQue, String faqAns, String faqClass) {

		FAQVO faqVO = new FAQVO();

		faqVO.setFaqQue(faqQue);
		faqVO.setFaqAns(faqAns);
		faqVO.setFaqClass(faqClass);
		dao.insert(faqVO);

		return faqVO;
	}

	public FAQVO updateFAQ(Integer faqNo, String faqQue, String faqAns,String faqClass) {

		FAQVO faqVO = new FAQVO();
		faqVO.setFaqNo(faqNo);
		faqVO.setFaqQue(faqQue);
		faqVO.setFaqAns(faqAns);
		faqVO.setFaqClass(faqClass);
		dao.update(faqVO);

		return faqVO;
	}

	public void deleteFAQ(Integer faqNo) {
		dao.delete(faqNo);
	}

	public FAQVO getOneFAQ(Integer faqNo) {
		return dao.findByPrimaryKey(faqNo);
	}

	public List<FAQVO> getClassFAQ(String faqClass) {
		return dao.getFAQsByFAQClass(faqClass);
	}
	
	public List<FAQVO> getAll() {
		return dao.getAll();
	}
}
