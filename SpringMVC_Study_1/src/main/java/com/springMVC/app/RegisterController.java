package com.springMVC.app;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegisterController {
//	@RequestMapping(value="/register/add", method=RequestMethod.GET) // �ű�ȸ�� ����
	@GetMapping("/register/add")
	public String register() {
		return "registerForm";  // WEB-INF/views/registerForm.jsp
	}
	
//	@RequestMapping(value="/register/save", method=RequestMethod.POST) // �ű�ȸ�� ����
// 	@PostMapping("/register/save")
	@PostMapping("/register/add")
	public String save(@ModelAttribute("user") User user, Model m) throws UnsupportedEncodingException {
		if(!isValid(user)) {
			String msg = URLEncoder.encode("id�� �߸��Է��ϼ̽��ϴ�.", "utf-8");
			
			m.addAttribute("msg", msg);
			return "redirect:/register/add"; // �ű�ȸ�� ����ȭ������ �̵�(redirect)
		}
		
		return "registerInfo";
	}

	private boolean isValid(User user) {
		return false;
	}
	
}