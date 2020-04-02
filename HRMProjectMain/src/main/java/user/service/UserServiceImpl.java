package user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import user.domain.LoginDTO;
import user.domain.UserVO;
import user.persistence.UserDAO;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO userDAO; 
	
	//회원가입처리
	@Override
	public void register(UserVO userVO) throws Exception {
		userDAO.register(userVO);
	}

	@Override
	public UserVO login(LoginDTO loginDTO) throws Exception {
		return userDAO.login(loginDTO);
	}

}
