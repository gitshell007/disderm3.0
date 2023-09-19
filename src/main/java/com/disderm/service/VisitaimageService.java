package com.disderm.service;
import com.disderm.model.VisitaimageModel;
import com.disderm.dao.VisitaimageDAO;
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

@Path("visita_image")
public class VisitaimageService {
	private org.owasp.esapi.codecs.MySQLCodec MySQLCodecInstance;

  	@POST
  	@Path("/insert")
  	@Produces("application/json")  
	public boolean serviceInsertVisitaimage (   
		@FormParam("id") Long id,
		@FormParam("visita_id") Long visita_id,
		@FormParam("image") String image) {
			Timestamp ts_now = new Timestamp(new Date().getTime());
			VisitaimageDAO visita_imageDao = new VisitaimageDAO();
			VisitaimageModel _VisitaimageModel = new VisitaimageModel();
			_VisitaimageModel.set_id(id);
			_VisitaimageModel.set_visita_id(visita_id);
			_VisitaimageModel.set_image(image);
			String reply = "";
			Long insert_id = 0L;
			boolean insert = true; 
			CustomServiceAnswerModel csam_visita_image = new CustomServiceAnswerModel();
            Gson gson_visita_image = new Gson(); 
			csam_visita_image.setResult(String.valueOf(visita_imageDao.insertVisitaimagevisita_image(_VisitaimageModel)));    
			reply = gson_visita_image.toJson(csam_visita_image);
			if (((csam_visita_image.getResult()).equals("")) || ((csam_visita_image.getResult())==null) || ((csam_visita_image.getResult()).equals("0")))
			{
				insert = false;
			}
				return insert;
		}@GET
@Path("/getAll/{user_id}")@Produces("application/json")
public String serviceGetAllVisitaimage ( @PathParam("user_id") Long user_id) {VisitaimageDAO visita_imageDao = new VisitaimageDAO();String reply = "";return reply;
}@GET
@Path("/getAll/username/{username}")@Produces("application/json")
public String serviceGetAllVisitaimageByUsername ( @PathParam("username") String username) {VisitaimageDAO visita_imageDao = new VisitaimageDAO();String reply = "";return reply;
}  	@POST
  	@Path("/update")
  	@Produces("application/json")  
	public boolean serviceUpdateVisitaimage (   
		@FormParam("id") Long id,
		@FormParam("visita_id") Long visita_id,
		@FormParam("image") String image) {
			Timestamp ts_now = new Timestamp(new Date().getTime());
			VisitaimageDAO visita_imageDao = new VisitaimageDAO();
			VisitaimageModel _VisitaimageModel = new VisitaimageModel();
			_VisitaimageModel.set_id(id);
			_VisitaimageModel.set_visita_id(visita_id);
			_VisitaimageModel.set_image(image);
			String reply = "";
			Long insert_id = 0L;
			boolean update = true; 
			CustomServiceAnswerModel csam_visita_image = new CustomServiceAnswerModel();
                Gson gson_visita_image = new Gson(); 
			csam_visita_image.setResult(String.valueOf(visita_imageDao.updateVisitaimagevisita_image(_VisitaimageModel)));    
			reply = gson_visita_image.toJson(csam_visita_image);
			if (((csam_visita_image.getResult()).equals("")) || ((csam_visita_image.getResult())==null) || ((csam_visita_image.getResult()).equals("0")))
			{
				update = false;
			}
				return update;
		}
}