/******************************
 * EditApptServlet retrieves data from the patient_profile.jsp page and uses it
 * to update the properties of the Appointment object in the session.
 ******************************/
package dentist.servlets;

import dentist.businessobjects.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "EditApptServlet", urlPatterns = {"/EditApptServlet"})
public class EditApptServlet extends HttpServlet {

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
        System.out.println("Begin Updating Appointment");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession ses1;
        ses1 = request.getSession();
        
        //Step 1: Get info from textboxes from patient_profile.jsp
        String oldAppt, newAppt, dent, proc;
        oldAppt = request.getParameter("date");
        newAppt = request.getParameter("timebox");
        if (newAppt.equals("")){newAppt = oldAppt;}
        dent = request.getParameter("dentist");
        proc = request.getParameter("procedure");
        System.out.println("Step 1: Grab Info Complete");

        //Step 2: Retrieve objects from the session
        Patient p1 = (Patient)ses1.getAttribute("user");
        Appointment appt = (Appointment)ses1.getAttribute("appt");
        System.out.println("Step 2: Retrieve Patient Complete");

        //Step 3: Update Patient information using the info provided from
        //        the textboxes and Patient's UpdateDB method.
        appt.DeleteDB();
        appt.InsertDB(newAppt, p1.getId(), dent, proc);
        System.out.println("Step 3: Update Appointment Info Complete");
        appt.display();

        //Step 4: make decisions
        //        no decisions needed to be made

        //Step 5: Place the Business Objects in the session to be retrieved later
        ses1.setAttribute("appt", appt);
        ses1.setAttribute("user", p1);
        System.out.println("Step 5: Send Procedure to the Session Complete");
        System.out.println("Procedure added to session / forwarded to patient_profile.jsp");

        //Step 6: use RequestDispatcher to forward the user to patient_profile.jsp
        RequestDispatcher rd = request.getRequestDispatcher("/patient_profile.jsp");
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
