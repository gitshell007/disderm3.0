<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 04/04/2021
  Time: 1:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.disderm.utils.Varios" %>
<%@ page import="com.disderm.dao.PharmacyDAO" %>
<%@ page import="com.disderm.model.PharmacyModel" %>

<%
    PharmacyDAO pharmacyDAO = new PharmacyDAO();
    Long pharmacy_id = Long.parseLong(request.getParameter("id"));
    pharmacyDAO.deletePharmacyByID(pharmacy_id);
    response.sendRedirect("/backoffice/modulos/pharmacy/tbl_custom_search_pharmacy.jsp");
%>