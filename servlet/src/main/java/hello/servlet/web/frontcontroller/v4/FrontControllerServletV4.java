package hello.servlet.web.frontcontroller.v4;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;

@WebServlet(name = "frontControllerServletV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet{

	private Map<String, ControllerV4> controllerMap = new HashMap<String, ControllerV4>();
	
	public FrontControllerServletV4() {
		controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
		controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
		controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String requestURI = req.getRequestURI();
		
		ControllerV4 controllerV4 = controllerMap.get(requestURI);
		
		if (controllerV4 == null) {
			resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		
		Map<String, String> createParamMap = createParamMap(req, resp);
	
		Map<String, Object> model = new HashMap<>();
		
		String viewName = controllerV4.process(createParamMap, model);
		
		MyView view = viewResolver(viewName);
		view.render(model,req, resp);
	}
	
	private Map<String, String> createParamMap(HttpServletRequest req, HttpServletResponse resp) {
		
		Map<String, String> paramMap = new HashMap<>();
		
		Enumeration<String> parameterNames = req.getParameterNames();
		
		while(parameterNames.hasMoreElements()) {
			String parameterName = parameterNames.nextElement();
			paramMap.put(parameterName, req.getParameter(parameterName));
		}
		
		return paramMap;
	}
	
	private MyView viewResolver(String viewName) {
		return new MyView("/WEB-INF/views/"+viewName+".jsp");
	}
	
	
	
}
