package com.disderm.service;
import com.disderm.model.UsuariosModel;
import com.disderm.dao.UsuariosDAO;
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

@Path("usuarios")
public class UsuariosService {
	private org.owasp.esapi.codecs.MySQLCodec MySQLCodecInstance;

  	@POST
  	@Path("/insert")
  	@Produces("application/json")  
	public boolean serviceInsertUsuarios (   
		@FormParam("id") Long id,
		@FormParam("nombre_usuario") String nombre_usuario,
		@FormParam("password") String password,
		@FormParam("nombre") String nombre,
		@FormParam("apellido") String apellido,
		@FormParam("nombre_mostrar") String nombre_mostrar,
		@FormParam("perfil") Long perfil,
		@FormParam("imagen_perfil") String imagen_perfil,
		@FormParam("habilitado") Long habilitado,
		@FormParam("telefono") String telefono,
		@FormParam("localidad") String localidad,
		@FormParam("provincia") String provincia,
		@FormParam("cp") String cp,
		@FormParam("app_platform") String app_platform,
		@FormParam("app_device_model") String app_device_model,
		@FormParam("app_device_token") String app_device_token,
		@FormParam("fecha_alta_sistema") Date fecha_alta_sistema,
		@FormParam("fecha_ultimo_acceso") Date fecha_ultimo_acceso,
		@FormParam("mensaje") String mensaje) {
			Timestamp ts_now = new Timestamp(new Date().getTime());
			UsuariosDAO usuariosDao = new UsuariosDAO();
			UsuariosModel _UsuariosModel = new UsuariosModel();
			_UsuariosModel.set_id(id);
			_UsuariosModel.set_nombre_usuario(nombre_usuario);
			_UsuariosModel.set_password(password);
			_UsuariosModel.set_nombre(nombre);
			_UsuariosModel.set_apellido(apellido);
			_UsuariosModel.set_nombre_mostrar(nombre_mostrar);
			_UsuariosModel.set_perfil(perfil);
			_UsuariosModel.set_imagen_perfil(imagen_perfil);
			_UsuariosModel.set_habilitado(habilitado);
			_UsuariosModel.set_telefono(telefono);
			_UsuariosModel.set_localidad(localidad);
			_UsuariosModel.set_provincia(provincia);
			_UsuariosModel.set_cp(cp);
			_UsuariosModel.set_app_platform(app_platform);
			_UsuariosModel.set_app_device_model(app_device_model);
			_UsuariosModel.set_app_device_token(app_device_token);
			_UsuariosModel.set_fecha_alta_sistema(fecha_alta_sistema);
			_UsuariosModel.set_fecha_ultimo_acceso(fecha_ultimo_acceso);
			_UsuariosModel.set_mensaje(mensaje);
			String reply = "";
			Long insert_id = 0L;
			boolean insert = true; 
			CustomServiceAnswerModel csam_usuarios = new CustomServiceAnswerModel();
            Gson gson_usuarios = new Gson(); 
			csam_usuarios.setResult(String.valueOf(usuariosDao.insertUsuariosusuarios(_UsuariosModel)));    
			reply = gson_usuarios.toJson(csam_usuarios);
			if (((csam_usuarios.getResult()).equals("")) || ((csam_usuarios.getResult())==null) || ((csam_usuarios.getResult()).equals("0")))
			{
				insert = false;
			}
				return insert;
		}@GET
@Path("/getAll/{user_id}")@Produces("application/json")
public String serviceGetAllUsuarios ( @PathParam("user_id") Long user_id) {UsuariosDAO usuariosDao = new UsuariosDAO();String reply = "";return reply;
}@GET
@Path("/getAll/username/{username}")@Produces("application/json")
public String serviceGetAllUsuariosByUsername ( @PathParam("username") String username) {UsuariosDAO usuariosDao = new UsuariosDAO();String reply = "";return reply;
}  	@POST
  	@Path("/update")
  	@Produces("application/json")  
	public boolean serviceUpdateUsuarios (   
		@FormParam("id") Long id,
		@FormParam("nombre_usuario") String nombre_usuario,
		@FormParam("password") String password,
		@FormParam("nombre") String nombre,
		@FormParam("apellido") String apellido,
		@FormParam("nombre_mostrar") String nombre_mostrar,
		@FormParam("perfil") Long perfil,
		@FormParam("imagen_perfil") String imagen_perfil,
		@FormParam("habilitado") Long habilitado,
		@FormParam("telefono") String telefono,
		@FormParam("localidad") String localidad,
		@FormParam("provincia") String provincia,
		@FormParam("cp") String cp,
		@FormParam("app_platform") String app_platform,
		@FormParam("app_device_model") String app_device_model,
		@FormParam("app_device_token") String app_device_token,
		@FormParam("fecha_alta_sistema") Date fecha_alta_sistema,
		@FormParam("fecha_ultimo_acceso") Date fecha_ultimo_acceso,
		@FormParam("mensaje") String mensaje) {
			Timestamp ts_now = new Timestamp(new Date().getTime());
			UsuariosDAO usuariosDao = new UsuariosDAO();
			UsuariosModel _UsuariosModel = new UsuariosModel();
			_UsuariosModel.set_id(id);
			_UsuariosModel.set_nombre_usuario(nombre_usuario);
			_UsuariosModel.set_password(password);
			_UsuariosModel.set_nombre(nombre);
			_UsuariosModel.set_apellido(apellido);
			_UsuariosModel.set_nombre_mostrar(nombre_mostrar);
			_UsuariosModel.set_perfil(perfil);
			_UsuariosModel.set_imagen_perfil(imagen_perfil);
			_UsuariosModel.set_habilitado(habilitado);
			_UsuariosModel.set_telefono(telefono);
			_UsuariosModel.set_localidad(localidad);
			_UsuariosModel.set_provincia(provincia);
			_UsuariosModel.set_cp(cp);
			_UsuariosModel.set_app_platform(app_platform);
			_UsuariosModel.set_app_device_model(app_device_model);
			_UsuariosModel.set_app_device_token(app_device_token);
			_UsuariosModel.set_fecha_alta_sistema(fecha_alta_sistema);
			_UsuariosModel.set_fecha_ultimo_acceso(fecha_ultimo_acceso);
			_UsuariosModel.set_mensaje(mensaje);
			String reply = "";
			Long insert_id = 0L;
			boolean update = true; 
			CustomServiceAnswerModel csam_usuarios = new CustomServiceAnswerModel();
                Gson gson_usuarios = new Gson(); 
			csam_usuarios.setResult(String.valueOf(usuariosDao.updateUsuariosusuarios(_UsuariosModel)));    
			reply = gson_usuarios.toJson(csam_usuarios);
			if (((csam_usuarios.getResult()).equals("")) || ((csam_usuarios.getResult())==null) || ((csam_usuarios.getResult()).equals("0")))
			{
				update = false;
			}
				return update;
		}
}