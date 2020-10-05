
<%--
  Created by IntelliJ IDEA.
  User: mtrap
  Date: 08.05.2020
  Time: 18:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Parkhaus</title>
    <script src='https://ccmjs.github.io/mkaul-components/parkhaus/versions/ccm.parkhaus-7.0.0.js'></script>

  </head>
  <body>

  <ccm-parkhaus-7-0-0
          server_url="http://localhost:8080/Semesterprojekt_SE1_war_exploded/DemoServlet" key='{"name":"ClientOnlyParkhaus","server_url":"","extra_buttons":[{"extra_class":"","extra_inner":"sum","extra_popup_title":"ka was hauptsache summe"}],"extra_charts":[]}'
          extra_buttons='["sum","avg","avgtimespent","ParkdauerAnParkplatz"]'
          extra_charts='["chart"]'

  ></ccm-parkhaus-7-0-0>

  </body>

</html>






<html>
  <head>

    <style>
      table, th, td {
        border: 1px solid black;
      }
    </style>
  </head>
  <body>

<table id="mytable">
   <tr>
     <th>TagesEinnahmen</th>
     <th>WochenEinnahmen</th>
   </tr>
  <tr>
    <td>0</td>
    <td>0</td>
  </tr>

 </table>

 </body>
</html>

