package com.disderm.service;
import com.disderm.model.ImagenesModel;
import com.disderm.dao.ImagenesDAO;
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

@Path("imagenes")
public class ImagenesService {
	private org.owasp.esapi.codecs.MySQLCodec MySQLCodecInstance;

  	@POST
  	@Path("/insert")
  	@Produces("application/json")  
	public boolean serviceInsertImagenes (   
		@FormParam("id") Long id,
		@FormParam("diagnostico_id") Long diagnostico_id,
		@FormParam("imagen_nombre") String imagen_nombre,
		@FormParam("imagen_datetime") Date imagen_datetime,
		@FormParam("imagen") String imagen,
		@FormParam("localizacion") String localizacion) {
			Timestamp ts_now = new Timestamp(new Date().getTime());
			ImagenesDAO imagenesDao = new ImagenesDAO();
			ImagenesModel _ImagenesModel = new ImagenesModel();
			_ImagenesModel.set_id(id);
			_ImagenesModel.set_diagnostico_id(diagnostico_id);
			_ImagenesModel.set_imagen_nombre(imagen_nombre);
			_ImagenesModel.set_imagen_datetime(imagen_datetime);
			_ImagenesModel.set_imagen(imagen);
			_ImagenesModel.set_localizacion(localizacion);
			String reply = "";
			Long insert_id = 0L;
			boolean insert = true; 
			CustomServiceAnswerModel csam_imagenes = new CustomServiceAnswerModel();
            Gson gson_imagenes = new Gson(); 
			csam_imagenes.setResult(String.valueOf(imagenesDao.insertImagenesimagenes(_ImagenesModel)));    
			reply = gson_imagenes.toJson(csam_imagenes);
			if (((csam_imagenes.getResult()).equals("")) || ((csam_imagenes.getResult())==null) || ((csam_imagenes.getResult()).equals("0")))
			{
				insert = false;
			}
				return insert;
		}@GET
@Path("/getAll/{user_id}")@Produces("application/json")
public String serviceGetAllImagenes ( @PathParam("user_id") Long user_id) {ImagenesDAO imagenesDao = new ImagenesDAO();String reply = "";return reply;
}@GET
@Path("/getAll/username/{username}")@Produces("application/json")
public String serviceGetAllImagenesByUsername ( @PathParam("username") String username) {ImagenesDAO imagenesDao = new ImagenesDAO();String reply = "";return reply;
}  	@POST
  	@Path("/update")
  	@Produces("application/json")  
	public boolean serviceUpdateImagenes (   
		@FormParam("id") Long id,
		@FormParam("diagnostico_id") Long diagnostico_id,
		@FormParam("imagen_nombre") String imagen_nombre,
		@FormParam("imagen_datetime") Date imagen_datetime,
		@FormParam("imagen") String imagen,
		@FormParam("localizacion") String localizacion) {
			Timestamp ts_now = new Timestamp(new Date().getTime());
			ImagenesDAO imagenesDao = new ImagenesDAO();
			ImagenesModel _ImagenesModel = new ImagenesModel();
			_ImagenesModel.set_id(id);
			_ImagenesModel.set_diagnostico_id(diagnostico_id);
			_ImagenesModel.set_imagen_nombre(imagen_nombre);
			_ImagenesModel.set_imagen_datetime(imagen_datetime);
			_ImagenesModel.set_imagen(imagen);
			_ImagenesModel.set_localizacion(localizacion);
			String reply = "";
			Long insert_id = 0L;
			boolean update = true; 
			CustomServiceAnswerModel csam_imagenes = new CustomServiceAnswerModel();
                Gson gson_imagenes = new Gson(); 
			csam_imagenes.setResult(String.valueOf(imagenesDao.updateImagenesimagenes(_ImagenesModel)));    
			reply = gson_imagenes.toJson(csam_imagenes);
			if (((csam_imagenes.getResult()).equals("")) || ((csam_imagenes.getResult())==null) || ((csam_imagenes.getResult()).equals("0")))
			{
				update = false;
			}
				return update;
		}
}