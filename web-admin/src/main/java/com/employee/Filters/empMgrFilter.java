package com.employee.Filters;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.employee.model.EmployeeVO;

public class empMgrFilter  implements Filter {

	private FilterConfig config;

	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}

	public void destroy() {
		config = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		// 【取得 session】
		HttpSession session = req.getSession();
		// 【從 session 判斷此user是否登入過】
		EmployeeVO manager =(EmployeeVO) session.getAttribute("manager");
		if(manager == null) {
			res.sendRedirect(req.getContextPath() + "/manager/login.jsp");
			return;
		}
		List<Integer> authority = manager.getAuthorityNo();
		
		boolean sts  = authority.contains(5);
		System.out.println(sts);
		if(!sts) {
			
			session.setAttribute("location", req.getRequestURI());
			res.sendRedirect(req.getContextPath() + "/manager/notAllow.jsp");
		}
		else {
			chain.doFilter(request, response);
			
		}
	}

}
