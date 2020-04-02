package app.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Nullable;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import app.service.AppService;
import app.domain.AppDTO;
import user.domain.UserVO;

@Controller
@RequestMapping("/approve")
public class ApproveController implements ApplicationContextAware{
	
	@Autowired
	private AppService appService; 
	private WebApplicationContext context = null;
	
	@RequestMapping(value = "/appboard", method = RequestMethod.GET)
	public String appboardGET(HttpSession hs,Model m, int pageNum){
		UserVO userVO = (UserVO)hs.getAttribute("login");
		int count;
		try {
			count = appService.appCo(userVO.getUserid());
			System.out.println(count);
			if(pageNum==0) {pageNum=pageNum+1;}
			if(count==0) {count=1;}
			m.addAttribute("count",count);
			m.addAttribute("pageNum",pageNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "approve/appboard";
	}
	
	@RequestMapping(value = "/appwrite", method = RequestMethod.GET)
	public String appwriteGET(HttpSession hs,Model m){
		UserVO userVO = (UserVO)hs.getAttribute("login");
		m.addAttribute("writer", userVO.getUsername());
		return "approve/appwrite";
	}
	
	@RequestMapping(value = "/appwrite", method = RequestMethod.POST)
	public String appwrite(AppDTO appdto, HttpSession hs,@Nullable MultipartFile appfiles, Model m) throws Exception {
		UserVO userVO = (UserVO)hs.getAttribute("login");
		appdto.setUserid(userVO.getUserid());
		if(appfiles.getSize()!=0) {
			String fileName = appfiles.getOriginalFilename();
			String extension = fileName.substring(fileName.lastIndexOf("."), fileName.length());
			UUID uuid = UUID.randomUUID();
			String newFileName = uuid.toString() + extension;
			File file = new File("D://uploads/"+newFileName);
			appfiles.transferTo(file);
			appdto.setPathname("D://uploads/"+newFileName);
			appdto.setRealfilename(appfiles.getOriginalFilename());
		}else {
			appdto.setRealfilename("");
			appdto.setPathname("");
		}
		appService.insertapp(appdto);
		m.addAttribute("pageNum", 1);
		return"redirect:appboard";
	}
	
	//ajax ready 
	@RequestMapping(value = "/applist", method = RequestMethod.POST)
	@ResponseBody
	public String appboardPOST(HttpSession hs) throws Exception {
		UserVO userVO = (UserVO)hs.getAttribute("login");
		List<AppDTO> list = appService.selapp(userVO.getUserid());
		Gson json = new Gson(); 		
		return json.toJson(list);
	}
	
	//ajax search ready 
	@RequestMapping(value = "/applistsearch", method = RequestMethod.POST)
	@ResponseBody
	public String appboardsearchPOST(HttpSession hs, int selector,String seltext, int pageNum, Model m) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		List<AppDTO> list;
		UserVO userVO = (UserVO)hs.getAttribute("login");
		map.put("userid",userVO.getUserid());
		if(selector==0) {
			map.put("writer", "%"+seltext+"%");
			list = appService.appsearchw(map);
		}else if(selector==1) {
			map.put("subject", "%"+seltext+"%");
			list = appService.appsearchs(map);
		}else {
			map.put("realfilename", "%"+seltext+"%");
			list = appService.appsearchf(map);
		}
		m.addAttribute("pageNum", pageNum);
		m.addAttribute("selector",selector);
		m.addAttribute("seltext",seltext);
		Gson json = new Gson(); 		
		return json.toJson(list);
	}
	
	//search
	@RequestMapping(value = "/selectArticle")
	public String selectArticle(HttpSession hs,int pageNum, int selector,String seltext, Model m) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		int count;
		UserVO userVO = (UserVO)hs.getAttribute("login");
		map.put("userid",userVO.getUserid());
		if(selector==0) {
			map.put("writer", "%"+seltext+"%");
			count = appService.appSearchwco(map);
		}else if(selector==1) {
			map.put("subject", "%"+seltext+"%");
			count = appService.appSearchsco(map);
		}else {
			map.put("realfilename", "%"+seltext+"%");
			count = appService.appSearchfco(map);
		}
		try {
			System.out.println(count);
			if(pageNum==0) {
				pageNum=pageNum+1;
			}
			m.addAttribute("count",count);
			m.addAttribute("pageNum",pageNum);
			m.addAttribute("selector",selector);
			m.addAttribute("seltext",seltext);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "approve/appboardsearch";
	}

	
	//글 내용
	@RequestMapping(value = "/appcontent",method = RequestMethod.GET)
	public String appcontent(HttpSession hs,int appnum, Model m) {
		UserVO userVO = (UserVO)hs.getAttribute("login");
		Map<String,String> map = new HashMap<String, String>();
		map.put("userid", userVO.getUserid());
		map.put("appnum", Integer.toString(appnum));
		try {
			AppDTO appdto = appService.appcontent(map);
			m.addAttribute("appdto",appdto);
			m.addAttribute("appresult",appdto.getAppresult());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "approve/appcontent";
	}
	
	@RequestMapping("/file")//download
	public ModelAndView download(HttpSession hs,int appnum, Model m) throws Exception {
		UserVO userVO = (UserVO)hs.getAttribute("login");
		String userid = userVO.getUserid();
		File downloadFile = getFile(userid,appnum,m);
		return new ModelAndView("download", "downloadFile", downloadFile);//빈네임 뷰리졸버 찾아감(빈네임,KEY,VALUE)
	}

	private File getFile(String userid, int appnum, Model m) {
		Map<String,String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("appnum", Integer.toString(appnum));
		AppDTO appdto;
		try {
			appdto = appService.appcontent(map);
			m.addAttribute("realfilename", appdto.getRealfilename());
			return new File(appdto.getPathname());
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.context = (WebApplicationContext) applicationContext;
	}//ApplicationContextAware 이걸 구현하고있다면 setter 자동호출
	
}
