package org.example;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

import org.example.calculator.domain.Calculator;
import org.example.calculator.domain.PositiveNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/calculate")
public class CalculatorServlet implements Servlet {
	private static final Logger log = LoggerFactory.getLogger(CalculatorServlet.class);
	private ServletConfig servletConfig;

	// 서블릿을 처음 메모리에 올릴 때 실행되어, 서블릿을 초기화하며 처음 한 번만 실행
	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		log.info("init");
		this.servletConfig = servletConfig;
	}

	// 요청/응답(request, response)을 처리하며, 요청이 GET인지 POST인지 구분하여 doGet(), doPost() 메소드를 사용
	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		log.info("service");
		int operand1 = Integer.parseInt(request.getParameter("operand1"));
		String operator = request.getParameter("operator");
		int operand2 = Integer.parseInt(request.getParameter("operand2"));

		int result = Calculator.calculate(new PositiveNumber(operand1), operator, new PositiveNumber(operand2));

		PrintWriter writer = response.getWriter();
		writer.println(result);
	}

	// 서블릿 요청이 있을 때, 실행
	@Override
	public void destroy() {
		// resource release
	}

	// 서블릿을 실행하기 위해 필요한 설정 정보 제공
	@Override
	public ServletConfig getServletConfig() {
		return this.servletConfig;
	}

	// 서블릿 버전, 권리, 작성한 사람에 대한 정보 제공
	@Override
	public String getServletInfo() {
		return null;
	}
}
