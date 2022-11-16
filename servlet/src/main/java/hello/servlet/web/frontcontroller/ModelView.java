package hello.servlet.web.frontcontroller;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ModelView {
	
	private String viewPath;
	private Map<String, Object> model = new HashMap<>();
	
	public ModelView(String viewPath) {
		this.viewPath = viewPath;
	}
	
	
	
	

}
