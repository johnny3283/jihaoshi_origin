package com.faq.model;

import java.util.List;
import java.util.Set;

public interface FAQDAO_interface {
	
		public void insert(FAQVO faqVO);
		
	    public void update(FAQVO faqVO);
	    
	    public void delete(Integer faqNo);
	    
	    public FAQVO findByPrimaryKey(Integer faqNo);
	    public List<FAQVO> getAll();
	    //�d�߬Y���O��FAQ(�@��h)(�^�� Set)
	    public List<FAQVO> getFAQsByFAQClass(String faqClass);
	    public List<FAQVO> selectFAQ();
	    public List<FAQVO> selectFAQ(String faqClass);
}