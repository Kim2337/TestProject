package commons.view;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

public class AppDownloadView extends AbstractView {//class방식 view 구현 //컨트롤러에서 보내주면 자동으로 render메서드 실행

	public AppDownloadView() {//생성자
		setContentType("application/download; charset=utf-8");//뭐의 컨텐츠타입이 set되는거? 걍 보이지않는 변수에 저장되는건가?
	}

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model,//model은 맵을 상속받으니 가능
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		File file = (File) model.get("downloadFile");

		response.setContentType(getContentType());//다운받을 파일 컨텐츠 타입 지정
		response.setContentLength((int)file.length());//다운받을 파일 크기 지정

		String userAgent = request.getHeader("User-Agent");//사용자의 사용 브라우저 종류를 받아옴


		boolean ie = userAgent.indexOf("MSIE") > -1; //인터넷익스플로러라면 true 아니라면 false
		String fileName = null;
		if (ie) { 
			fileName = URLEncoder.encode(file.getName(), "utf-8");//ie라면
		} else {
			fileName = new String(file.getName().getBytes("utf-8"),"iso-8859-1");//다른 브라우저라면
		}
		response.setHeader("Content-Disposition", "attachment; filename=\""+ (String)model.get("realfilename") + "\";");
		//Content-Disposition라는 이름의 속성값을 "attachment; filename=\""+ fileName + "\";" 이렇게 설정하겠다.

		response.setHeader("Content-Transfer-Encoding", "binary");
		//Content-Transfer-Encoding라는 이름의 속성값을 binary로 설정하겠다.
		
		OutputStream out = response.getOutputStream();

		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			FileCopyUtils.copy(fis, out);
		} finally {
			if (fis != null)
				try {
					fis.close();
				} catch (IOException ex) {
				}
		}
		out.flush();//찌꺼기 처리
	}

}
