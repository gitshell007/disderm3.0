<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 04/04/2021
  Time: 1:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.disderm.utils.Varios" %>
<%@ page import="com.disderm.dao.PharmacistDAO" %>
<%@ page import="com.disderm.model.PharmacistModel" %>

<%
    PharmacistDAO pharmacistDAO = new PharmacistDAO();
    Long pharmacist_id = Long.parseLong(request.getParameter("id"));
    pharmacistDAO.deletePharmacistByID(pharmacist_id);
    response.sendRedirect("/backoffice/modulos/pharmacist/tbl_custom_search_pharmacist.jsp");
%>