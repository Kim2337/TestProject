package app.persistence;

import java.util.List;
import java.util.Map;

import app.domain.AppDTO;

public interface AppDAO {
		//결재현황게시판 처리
		void insertApp(AppDTO appdto) throws Exception;
		
		//결재 게시판 내용 불러오기
		List<AppDTO> selApp(String userid) throws Exception;
		
		List<AppDTO> appSearchw(Map<String,String> map) throws Exception;
		
		List<AppDTO> appSearchs(Map<String,String> map) throws Exception;
		
		List<AppDTO> appSearchf(Map<String,String> map) throws Exception;
		
		int appSearchwco(Map<String,String> map) throws Exception;
		
		int appSearchsco(Map<String,String> map) throws Exception;
		
		int appSearchfco(Map<String,String> map) throws Exception;
		
		int appCount(String userid) throws Exception;
		
		AppDTO appContent(Map<String,String> map) throws Exception;
}
