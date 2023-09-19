package com.disderm.service;
import com.disderm.model.VisitaModel;
import com.disderm.dao.VisitaDAO;
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

@Path("visita")
public class VisitaService {
	private org.owasp.esapi.codecs.MySQLCodec MySQLCodecInstance;

  	@POST
  	@Path("/insert")
  	@Produces("application/json")  
	public boolean serviceInsertVisita (   
		@FormParam("id") Long id,
		@FormParam("fecha_visita") Date fecha_visita,
		@FormParam("urgencia") String urgencia,
		@FormParam("sintomatologia") String sintomatologia) {
			Timestamp ts_now = new Timestamp(new Date().getTime());
			VisitaDAO visitaDao = new VisitaDAO();
			VisitaModel _VisitaModel = new VisitaModel();
			_VisitaModel.set_id(id);
			_VisitaModel.set_fecha_visita(fecha_visita);
			_VisitaModel.set_urgencia(urgencia);
			_VisitaModel.set_sintomatologia(sintomatologia);
			String reply = "";
			Long insert_id = 0L;
			boolean insert = true; 
			CustomServiceAnswerModel csam_visita = new CustomServiceAnswerModel();
            Gson gson_visita = new Gson(); 
			csam_visita.setResult(String.valueOf(visitaDao.insertVisitavisita(_VisitaModel)));    
			reply = gson_visita.toJson(csam_visita);
			if (((csam_visita.getResult()).equals("")) || ((csam_visita.getResult())==null) || ((csam_visita.getResult()).equals("0")))
			{
				insert = false;
			}
				return insert;
		}@GET
@Path("/getAll/{user_id}")@Produces("application/json")
public String serviceGetAllVisita ( @PathParam("user_id") Long user_id) {VisitaDAO visitaDao = new VisitaDAO();String reply = "";return reply;
}@GET
@Path("/getAll/username/{username}")@Produces("application/json")
public String serviceGetAllVisitaByUsername ( @PathParam("username") String username) {VisitaDAO visitaDao = new VisitaDAO();String reply = "";return reply;
}  	@POST
  	@Path("/update")
  	@Produces("application/json")  
	public boolean serviceUpdateVisita (   
		@FormParam("id") Long id,
		@FormParam("fecha_visita") Date fecha_visita,
		@FormParam("urgencia") String urgencia,
		@FormParam("sintomatologia") String sintomatologia) {
			Timestamp ts_now = new Timestamp(new Date().getTime());
			VisitaDAO visitaDao = new VisitaDAO();
			VisitaModel _VisitaModel = new VisitaModel();
			_VisitaModel.set_id(id);
			_VisitaModel.set_fecha_visita(fecha_visita);
			_VisitaModel.set_urgencia(urgencia);
			_VisitaModel.set_sintomatologia(sintomatologia);
			String reply = "";
			Long insert_id = 0L;
			boolean update = true; 
			CustomServiceAnswerModel csam_visita = new CustomServiceAnswerModel();
                Gson gson_visita = new Gson(); 
			csam_visita.setResult(String.valueOf(visitaDao.updateVisitavisita(_VisitaModel)));    
			reply = gson_visita.toJson(csam_visita);
			if (((csam_visita.getResult()).equals("")) || ((csam_visita.getResult())==null) || ((csam_visita.getResult()).equals("0")))
			{
				update = false;
			}
				return update;
		}
}