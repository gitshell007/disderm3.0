<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 04/04/2021
  Time: 1:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.disderm.utils.Varios" %>
<%@ page import="com.disderm.dao.ProductDAO" %>
<%@ page import="com.disderm.model.ProductModel" %>

<%
    ProductDAO productDAO = new ProductDAO();
    Long sales_id = Long.parseLong(request.getParameter("id"));
    productDAO.deleteProductByID(sales_id);
    response.sendRedirect("/backoffice/modulos/product/tbl_custom_search_product.jsp");
%>