package javaProjectCnx;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;

public class AddCat {

	@Override
	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		return 1;
	}
	
	@Override
	public int doEndTag() throws jakarta.servlet.jsp.JspException {
		JspWriter out = pageContext.getOut();
		return 4;
	}
}
