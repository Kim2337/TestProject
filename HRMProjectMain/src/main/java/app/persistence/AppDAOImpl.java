package app.persistence;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import app.domain.AppDTO;

public class AppDAOImpl extends SqlSessionDaoSupport implements AppDAO {
	
	private static final String NAMESPACEAPP = "app.AppMappers";
	
		//결재현황 게시판 처리
		@Override
		public void insertApp(AppDTO appdto) throws Exception {
			getSqlSession().insert(NAMESPACEAPP+".insertApp",appdto);
			
		}
		
		//결재게시판 내용 불러오기
		@Override
		public List<AppDTO> selApp(String userid) throws Exception {
			return getSqlSession().selectList(NAMESPACEAPP+".appBoard", userid);
			
		}
		
		//결제 게시글 가져오기
		@Override
		public int appCount(String userid) throws Exception {
			return getSqlSession().selectOne(NAMESPACEAPP+".appcount",userid);
		}

		@Override
		public List<AppDTO> appSearchw(Map<String, String> map) throws Exception {
			return getSqlSession().selectList(NAMESPACEAPP+".appSearchw", map);
		}

		@Override
		public List<AppDTO> appSearchs(Map<String, String> map) throws Exception {	
			return getSqlSession().selectList(NAMESPACEAPP+".appSearchs", map);
		}

		@Override
		public List<AppDTO> appSearchf(Map<String, String> map) throws Exception {
			return getSqlSession().selectList(NAMESPACEAPP+".appSearchf", map);
		}

		@Override
		public AppDTO appContent(Map<String, String> map) throws Exception {
			return getSqlSession().selectOne(NAMESPACEAPP+".appcontent", map);
		}

		@Override
		public int appSearchwco(Map<String, String> map) throws Exception {
			return getSqlSession().selectOne(NAMESPACEAPP+".appSearchwco", map);
		}

		@Override
		public int appSearchsco(Map<String, String> map) throws Exception {
			return getSqlSession().selectOne(NAMESPACEAPP+".appSearchsco", map);
		}

		@Override
		public int appSearchfco(Map<String, String> map) throws Exception {
			return getSqlSession().selectOne(NAMESPACEAPP+".appSearchfco", map);
		}


}
