package javaProjectCnx;

public class Compte {
	private int idCompte;
	private String login;
	private String pwd;
	private String type;
	private int idUsers;
	
	
	public Compte(String login, String pwd, int idUsers) {
		super();
		this.login = login;
		this.pwd = pwd;
		this.idUsers = idUsers;
		this.type = "s";
	}

	public Compte(String login, String pwd, String type, int idUsers) {
		super();
		this.login = login;
		this.pwd = pwd;
		this.type = type;
		this.idUsers = idUsers;
	}
	
	public Compte() { 
		super();
		this.login = null; 
		this.pwd = null; 
		this.idUsers = 0; 
		this.type = null; 
	}
	 
	
	public int getIdUsers() {
		return idUsers;
	}

	public void setIdUsers(int idUsers) {
		this.idUsers = idUsers;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public int getIdCompte() {
		return idCompte;
	}

	
}
