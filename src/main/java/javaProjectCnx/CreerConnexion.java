package javaProjectCnx;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpSession;


public class CreerConnexion {
	Connection cn = null;
	Statement st = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	String sql = "";
	
	public Connection etablirConnexion() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			cn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/prjcommerce","root","");
			//System.out.println("Bravo!!! connexion réussie");
			st = cn.createStatement();
		}catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return cn;
	}
	public void cloturerConnexion() {
		try {
			cn.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	public Users recupUserInfo(String login) {
		
		String fName = null;
		String lName = null;
		String adresse = null;
		String tel = null;
		int age = 0;
		String sexe = null;
		etablirConnexion();	
		sql = "SELECT * FROM users WHERE idUsers = ("
				+ "SELECT idUsers FROM compte WHERE login = '"+login+"'"
						+ ")";
		try {
			rs = st.executeQuery(sql);
			if(rs.next()) {
				fName = rs.getString("fname");
				lName = rs.getString("lname");
				adresse = rs.getString("adresse");
				tel = rs.getString("tel");
				age = rs.getInt("age");
				sexe = rs.getString("sexe");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Users user = new Users(fName, lName, adresse, tel, age, sexe);
		
		return user;
	}
	
	public String listCat() {
		String td = "";
		sql = "SELECT * FROM categorie";
		try {
			rs= st.executeQuery(sql);
			while(rs.next()) {
				td =  td + "<tr>"
							+ "<td>"+rs.getInt(1)+"</td>"
							+ "<td>"+rs.getString(2)+"</td>"
						 + "</tr>";
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return td;
	}
	
	public String recupTypeCompte(String login) {
		String type = null;
		etablirConnexion();
		sql = "SELECT type FROM compte WHERE login LIKE '"+
		login +"'";
		try {
			rs = st.executeQuery(sql);
			if(rs.next()) {
				type = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return type;
	}
	
	public Compte recupCompte(String login) {
		String type = null;
		int idCompte = 0;
		String pwd = null;
		int idUsers = 0;
		Compte compte = new Compte();
		etablirConnexion();
		sql = "SELECT * FROM compte WHERE login LIKE '"+
		login +"'";
		try {
			rs = st.executeQuery(sql);
			if(rs.next()) {
				idCompte = rs.getInt("idCompte");
				idUsers = rs.getInt("idUsers");
				pwd = rs.getString("pwd");
				type = rs.getString("type");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		compte = new Compte(login, pwd, type, idUsers);
		return compte;
	}
	
	public String verifierCoordonnees(String login) {
		String mdp = null;
		etablirConnexion();
		sql = "SELECT pwd FROM compte WHERE login LIKE '" + 
		login + "'";
		try {
			rs = st.executeQuery(sql);
			if(rs.next()) {
				mdp = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mdp;
	}
	public void ajouterCompte(Compte cp) throws SQLException {
		etablirConnexion();
		/*
		 * sql = "SELECT * FROM compte WHERE login LIKE '" + cp.getLogin() + "'"; rs =
		 * st.executeQuery(sql); if(!rs.next()) {
		 */	
			sql = "INSERT INTO compte(login,pwd,type,idUsers) VALUES('"
					+ cp.getLogin() + "','" + cp.getPwd() + "','" 
					+ cp.getType() + "'," + cp.getIdUsers() + ");";
			ps = cn.prepareStatement(sql);
			ps.execute();
			cloturerConnexion();
		//}
	}
	public void ajouterUsers(Users u) throws SQLException {
		etablirConnexion();
		sql = "INSERT INTO users(fname,lname,adresse,tel,age,sexe) "
				+ "VALUES('" + u.getfName() + "','" + u.getlName() +
				"','" + u.getAdresse() + "','" + u.getTel() + "'," +
				u.getAge() + ",'" + u.getSexe() + "')";
		ps = cn.prepareStatement(sql);
		ps.execute();
		cloturerConnexion();
	}
	public int idLastUsers() throws SQLException {
		etablirConnexion();
		int id = 0;
		sql = "SELECT MAX(idUsers) FROM users;";
		rs = st.executeQuery(sql);
		if(rs.next()) {
			id = rs.getInt(1);
		}
		cloturerConnexion();
		return id;
	}
	
	
	/*
	 * public static void main(String[] args) throws SQLException { CreerConnexion
	 * cc = new CreerConnexion(); cc.etablirConnexion(); //
	 * System.out.println(cc.idLastUsers()); //Compte c = new
	 * Compte("root","root",5); //cc.ajouterCompte(c);
	 * System.out.println(cc.recupUserInfo("root").getfName()); }
	 */
	 
	 
}