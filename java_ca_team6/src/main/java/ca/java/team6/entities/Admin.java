package ca.java.team6.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Admin {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="UserId")
	private long userId;
	
	@Column(name="Username", length=50)
	private String userName;
	
	@Column(name="Password(Hash)", length=50)
	private String passWord;
	
	public Admin()
	{
		
	}
	
	public Admin(String userName, String passWord)
	{
		this.userName = userName;
		this.passWord = passWord;
	}
	
	public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassWord() {
		return passWord;
	}
	
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
	@Override
	public String toString() {
		return "Admin [userId=" + userId + ", userName=" + userName + ", passWord=" + passWord + "]";
	}
	
	
}
