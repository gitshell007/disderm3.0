<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 04/04/2021
  Time: 1:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.disderm.utils.Varios" %>
<%@ page import="com.disderm.dao.LaboratoryDAO" %>
<%@ page import="com.disderm.model.LaboratoryModel" %>

<%
    LaboratoryDAO laboratoryDAO = new LaboratoryDAO();
    Long laboratory_id = Long.parseLong(request.getParameter("id"));
    laboratoryDAO.deleteLaboratoryByID(laboratory_id);
    response.sendRedirect("/backoffice/modulos/laboratory/tbl_custom_search_laboratory.jsp");
%>