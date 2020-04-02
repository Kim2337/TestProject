package app.domain;

import java.util.Date;


public class AppDTO {

	private int appnum;
	private String userid;
	private String subject;
	private Date writedate;
	private int appresult;
	private String realfilename;
	private String pathname;
	private String writer;
	private String content;
	
	public int getAppnum() {
		return appnum;
	}
	public void setAppnum(int appnum) {
		this.appnum = appnum;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public Date getWritedate() {
		return writedate;
	}
	public void setWritedate(Date writedate) {
		this.writedate = writedate;
	}
	public int getAppresult() {
		return appresult;
	}
	public void setAppresult(int appresult) {
		this.appresult = appresult;
	}
	public String getRealfilename() {
		return realfilename;
	}
	public void setRealfilename(String realfilename) {
		this.realfilename = realfilename;
	}
	public String getPathname() {
		return pathname;
	}
	public void setPathname(String pathname) {
		this.pathname = pathname;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "AppDTO [appnum=" + appnum + ", userid=" + userid + ", subject=" + subject + ", writedate=" + writedate
				+ ", appresult=" + appresult + ", realfilename=" + realfilename + ", pathname=" + pathname + ", writer="
				+ writer + ", content=" + content + "]";
	}


}