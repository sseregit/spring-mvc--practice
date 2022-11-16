package hello.servlet.web.frontcontroller.v3;

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
import hello.servlet.web.frontcontroller.v2.controller.MemberFormControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberListControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberSaveControllerV2;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet{

	private Map<String, ControllerV3> controllerMap = new HashMap<String, ControllerV3>();
	
	public FrontControllerServletV3() {
		controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
		controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
		controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String requestURI = req.getRequestURI();
		
		ControllerV3 controllerV3 = controllerMap.get(requestURI);
		
		if (controllerV3 == null) {
			resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		
		Map<String, String> createParamMap = createParamMap(req, resp);
		
		ModelView mv = controllerV3.process(createParamMap);
		
		String viewPath = mv.getViewPath();
		MyView view = viewResolver(viewPath);
		view.render(mv.getModel(),req, resp);
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
