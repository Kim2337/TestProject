package user.persistence;


import org.mybatis.spring.support.SqlSessionDaoSupport;

import user.domain.LoginDTO;
import user.domain.UserVO;

public class UserDAOImpl extends SqlSessionDaoSupport implements UserDAO {

	private static final String NAMESPACE = "user.UserMapper";
	
	//회원가입처리
	@Override
	public void register(UserVO userVO) throws Exception {
		getSqlSession().insert(NAMESPACE + ".register", userVO);
	}
	
	//로그인처리
	@Override
	public UserVO login(LoginDTO loginDTO) throws Exception {
		return getSqlSession().selectOne(NAMESPACE+".login",loginDTO);
	}
	

}
