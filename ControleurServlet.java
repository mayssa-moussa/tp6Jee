package web;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;

import dao.IEtudiantDao;
import dao.IGroupeDao;
import dao.EtudiantDaoImpl;
import dao.GroupeDaoImpl;
import metier.entities.Etudiant;
import metier.entities.Groupe;
@WebServlet (name="cs",urlPatterns= {"/controleur","*.do"})
public class ControleurServlet extends HttpServlet {
    IEtudiantDao metier;
    IGroupeDao metierGp;
    @Override
    public void init() throws ServletException {
        metier = new EtudiantDaoImpl();
        metierGp = new GroupeDaoImpl();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path=request.getServletPath();
        if (path.equals("/index.do")) {
            request.getRequestDispatcher("Etudiant.jsp").forward(request,response);
        }
        else if (path.equals("/chercher.do")) {
            String motCle=request.getParameter("motCle");
            EtudiantModele model= new EtudiantModele();
            model.setMotCle(motCle);
            List<Etudiant> etuds = metier.etudiantParMC(motCle);
            model.setEtudiants(etuds);
            request.setAttribute("model", model);
            request.getRequestDispatcher("Etudiant.jsp").forward(request,response);
        }
        else if (path.equals("/saisie.do") ) {
            List<Groupe> gps = metierGp.getAllGroupe();
            GroupeModel model= new GroupeModel();
            model.setGroupes(gps);
            request.setAttribute("gpModel", model);
            request.getRequestDispatcher("saisieEtudiant.jsp").forward(request,response);
        }
        else if (path.equals("/save.do") && request.getMethod().equals("POST")) {
            String nom=request.getParameter("nom");
            String prenom =request.getParameter("prenom");
            String dateNaiss=request.getParameter("dateNaiss");
            String adresse=request.getParameter("adresse");
            String email=request.getParameter("email");
            int Annee=Integer.parseInt(request.getParameter("annee-etudes"));
            String dep=request.getParameter("dep");
            Etudiant p = metier.save(new Etudiant(nom,prenom,dateNaiss,adresse,email,Annee,dep));
            request.setAttribute("etudiant", p);
            request.getRequestDispatcher("confirmation.jsp").forward(request,response);
        }
        else if (path.equals("/supprimer.do")) {
            Long id= Long.parseLong(request.getParameter("id"));
            metier.deleteEtudiant(id);
            response.sendRedirect("chercher.do?motCle=");
        }
        else if (path.equals("/editer.do") ) {
            Long id= Long.parseLong(request.getParameter("idGroupe"));
            Etudiant e= metier.getEtudiant(id);
            request.setAttribute("etudiant", e);
            List<Groupe> gps = metierGp.getAllGroupe();
            GroupeModel model= new GroupeModel();
            model.setGroupes(gps);
            request.setAttribute("gpModel", model);
            request.getRequestDispatcher("editerEtudiant.jsp").forward(request,response);
        }
        else if (path.equals("/update.do") ) {
            Long id = Long.parseLong(request.getParameter("id"));
            String nom=request.getParameter("nom");
            String prenom=request.getParameter("prenom");
            String dateNaiss =request.getParameter("dateNaiss");
            String adresse =request.getParameter("adresse");
            String email =request.getParameter("email");
            int anneeEtudes = Integer.parseInt(request.getParameter("anneeEtudes"));
            String dep=request.getParameter("dep");
            Long groupeId=Long.parseLong(request.getParameter("groupe"));
            Etudiant  e = new Etudiant();
            e.setIdEtudiant(id);
            e.setNom(nom);
            e.setPrenom(prenom);
            e.setDateNaiss(dateNaiss);
            e.setAdresse(adresse);
            e.setEmail(email);
            e.setAnneeEtudes(anneeEtudes);
            e.setDep(dep);
            Groupe gp = metierGp.getGroupe(groupeId);
            e.setGroupe(gp);
            metier.updateEtudiant(e);
            response.sendRedirect("chercher.do?motCle=");
        }
        else {
            response.sendError(Response.SC_NOT_FOUND);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}


