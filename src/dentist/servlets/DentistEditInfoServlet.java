/******************************
 * DentistEditInfoServlet retrieves data from the dentist_profile.jsp page and uses it
 * to update the properties of the Dentist object in the session.
 ******************************/
package dentist.servlets;

import dentist.businessobjects.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author THE PATRICKS
 */
@WebServlet(name = "DentistEditInfoServlet", urlPatterns = {"/DentistEditInfoServlet"})
public class DentistEditInfoServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession ses1;
        ses1 = request.getSession();
        
        //Step 1: Get info from textboxes from dentist_profile.jsp
        String fn, ln, email, office;
        fn = request.getParameter("fnbox");
        ln = request.getParameter("lnbox");
        email = request.getParameter("emailbox");
        office = request.getParameter("officebox");
        System.out.println("Step 1: Grab Info Complete");

        //Step 2: Retrieve objects from the session
        Dentist d1 = (Dentist)ses1.getAttribute("user");
        System.out.println("Step 2: Retrieve Dentist Complete");

        //Step 3: Update Dentist information using the info provided from
        //        the textboxes and Dentist's UpdateDB method.
        d1.setFname(fn);
        d1.setLname(ln);
        d1.setEmail(email);
        d1.setOffice(office);
        d1.UpdateDB();
        System.out.println("Step 3: Update Dentist Info Complete");
        d1.display();

        //Step 4: make decisions
        //        no decisions needed to be made

        //Step 5: place the Patient in the session to be retrieved later
        ses1.setAttribute("user", d1);
        System.out.println("Step 5: Send Dentist back to the Session Complete");
        System.out.println("Patient added to session / forwarded to dentist_profile.jsp");

        //Step 6: use RequestDispatcher to forward the user to dentist_profile.jsp
        RequestDispatcher rd = request.getRequestDispatcher("/dentist_profile.jsp");
        rd.forward(request, response);           
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
