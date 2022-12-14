package hello.servlet.web.frontcontroller.v5.adapter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v4.ControllerV4;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;

public class ControllerV4HandlerAdapter implements MyHandlerAdapter {

	@Override
	public boolean support(Object handler) {
		return (handler instanceof ControllerV4);
	}

	@Override
	public ModelView handle(HttpServletRequest req, HttpServletResponse resp, Object handler)
			throws ServletException, IOException {
		ControllerV4 controller = (ControllerV4) handler;
		
		Map<String, String> paramMap = createParamMap(req);
		Map<String, Object> model = new HashMap<>();
		
		String viewPath = controller.process(paramMap, model);
		
		ModelView mv = new ModelView(viewPath);
		
		mv.setModel(model);
		
		return mv;
	}
	
	private Map<String, String> createParamMap(HttpServletRequest req) {
		
		Map<String, String> paramMap = new HashMap<>();
		
		Enumeration<String> parameterNames = req.getParameterNames();
		
		while(parameterNames.hasMoreElements()) {
			
			String parameterName = parameterNames.nextElement();
			
			paramMap.put(parameterName, req.getParameter(parameterName));
		}
		
		return paramMap;
	}

}
