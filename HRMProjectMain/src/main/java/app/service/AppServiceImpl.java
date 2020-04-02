package app.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.persistence.AppDAO;
import app.domain.AppDTO;

@Service
public class AppServiceImpl implements AppService {
	
	@Autowired
	private AppDAO appDAO; 
	
	@Override
	public void insertapp(AppDTO appdto) throws Exception {
		appDAO.insertApp(appdto);
	}

	@Override
	public List<AppDTO> selapp(String userid) throws Exception {
		return appDAO.selApp(userid);
	}

	@Override
	public int appCo(String userid) throws Exception {
		return appDAO.appCount(userid);
	}

	@Override
	public List<AppDTO> appsearchw(Map<String, String> map) throws Exception {
		return appDAO.appSearchw(map);
	}

	@Override
	public List<AppDTO> appsearchs(Map<String, String> map) throws Exception {
		return appDAO.appSearchs(map);
	}

	@Override
	public List<AppDTO> appsearchf(Map<String, String> map) throws Exception {
		return appDAO.appSearchf(map);
	}

	@Override
	public AppDTO appcontent(Map<String, String> map) throws Exception {
		return appDAO.appContent(map);
	}

	@Override
	public int appSearchwco(Map<String, String> map) throws Exception {
		return appDAO.appSearchwco(map);
	}

	@Override
	public int appSearchsco(Map<String, String> map) throws Exception {
		return appDAO.appSearchsco(map);
	}

	@Override
	public int appSearchfco(Map<String, String> map) throws Exception {
		return appDAO.appSearchfco(map);
	}
}