package user.controller;

import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import user.domain.LoginDTO;
import user.domain.UserVO;
import user.service.UserService;

@Controller
@RequestMapping("/user")
public class UserLoginController {
	
	@Autowired
	private UserService userService;
	
	//로그인 페이지 리턴
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginGET(){
		return "user/login";
	}
	
	//로그인 페이지 리턴
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String mainPage(){
		return "user/main";
	}
	
	//로그인 처리
	@RequestMapping(value = "/loginPost", method = RequestMethod.POST)
	public String loginPOST(LoginDTO loginDTO, HttpSession httpsession, Model model) throws Exception {
		UserVO userVO = userService.login(loginDTO);//입력된 로그인정보를 이용하여 DB에 저장된 회원 정보를 가져옴
		System.out.println("userVO로그인 :: " + userVO);
		if(userVO == null||!BCrypt.checkpw(loginDTO.getUserpw(), userVO.getUserpw())) {//DB에서 받아온 회원정보가 없거나 페스워드 비교값이 일치하지않으면
			return "redirect:/jsp/user/login";//다시 로그인페이지로 이동
		}
		model.addAttribute("user", userVO);//로그인 정보값이 일치하면
		return "user/main";//로그인 성공페이지로 이동
	}
		
	//세션정보를 지우고 로그인페이지로 이동
	@RequestMapping(value = "/logout")
	public String logout(HttpSession httpsession){
		httpsession.removeAttribute("login");//세션정보를 지움
		httpsession.invalidate();//세션정보를 지움
		return "redirect:/jsp/user/login";//로그인 페이지로 이동
	}
}
