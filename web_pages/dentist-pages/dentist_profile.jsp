<%-- 
    Document   : dentist_profile
    Created on : Jul 22, 2018, 3:53:21 PM
    Author     : THE PATRICKS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="BusinessObjects.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dentist Profile</title>
        
        <% Dentist d1 = (Dentist)session.getAttribute("user"); %>
        
        <style>
            /* flexbox class to hold flex items / main content */
            .flexbox{
                display: flex;
                flex-wrap: wrap;
                width: 100%;
            }
            
            /* navigation bar with square buttons */
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
                padding: 20px;
                flex: 45%;
            }
            
            /* style the footer */
            footer{
                background-color: gray;
                padding: 10px;
                text-align: center;
                color: white;
            }
        </style>
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
                    String name = "Dr. " + d1.getFname() + " " + d1.getLname();
                    out.print("<h1>" + name + "</h1>");
                %>
                <form method="post" action="DentistEditInfoServlet">
                    <table>
                        <tr>
                            <th><b>Personal Info</b></th>
                        </tr>
                        <tr>
                            <td width="30%">First Name:</td>
                            <td width="70%"><input type="text" name="fnbox" value="<%= d1.getFname()%>"/></td>
                        </tr>
                        <tr>
                            <td>Last Name:</td>
                            <td><input type="text" name="lnbox" value="<%= d1.getLname()%>"/></td>
                        </tr>
                        <tr>
                            <td>Email:</td>
                            <td><input type="text" name="emailbox" value="<%= d1.getEmail()%>"/></td>
                        </tr>
                        <tr>
                            <td>Office:</td>
                            <td><input type="text" name="officebox" value="<%= d1.getOffice()%>"/></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><input type="submit" name="submit" value="Update Info"/></td>
                        </tr>
                    </table>
                </form>
            </div>
        
            <article>
                <table style="width: 100%">
                    <tr>
                        <th width="30%">
                            Appointment Time
                        </th>
                        <th width="25%">
                            Patient Name
                        </th>
                        <th width="45%">
                            Procedure/Cost
                        </th>
                    </tr>
                    <%
                        AppointmentList alist = d1.getList();
                        Appointment a;
                        for (int i = 0 ; i < alist.getSize() ; i++){
                            a = alist.getAppt(i);
                            out.println(
                            "<tr>" +
                            "<td>" + a.getTime() + "</td>" +
                            "<td>" + a.getPatId() +"</td>" +
                            "<td>" + a.getProcedure() +"</td>" +
                            "</tr>"
                            );
                        }
                    %>
                </table>
            </article>
        </section>
        <footer>
            Placeholder for future content.
        </footer>
    </body>
</html>
