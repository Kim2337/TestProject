package user.domain;

import java.sql.Timestamp;

public class UserVO {

	private int empno;
	private String userid;
	private String userpw;
	private String username;
	private String useremail;
	private String userphonenum;
	private Timestamp hiredate;
	private Timestamp birth;
	private int approval;
	private String level;
	private int deptno;
	public int getEmpno() {
		return empno;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUseremail() {
		return useremail;
	}
	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}
	public String getUserphonenum() {
		return userphonenum;
	}
	public void setUserphonenum(String userphonenum) {
		this.userphonenum = userphonenum;
	}
	public Timestamp getHiredate() {
		return hiredate;
	}
	public void setHiredate(Timestamp hiredate) {
		this.hiredate = hiredate;
	}
	public Timestamp getBirth() {
		return birth;
	}
	public void setBirth(Timestamp birth) {
		this.birth = birth;
	}
	public int getApproval() {
		return approval;
	}
	public void setApproval(int approval) {
		this.approval = approval;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public int getDeptno() {
		return deptno;
	}
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
	@Override
	public String toString() {
		return "UserVO [empno=" + empno + ", userid=" + userid + ", userpw=" + userpw + ", username=" + username
				+ ", useremail=" + useremail + ", userphonenum=" + userphonenum + ", hiredate=" + hiredate + ", birth="
				+ birth + ", approval=" + approval + ", level=" + level + ", deptno=" + deptno + "]";
	}
	
	
	
}
