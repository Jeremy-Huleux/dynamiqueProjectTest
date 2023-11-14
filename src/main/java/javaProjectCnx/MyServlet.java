package javaProjectCnx;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

/**
 * Servlet implementation class MyServlet
 */
//@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
	String login = "";
	private static final long serialVersionUID = 1L;
    CreerConnexion cc = new CreerConnexion();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String flag = request.getParameter("flag");
		
		if(flag.equalsIgnoreCase("connect")) {
			HttpSession session = request.getSession(true);
			Users u = new Users();
			session.setAttribute("login", login);
			u = this.doConnexion(request,response);			
		}else {
			if(flag.equalsIgnoreCase("inscrit")) {
				try {
					this.doInscription(request, response);
				} catch (ServletException | IOException | SQLException e) {
					e.printStackTrace();
				}
			}else if(flag.equalsIgnoreCase("ajoutCat")){
				this.doAjoutCat(request, response);
			}else if(flag.equalsIgnoreCase("ajoutArt")){
				this.doAjoutArt(request, response);
			}else {
					this.doGet(request, response);
				}
		}
	}

	private void doAjoutArt(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nomArt = request.getParameter("nomArt");
		float prixU = Float.parseFloat(request.getParameter("prixU"));
		int quantite = Integer.parseInt(request.getParameter("quantite"));
		int cat = Integer.parseInt(request.getParameter("cat"));
		try {
			cc.ajoutArt(nomArt, prixU, quantite, cat);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/ajoutSupprArt.jsp").forward(request, response);
	}

	private void doAjoutCat(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nomCat = request.getParameter("nomCat");
		int idCat = Integer.parseInt(request.getParameter("idCat"));
		try {
			cc.ajoutCat(nomCat, idCat);
			
		}catch(SQLException e) {
			e.printStackTrace();

		}
		request.getRequestDispatcher("/listecat.jsp").forward(request, response);
	}

	private void doInscription(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		HttpSession session = request.getSession(true);
		
		// sauvegarde du résultat de validation
		String resultat;
		Map<String,String> erreurs = new HashMap<String,String>();
		// Récupérer les valeurs des champs à partir du JSP
		String fname = request.getParameter("firstName");
		String lname = request.getParameter("lastName");
		String adresse = request.getParameter("adresse");
		String tel = request.getParameter("tel");
		int age = Integer.parseInt(request.getParameter("age"));
		String[] tabRadio = request.getParameterValues("sexe");
		String login = request.getParameter("login");
		String pwd = request.getParameter("pwd");
		String pwd2 = request.getParameter("pwd2");
		// récupérer la valeur du bouton radio
		String sexe = "";
		for(int i = 0; i < tabRadio.length; i++) {
			if(tabRadio[i] != null) {
				sexe = tabRadio[i];
			}
		}
		
		
		// Contrôle des champs
		try {
			this.validationAge(age);
		} catch (Exception e) {
			erreurs.put(""+age, e.getMessage());
		}
		try {
			this.validationNomination(fname);
		} catch (Exception e) {
			erreurs.put(fname, e.getMessage());
		}
		try {
			this.validationNomination(lname);
		} catch (Exception e) {
			erreurs.put(lname, e.getMessage());
		}
		try {
			this.validationMotsDePasse(pwd, pwd2);
		} catch (Exception e) {
			erreurs.put(pwd, e.getMessage());
		}
		// Résultat global des validations
		if(erreurs.isEmpty()) {
			resultat = "Succès de l'inscription";
			// INSERT INTO
			Users u = new Users(fname,lname,adresse,tel,age,sexe);
			cc.ajouterUsers(u);
			int idUsers = cc.idLastUsers();
			Compte c = new Compte(login, pwd, idUsers);
			cc.ajouterCompte(c);
		}else {
			resultat = "Échec d'inscription";
		}
		request.setAttribute("erreurs", erreurs);
		request.setAttribute("resultat", resultat);
		request.getRequestDispatcher("/inscription.jsp").forward(request, response);
	}

	// Vérification de l'âge
	public void validationAge(int age) throws Exception {
		if(age < 15 || age > 120) {
			throw new Exception("L'age doit etre compris entre 15 et 120");
		}
	}
	// Vérification du nom/prénom
	public void validationNomination(String ch) throws Exception {
		if(ch != null && ch.trim().length() < 3) {
			throw new Exception("Le champ doit être d'au moins 3 caractères");
		}
	}
	// Vérification du mot de passe et de sa confirmation
	public void validationMotsDePasse(String motDePasse, String confirmation) throws Exception {
		if(motDePasse != null && motDePasse.trim().length() != 0 &&
				confirmation != null && confirmation.trim().length() != 0) {
			if(!motDePasse.equals(confirmation)) {
				throw new Exception("les mots des passe ne sont pas les mêmes");
			}else {
				if(motDePasse.trim().length() < 3) {
					throw new Exception("Longueur insuffisant pour mots des passe");
				}
			}
		}else {
			throw new Exception("SVP saisir un mot des passe");
		}
	}
	// Vérification du pseudo
	public void validationPseudo(String pseudo) throws Exception {
		if(pseudo != null && pseudo.trim().length() != 0) {
			if(!pseudo.matches(
					"([^.@])(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
				throw new Exception("Pseudo non valide");	
			}
		}else {
			throw new Exception("SVP saisir un pseudo");
		}
	}
	private Users doConnexion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		login = request.getParameter("pseudo");
		String pwd = request.getParameter("mdp");
		String pwdBDD = cc.verifierCoordonnees(login);
		Users user = new Users();
		String type = null;
		Compte compte = new Compte();
		

		if(pwdBDD == null) {
			request.getRequestDispatcher("/connexionKO.jsp").forward(request, response);
		}else {
			if(pwd.equals(pwdBDD)) {
				user = cc.recupUserInfo(login);
				compte = cc.recupCompte(login);
				session.setAttribute("user", user);
				session.setAttribute("compte", compte);
				request.getRequestDispatcher("/connexionOK.jsp").forward(request, response);
			}else {
				request.getRequestDispatcher("/connexionKO.jsp").forward(request, response);
			}
		}
	return user;
	}
	
}
