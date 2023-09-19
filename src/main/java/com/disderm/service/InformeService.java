package com.disderm.service;
import com.disderm.model.InformeModel;
import com.disderm.dao.InformeDAO;
import com.disderm.model.CustomServiceAnswerModel;
import com.google.gson.Gson;
import java.util.*;
import javax.ws.rs.*;
import java.util.Date;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.HashMap;
import java.sql.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.codecs.MySQLCodec;
import com.disderm.utils.FFecha;
import com.disderm.utils.FFecha.*;

@Path("informe")
public class InformeService {
	private org.owasp.esapi.codecs.MySQLCodec MySQLCodecInstance;

  	@POST
  	@Path("/insert")
  	@Produces("application/json")  
	public boolean serviceInsertInforme (   
		@FormParam("id") Long id,
		@FormParam("situacion_lesion") String situacion_lesion,
		@FormParam("informe") String informe,
		@FormParam("tipo_incidencia") String tipo_incidencia) {
			Timestamp ts_now = new Timestamp(new Date().getTime());
			InformeDAO informeDao = new InformeDAO();
			InformeModel _InformeModel = new InformeModel();
			_InformeModel.set_id(id);
			_InformeModel.set_situacion_lesion(situacion_lesion);
			_InformeModel.set_informe(informe);
			_InformeModel.set_tipo_incidencia(tipo_incidencia);
			String reply = "";
			Long insert_id = 0L;
			boolean insert = true; 
			CustomServiceAnswerModel csam_informe = new CustomServiceAnswerModel();
            Gson gson_informe = new Gson(); 
			csam_informe.setResult(String.valueOf(informeDao.insertInformeinforme(_InformeModel)));    
			reply = gson_informe.toJson(csam_informe);
			if (((csam_informe.getResult()).equals("")) || ((csam_informe.getResult())==null) || ((csam_informe.getResult()).equals("0")))
			{
				insert = false;
			}
				return insert;
		}@GET
@Path("/getAll/{user_id}")@Produces("application/json")
public String serviceGetAllInforme ( @PathParam("user_id") Long user_id) {InformeDAO informeDao = new InformeDAO();String reply = "";return reply;
}@GET
@Path("/getAll/username/{username}")@Produces("application/json")
public String serviceGetAllInformeByUsername ( @PathParam("username") String username) {InformeDAO informeDao = new InformeDAO();String reply = "";return reply;
}  	@POST
  	@Path("/update")
  	@Produces("application/json")  
	public boolean serviceUpdateInforme (   
		@FormParam("id") Long id,
		@FormParam("situacion_lesion") String situacion_lesion,
		@FormParam("informe") String informe,
		@FormParam("tipo_incidencia") String tipo_incidencia) {
			Timestamp ts_now = new Timestamp(new Date().getTime());
			InformeDAO informeDao = new InformeDAO();
			InformeModel _InformeModel = new InformeModel();
			_InformeModel.set_id(id);
			_InformeModel.set_situacion_lesion(situacion_lesion);
			_InformeModel.set_informe(informe);
			_InformeModel.set_tipo_incidencia(tipo_incidencia);
			String reply = "";
			Long insert_id = 0L;
			boolean update = true; 
			CustomServiceAnswerModel csam_informe = new CustomServiceAnswerModel();
                Gson gson_informe = new Gson(); 
			csam_informe.setResult(String.valueOf(informeDao.updateInformeinforme(_InformeModel)));    
			reply = gson_informe.toJson(csam_informe);
			if (((csam_informe.getResult()).equals("")) || ((csam_informe.getResult())==null) || ((csam_informe.getResult()).equals("0")))
			{
				update = false;
			}
				return update;
		}
}