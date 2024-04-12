package web;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.catalina.connector.Response;
import dao.GroupeDaoImpl;
import dao.IGroupeDao;
import metier.entities.Groupe;
@WebServlet (name="gpServ",urlPatterns= {"/groupes","/saisieGroupe",
"/saveGroupe","/supprimerGroupe","/editerGroupe","/updateGroupe"})

public class GroupeServlet extends HttpServlet {
IGroupeDao metier;
@Override
public void init() throws ServletException {
metier = new GroupeDaoImpl();
}
@Override
protected void doGet(HttpServletRequest request,
HttpServletResponse response)
throws ServletException, IOException {
String path=request.getServletPath();
System.out.println("PATH "+path);
if (path.equals("/groupes"))
{
	GroupeModel model= new GroupeModel();
List<Groupe> gps = metier.getAllGroupe();
model.setGroupes(gps);
request.setAttribute("model", model);
request.getRequestDispatcher("groupes.jsp").forward(request,response);
}
else if (path.equals("/saisieGroupe") )
{
request.getRequestDispatcher("saisieGroupe.jsp").forward(request,response
);
}
else if (path.equals("/saveGroupe") &&

request.getMethod().equals("POST"))

{
Date dateGroupe= new Date();
String nomGroupe=request.getParameter("nomGroupe");
String pattern = "yyyy-MM-dd";
SimpleDateFormat simpleDateFormat = new

SimpleDateFormat(pattern);
try {

dateGroupe =

simpleDateFormat.parse(request.getParameter("dateGroupe"));

} catch (ParseException e) {
e.printStackTrace();
}
Groupe gp = metier.save(new Groupe(nomGroupe,dateGroupe));
request.setAttribute("groupe", gp);
response.sendRedirect("groupes");
}
else if (path.equals("/supprimerGroupe"))
{
Long id= Long.parseLong(request.getParameter("id"));
metier.deleteGroupe(id);
response.sendRedirect("groupes");
}
else if (path.equals("/editerGroupe") )
{
Long id= Long.parseLong(request.getParameter("id"));
Groupe gp = metier.getGroupe(id);
request.setAttribute("groupe", gp);
request.getRequestDispatcher("editerGroupe.jsp").forward(request,response
);
}
else if (path.equals("/updateGroupe") )
{
Date dateGroupe= new Date();
Long id = Long.parseLong(request.getParameter("idGroupe"));
String nom=request.getParameter("nom");
Groupe gp = new Groupe();
gp.setIdGroupe(id);
gp.setNomGroupe(nom);
String pattern = "yyyy-MM-dd";
SimpleDateFormat simpleDateFormat = new

SimpleDateFormat(pattern);
try {

dateGroupe =

simpleDateFormat.parse(request.getParameter("dateCreation"));

} catch (ParseException e) {
e.printStackTrace();
}
gp.setDateCreation(dateGroupe);
metier.updateGroupe(gp);
response.sendRedirect("groupes");
}
else
{
response.sendError(Response.SC_NOT_FOUND);
}
}
@Override
protected void doPost(HttpServletRequest request,

HttpServletResponse response) throws

ServletException, IOException {
doGet(request,response);
}
}
