package javaProjectCnx;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

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
	public String idCatToString(int idCat) {
		String nomCat = null;
		sql = "SELECT designation FROM categorie WHERE idCategorie = "+idCat;
		try {
			rs = st.executeQuery(sql);
			if(rs.next()) {
				nomCat = rs.getString(1);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return nomCat;
	}
	public List<Article> listArt() {
		List<Article> td2 = new ArrayList<Article>();
		Article article = new Article();
		
		sql = "SELECT * FROM article";
		try {
			rs= st.executeQuery(sql);
			while(rs.next()) {
				//td2.add(rs.getInt(1));
				article = new Article(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getInt(4), rs.getInt(5));
				td2.add(article);				
				/*
				 * td2 = td2 + "<tr>" + "	<td>"+ rs.getInt(1) +"</td>" + "	<td>"+
				 * rs.getString(2) +"</td>" + "	<td>"+ Math.round(rs.getFloat(3)*1)/1 +"</td>" +
				 * "	<td>"+ rs.getInt(4) +"</td>" + "	<td>"+
				 * this.idCatToString(rs.getInt(5)) +"</td>" + "	</tr>";
				 */
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return td2;
	}
	public String listCatChoix() {
		String choix = "";
		sql = "SELECT * FROM categorie";
		try {
			rs= st.executeQuery(sql);
			while(rs.next()) {
				choix =  choix + "<option value=\""+rs.getInt(1)+"\">"+rs.getString(2)+"</option>";
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return choix;
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
	public void ajoutCat(String nomCat, int idCat) throws SQLException {
		// TODO Auto-generated method stub
		etablirConnexion();

			sql = "INSERT INTO categorie "
					+ "VALUES("+idCat+", '"+nomCat+"')";
			ps = cn.prepareStatement(sql);
			ps.execute();

		cloturerConnexion();
		
	}
	public void ajoutArt(String nomArt, float prixU, int quantite, int cat) throws SQLException {
		// TODO Auto-generated method stub
		etablirConnexion();

		sql = "INSERT INTO article(designation, pu, qty, idCategorie) "
				+ "VALUES('"+nomArt+"', '"+prixU+"', "+quantite+", "+cat+")";
		ps = cn.prepareStatement(sql);
		ps.execute();

	cloturerConnexion();
	}
	public void ajoutArtHibernate(Article1 a) {
		// TODO Auto-generated method stub
		Configuration configuration = new Configuration().configure();
		SessionFactory sf = configuration.buildSessionFactory();
		Session session = sf.openSession();
		Transaction tr = session.beginTransaction();
		session.persist(a);
		tr.commit();
		session.close();
		sf.close();
	}
	
	
	/*
	 * public static void main(String[] args) throws SQLException { CreerConnexion
	 * cc = new CreerConnexion(); cc.etablirConnexion(); //
	 * System.out.println(cc.idLastUsers()); //Compte c = new
	 * Compte("root","root",5); //cc.ajouterCompte(c);
	 * System.out.println(cc.recupUserInfo("root").getfName()); }
	 */
	 
	 
}