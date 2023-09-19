<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 04/04/2021
  Time: 1:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.disderm.utils.Varios" %>
<%@ page import="com.disderm.dao.SalesDAO" %>
<%@ page import="com.disderm.model.SalesModel" %>

<%
    SalesDAO salesDAO = new SalesDAO();
    Long sales_id = Long.parseLong(request.getParameter("id"));
    salesDAO.deleteSalesByID(sales_id);
    response.sendRedirect("/backoffice/modulos/sales/tbl_custom_search_sales.jsp");
%>