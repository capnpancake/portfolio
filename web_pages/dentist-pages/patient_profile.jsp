<%-- 
    Document   : patient_profile
    Created on : Jul 22, 2018, 3:53:48 PM
    Author     : THE PATRICKS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="BusinessObjects.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Patient Profile</title>
        
        <style>
            .flexbox{
                display: flex;
                flex-wrap: wrap;
                width: 100%;
            }
            
            /* style the header */
            nav{
                background-color: gray;
                color: white;
                font-family: arial;
                font-size: 24px;
            }
            
            nav > a{
                display: inline-block;
                text-decoration: none;
                padding: 10px;
                color: white;
                font-size: 18px;
            }
            nav > a:hover{
                background-color: black;
            }
            
            /* flex items */
            #profile{
                background-color: white;
                padding: 20px;
                flex: 15%
            }
            #info{
                background-color: white;
                padding: 20px;
                flex: 30%;
            }
            article{
                float: right;
                background-color: lightblue;
                padding: 60px;
                flex: 30%;
            }
            
            /* style the footer */
            footer{
                background-color: gray;
                padding: 10px;
                text-align: center;
                color: white;
            }
            
            /* left-align table headers */
            th{
                text-align: left;
            }
        </style>
        
        <% Patient p1 = (Patient)session.getAttribute("user");
           Appointment a = (Appointment)session.getAttribute("appt"); %>
        
    </head>
    <body>
        <header>
            <h1 style="width: 90%;">Dentist World</h1>
        </header>
        <nav>
            <a href="index.jsp">Home</a>
            <a href="#">Dentists</a>
            <a href="login.jsp">Login</a>
        </nav>
        <section class="flexbox">
            <div id="profile">
                <img src="placeholder.jpg" width="100%" alt="Profile Photo">
            </div>
            
            <div id="info">
                <%
                    String name = p1.getFname() + " " + p1.getLname();
                    out.print("<h1>" + name + "</h1>");
                %>
                <form method="post" action="PatientEditInfoServlet">
                    <table>
                        <tr>
                            <th><b>Personal Info</b></th>
                        </tr>
                        <tr>
                            <td width="30%">First Name:</td>
                            <td width="70%"><input type="text" name="fnbox" value="<%= p1.getFname()%>"/></td>
                        </tr>
                        <tr>
                            <td>Last Name:</td>
                            <td><input type="text" name="lnbox" value="<%= p1.getLname()%>"/></td>
                        </tr>
                        <tr>
                            <td>Address:</td>
                            <td><input type="text" name="addressbox" value="<%= p1.getAddress()%>"/></td>
                        </tr>
                        <tr>
                            <td>Email:</td>
                            <td><input type="text" name="emailbox" value="<%= p1.getEmail()%>"/></td>
                        </tr>
                        <tr>
                            <td>Insurance:</td>
                            <td><input type="text" name="insbox" value="<%= p1.getInsurance()%>"/></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><input type="submit" name="submit" value="Update Info"/></td>
                        </tr>
                    </table>
                </form>
            </div>
        
            <article>
                <form method="post" action="EditApptServlet">
                    <!-- Section for rescheduling appointment -->
                    <p>
                        <b>Appointment Time</b><br>
                        <%
                            Dentist d = new Dentist();
                            Procedure p = new Procedure();
                            DentistList dlist = new DentistList();
                            ProcList plist = new ProcList();

                            out.println("<input type=\"text\" name=\"date\" value=\"" + a.getTime() + "\" readonly/>");
                        %><br>
                        <input type="text" name="timebox" placeholder="Enter reschedule date..."/>
                    </p>
                    
                    <!-- Section for choosing new dentist -->
                    <p>
                        <b>Dentist</b><br>
                        <%
                            out.println(
                                "<select name=\"dentist\">" +
                                    "<option value=\"" + a.getDentId() + " \" selected>" + a.getDent() + "</option>"
                            );
                            for (int n = 0 ; n < dlist.getSize() ; n++){
                                d = dlist.getDentist(n);
                                out.println("<option value=\"" + d.getId() + " \">Dr. " + d.getLname() + "</option>");
                            }
                            out.println("</select>");
                        %>
                    </p>
                    
                    <!-- Section for choosing new procedure -->
                    <p>
                        <b>Procedure</b><br>
                        <%
                            out.println(
                                "<select name=\"procedure\">" +
                                    "<option value=" + a.getProcCode() + " selected>" + a.getProcedure() + "</option>"
                            );
                            for (int n = 0 ; n < dlist.getSize() ; n++){
                                p = plist.getProc(n);
                                out.println(
                                    "<option value=" + p.getCode() + ">" + p.getName() + " | " + p.getCost() + "</option>"
                                );
                            }
                            out.println("</select>");
                        %>
                    </p><br>
                    <input type="submit" name="submit" value="Update Appointment"/>
                </form>
            </article>

        </section>
        
        <footer>
            Placeholder for future functionality.
        </footer>
    </body>
</html>
