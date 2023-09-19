package com.disderm.service;
import com.disderm.model.MensajesincidenciaModel;
import com.disderm.dao.MensajesincidenciaDAO;
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

@Path("mensajes_incidencia")
public class MensajesincidenciaService {
	private org.owasp.esapi.codecs.MySQLCodec MySQLCodecInstance;

  	@POST
  	@Path("/insert")
  	@Produces("application/json")  
	public boolean serviceInsertMensajesincidencia (   
		@FormParam("id") Long id,
		@FormParam("fecha") Date fecha,
		@FormParam("tipo_incidencia") String tipo_incidencia,
		@FormParam("mensaje") String mensaje,
		@FormParam("diagnostico_incidencia") Long diagnostico_incidencia,
		@FormParam("facultativo") Long facultativo,
		@FormParam("administrador") Long administrador) {
			Timestamp ts_now = new Timestamp(new Date().getTime());
			MensajesincidenciaDAO mensajes_incidenciaDao = new MensajesincidenciaDAO();
			MensajesincidenciaModel _MensajesincidenciaModel = new MensajesincidenciaModel();
			_MensajesincidenciaModel.set_id(id);
			_MensajesincidenciaModel.set_fecha(fecha);
			_MensajesincidenciaModel.set_tipo_incidencia(tipo_incidencia);
			_MensajesincidenciaModel.set_mensaje(mensaje);
			_MensajesincidenciaModel.set_diagnostico_incidencia(diagnostico_incidencia);
			_MensajesincidenciaModel.set_facultativo(facultativo);
			_MensajesincidenciaModel.set_administrador(administrador);
			String reply = "";
			Long insert_id = 0L;
			boolean insert = true; 
			CustomServiceAnswerModel csam_mensajes_incidencia = new CustomServiceAnswerModel();
            Gson gson_mensajes_incidencia = new Gson(); 
			csam_mensajes_incidencia.setResult(String.valueOf(mensajes_incidenciaDao.insertMensajesincidenciamensajes_incidencia(_MensajesincidenciaModel)));    
			reply = gson_mensajes_incidencia.toJson(csam_mensajes_incidencia);
			if (((csam_mensajes_incidencia.getResult()).equals("")) || ((csam_mensajes_incidencia.getResult())==null) || ((csam_mensajes_incidencia.getResult()).equals("0")))
			{
				insert = false;
			}
				return insert;
		}@GET
@Path("/getAll/{user_id}")@Produces("application/json")
public String serviceGetAllMensajesincidencia ( @PathParam("user_id") Long user_id) {MensajesincidenciaDAO mensajes_incidenciaDao = new MensajesincidenciaDAO();String reply = "";return reply;
}@GET
@Path("/getAll/username/{username}")@Produces("application/json")
public String serviceGetAllMensajesincidenciaByUsername ( @PathParam("username") String username) {MensajesincidenciaDAO mensajes_incidenciaDao = new MensajesincidenciaDAO();String reply = "";return reply;
}  	@POST
  	@Path("/update")
  	@Produces("application/json")  
	public boolean serviceUpdateMensajesincidencia (   
		@FormParam("id") Long id,
		@FormParam("fecha") Date fecha,
		@FormParam("tipo_incidencia") String tipo_incidencia,
		@FormParam("mensaje") String mensaje,
		@FormParam("diagnostico_incidencia") Long diagnostico_incidencia,
		@FormParam("facultativo") Long facultativo,
		@FormParam("administrador") Long administrador) {
			Timestamp ts_now = new Timestamp(new Date().getTime());
			MensajesincidenciaDAO mensajes_incidenciaDao = new MensajesincidenciaDAO();
			MensajesincidenciaModel _MensajesincidenciaModel = new MensajesincidenciaModel();
			_MensajesincidenciaModel.set_id(id);
			_MensajesincidenciaModel.set_fecha(fecha);
			_MensajesincidenciaModel.set_tipo_incidencia(tipo_incidencia);
			_MensajesincidenciaModel.set_mensaje(mensaje);
			_MensajesincidenciaModel.set_diagnostico_incidencia(diagnostico_incidencia);
			_MensajesincidenciaModel.set_facultativo(facultativo);
			_MensajesincidenciaModel.set_administrador(administrador);
			String reply = "";
			Long insert_id = 0L;
			boolean update = true; 
			CustomServiceAnswerModel csam_mensajes_incidencia = new CustomServiceAnswerModel();
                Gson gson_mensajes_incidencia = new Gson(); 
			csam_mensajes_incidencia.setResult(String.valueOf(mensajes_incidenciaDao.updateMensajesincidenciamensajes_incidencia(_MensajesincidenciaModel)));    
			reply = gson_mensajes_incidencia.toJson(csam_mensajes_incidencia);
			if (((csam_mensajes_incidencia.getResult()).equals("")) || ((csam_mensajes_incidencia.getResult())==null) || ((csam_mensajes_incidencia.getResult()).equals("0")))
			{
				update = false;
			}
				return update;
		}
}