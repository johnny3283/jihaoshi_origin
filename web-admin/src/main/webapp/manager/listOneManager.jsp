<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.employee.model.*" %>
<%@ page import="java.util.*" %>

<%
    EmployeeService empSvc = new EmployeeService();
    EmployeeVO EmployeeVO = (EmployeeVO) request.getAttribute("empVO");
%>

<html>
<head>
    <title>listOneEmployee</title>
    <style>
        #content {
            display: flex;
            justify-content: center;
            align-items: center;
        }

        #Commenttable {
            display: flex;
            justify-content: center;
            flex-direction: column;
        }

        table {
            border: 1px solid #ccc;
            border-collapse: collapse;
            margin-left: 20%;
            padding: 0;
            width: 50%;
            table-layout: fixed;
        }

        table caption {
            font-size: 1.5em;
            margin: .5em 0 .75em;
        }

        table tr {
            background-color: #f8f8f8;
            border: 1px solid #ddd;
            padding: .35em;
        }

        table th,
        table td {
            padding: .625em;
            text-align: center;
        }

        table th {
            font-size: .85em;
            letter-spacing: .1em;
            text-transform: uppercase;
        }

        @media screen and (max-width: 600px) {
            table {
                border: 0;
            }

            table caption {
                font-size: 1.3em;
            }

            table thead {
                border: none;
                clip: rect(0 0 0 0);
                height: 1px;
                margin: -1px;
                overflow: hidden;
                padding: 0;
                position: absolute;
                width: 1px;
            }

            table tr {
                border-bottom: 3px solid #ddd;
                display: block;
                margin-bottom: .625em;
            }

            table td {
                border-bottom: 1px solid #ddd;
                display: block;
                font-size: .8em;
                text-align: right;
            }

            table td::before {
                /*
                * aria-label has no advantage, it won't be read inside a table
                content: attr(aria-label);
                */
                content: attr(data-label);
                float: left;
                font-weight: bold;
                text-transform: uppercase;
            }

            table td:last-child {
                border-bottom: 0;
            }
            }
    </style>
</head>
<body>
<%@ include file="../navbar.file" %>
<br>
<div id="CONTENT">


    <c:if test="${not empty errorMsgs}">
        <font style="color: red">請修正以下錯誤:</font>
        <ul>
            <c:forEach var="message" items="${errorMsgs}">
                <li style="color: red">${message}</li>
            </c:forEach>
        </ul>
    </c:if>
    <div id="form">
        <form METHOD="post" ACTION="../manager/ManagerServlet" style="margin-bottom: 0px;">
            <table>
                <tr>
                    <td>管理員編號</td>
                    <td>${EmployeeVO.managerNo}</td>
                </tr>
                <tr>
                    <td>管理員姓名</td>
                    <td>${EmployeeVO.managerName}</td>
                </tr>
                <tr>
                    <td>管理員帳號</td>
                    <td>${EmployeeVO.managerAccount}</td>
                </tr>
                <tr>
                    <td>管理員狀態</td>
                    <td>${EmployeeVO.managerStatus}</td>
                </tr>
                <tr>
                    <td>管理員權限</td>
                    <td><c:forEach var="no" items="${EmployeeVO.authorityNo}">${authorityName[no]}&emsp;</c:forEach></td>
                </tr>
                <tr>
                    <td><input type="submit" value="修改" style="border-radius:1rem; border: 1px solid #ccc;">
                    <input type="hidden" name="managerNo" value="${EmployeeVO.managerNo}">
                    <input type="hidden" name="action" value="getOne_For_Update"></td>
                </tr>
            </table>
        </form>
    </div>
</div>
</body>
</html>