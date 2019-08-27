<%-- 
    Document   : index
    Created on : Jul 22, 2018, 3:39:09 PM
    Author     : THE PATRICKS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
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
            
            /* floating columns */
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
    </body>
</html>
