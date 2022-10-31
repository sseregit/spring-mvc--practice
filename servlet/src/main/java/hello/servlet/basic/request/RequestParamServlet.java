package hello.servlet.basic.request;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("[전체 파라미터 조회] - start");
		// Version이 8이라 사용불가
		// req.getParameterNames().asIterator().forEachRemaining(paramName -> System.out.println(paramName + "=" + req.getParameter(paramName));)
	
		Enumeration<String> params = req.getParameterNames();
		
		while (params.hasMoreElements()) {
			String param = params.nextElement();
			System.out.println(param + " = " + req.getParameter(param));
		}
		
		System.out.println("[전체 파라미터 조회] - end");
		System.out.println();
		System.out.println("[단일 파라미터 조회] - start");
		String username = req.getParameter("username");
		String age = req.getParameter("age");
		System.out.println("username =" + username );
		System.out.println("age =" + age );
		System.out.println("[단일 파라미터 조회] - end");
		System.out.println();		
		System.out.println("[이름이 같은 복수 파라미터 조회] - start");
		String[] names = req.getParameterValues("username");
		for (String name : names) {
			System.out.println("username = "+name);
		}
		System.out.println("[이름이 같은 복수 파라미터 조회] - end");
		
				
	}

	
	
}
