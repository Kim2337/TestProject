package app.service;

import java.util.List;
import java.util.Map;

import app.domain.AppDTO;

public interface AppService {
void insertapp(AppDTO appdto) throws Exception;
	
	List<AppDTO> selapp(String userid) throws Exception;
	
	List<AppDTO> appsearchw(Map<String,String> map) throws Exception;
	
	List<AppDTO> appsearchs(Map<String,String> map) throws Exception;
	
	List<AppDTO> appsearchf(Map<String,String> map) throws Exception;
	
	int appCo(String userid) throws Exception;
	
	AppDTO appcontent(Map<String,String> map) throws Exception;
	
	int appSearchwco(Map<String,String> map) throws Exception;
	
	int appSearchsco(Map<String,String> map) throws Exception;
	
	int appSearchfco(Map<String,String> map) throws Exception;
	
}
