package user.service;

import user.domain.LoginDTO;
import user.domain.UserVO;

public interface UserService {
	
	void register(UserVO userVO) throws Exception;
	
	UserVO login(LoginDTO loginDTO) throws Exception;

	
}
