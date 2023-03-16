package org.example;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.example.calculator.domain.Calculator;
import org.example.calculator.domain.PositiveNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 추상 클래스 HttpServlet 는 GET 요청(doGet())과 POST 요청(doPost())로 구분할 수 있다.
 */
@WebServlet("/calculate")
public class CalculatorServlet extends HttpServlet {
	private static final Logger log = LoggerFactory.getLogger(CalculatorServlet.class);

	// 요청/응답(request, response)을 처리하며, 요청이 GET인지 POST인지 구분하여 doGet(), doPost() 메소드를 사용 --> HttpServlet
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("service");
		int operand1 = Integer.parseInt(request.getParameter("operand1"));
		String operator = request.getParameter("operator");
		int operand2 = Integer.parseInt(request.getParameter("operand2"));

		int result = Calculator.calculate(new PositiveNumber(operand1), operator, new PositiveNumber(operand2));

		PrintWriter writer = response.getWriter();
		writer.println(result);
	}

}
