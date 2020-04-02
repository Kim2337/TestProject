package user.domain;

public class LoginDTO {//로그인 페이지에서 받아온 값을 처리하기위한 클래스
	private String userid;
	private String userpw;
	private boolean userCookie;//이건 왜 있는지 잘모르겠음..
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUserpw() {
		return userpw;
	}
	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}
	public boolean isUserCookie() {
		return userCookie;
	}
	public void setUserCookie(boolean userCookie) {
		this.userCookie = userCookie;
	}
	@Override
	public String toString() {
		return "LoginDTO [userid=" + userid + ", userpw=" + userpw + ", userCookie=" + userCookie + "]";
	}
	
}
