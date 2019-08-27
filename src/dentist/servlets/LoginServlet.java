/******************************
 * LoginServlet controls login events from the Login.jsp page. The servelt pulls
 * info from the textboxes of the Login.jsp page. Depending on user input, the
 * servlet directs the user to either the Dentist or Patient profile page.
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

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    /*************************
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     *************************/
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
            //Step 1: retrieve username and password from login.jsp
            String id, pw;
            Dentist d1 = new Dentist();
            Patient p1 = new Patient();
            id = request.getParameter("idbox");
            pw = request.getParameter("pwbox");
            System.out.println("Step 1: Login");
            System.out.println("ID: " + id);

            //Step 2: retrieve objects from the session
            //        no objects needed

            /******************
             * Step 3: Build a Dentist or Patient object using the username 
             * provided by the user. Usernames starting with 'd' or 'D' signify
             * Dentists. Usernames starting with 'p' or 'P' signify Patients.
             ******************/ 
            if (id.indexOf('d') == 0 || id.indexOf('D') == 0){
                d1.SelectDB(id);
                System.out.println("Step 3: Login");
                d1.display();

                //Step 4: make decisions
                //        no decisions needed to be made

                //Step 5: place the Dentist in the session to be retrieved later
                HttpSession ses1;
                ses1 = request.getSession();
                ses1.setAttribute("user", d1);
                System.out.println("Step 5: Login");
                System.out.println("Dentist added to session / forwarded to dentist_profile.jsp");
                
            }
            
            if (id.indexOf('a') == 0 || id.indexOf('A') == 0){
                p1.SelectDB(id);
                System.out.println("Step 3: Login");
                p1.display();

                //Step 4: make decisions
                //        no decisions needed to be made

                //Step 5: place the Patient in the session to be retrieved later
                HttpSession ses1;
                ses1 = request.getSession();
                ses1.setAttribute("user", p1);
                ses1.setAttribute("appt", p1.getAppt());
                System.out.println("Step 5: Login");
                System.out.println("Patient added to session / forwarded to patient_profile.jsp");
            } 

            //Step 6: use RequestDispatcher to forward the user to dentist_profile.jsp
            if (pw.equals(d1.getPass()) && !pw.equals("")){
                RequestDispatcher rd = request.getRequestDispatcher("/dentist_profile.jsp");
                rd.forward(request, response);
            } else if(pw.equals(p1.getPass()) && !pw.equals("")){
                RequestDispatcher rd = request.getRequestDispatcher("/patient_profile.jsp");
                rd.forward(request, response);
            } else{
                RequestDispatcher rd = request.getRequestDispatcher("/login_error.jsp");
                rd.forward(request, response);
            }
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
