package hello.servlet.web.frontcontroller.v2.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hello.servlet.domain.Member;
import hello.servlet.domain.MemberRepository;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v2.ControllerV2;

public class MemberSaveControllerV2 implements ControllerV2 {

	private MemberRepository memberRepository = MemberRepository.getInstance();
	
	@Override
	public MyView process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String username = req.getParameter("username");
		int age = Integer.parseInt(req.getParameter("age"));
		
		Member member = new Member(username, age);
		
		memberRepository.save(member);
		
		req.setAttribute("member", member);
			
		return new MyView("/WEB-INF/views/save-result.jsp");
		
	}
	
	

}
