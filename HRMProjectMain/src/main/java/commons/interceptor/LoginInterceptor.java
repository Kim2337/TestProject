package commons.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import user.domain.UserVO;


public class LoginInterceptor extends HandlerInterceptorAdapter {//특정 메서드 앞뒤로 실행할 인터셉터 dispatcher-servlet.xml의 <mvc:interceptors>내용 참고

	private static final String LOGIN = "login";
	private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);//콘솔에 로그 찍기 신경안써도됨
	
	//post = 메서드 끝나고 후반에 실행 
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,ModelAndView modelAndView) throws Exception {
		System.out.println("postHadle 실행");
		HttpSession httpSession = request.getSession();//로그인처리 메서드에서 가져온 세션
		ModelMap modelMap = modelAndView.getModelMap();//로그인처리 메서드에서 가져온 model 
		Object userVO = modelMap.get("user");//로그인처리 메서드에서 가져온 model에서 user라는 이름의 값을 가져옴
		if(userVO != null) {//만약 제대로 받아왔다면(null이 아니라면)
			logger.info("new login success");//성공 로그찍기
			httpSession.setAttribute(LOGIN, userVO);//세션에 login이라는 이름의 userVO값을 저장
			UserVO VO = (UserVO)modelMap.get("user");
			httpSession.setAttribute("name", VO.getUsername());
			

		}//end if
	} //postHandle메서드 끝
	
	//pre = 메서드 시작하기 전에 실행(세션 초기화 역할)
	//기존의 로그인 정보가 있을경우 제거하기위한 작업, 새롭게 로그인했을때
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println("preHandle");//preHandle시작
		HttpSession httpSession = request.getSession();//기존의 세션을 가져옴
		if(httpSession.getAttribute(LOGIN) != null) {//만약 기존 세션에 사용자정보가 있다면
			httpSession.removeAttribute(LOGIN);//기존의 정보를 지움
			logger.info("clear login data before");//확인용
		}
		
		return true;//불러온 메서드 그대로 실행
	}
	
	
}
