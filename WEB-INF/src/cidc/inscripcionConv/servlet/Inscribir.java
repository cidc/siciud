package cidc.inscripcionConv.servlet;

import java.io.IOException;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cidc.convocatorias.db.ConvocatoriasDB;
import cidc.convocatorias.obj.CompromisosOBJ;
import cidc.convocatorias.obj.ConvocatoriaOBJ;
import cidc.general.db.CursorDB;
import cidc.general.db.UsuarioDB;
import cidc.general.login.Usuario;
import cidc.general.servlet.ServletGeneral;
import cidc.inscripcionConv.db.InscripcionConvDB;
import cidc.inscripcionConv.obj.InscripcionConvOBJ;
import cidc.inscripcionConv.obj.ParametrosOBJ;
import cidc.inscripcionConv.obj.ResumenInscOBJ;
import cidc.inscripSistema.obj.Persona;

import cidc.convMovilidad.db.MovilidadDB;

public class Inscribir extends ServletGeneral {

	@SuppressWarnings("unchecked")
	public String[] operaciones(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		context = config.getServletContext();
		cursor = new CursorDB();
		String irA = "/InscripcionConv/Inscripcion.jsp";
		String mensaje = "";
		String terminar = "no";
		int accion = 0;
		if (req.getParameter("accion") != null)
			accion = Integer.parseInt(req.getParameter("accion"));

		System.out.println(".............");
		System.out.println("accion------>" + accion);
		HttpSession sesion = req.getSession();
		Usuario usuario = (Usuario) sesion.getAttribute("loginUsuario");
		UsuarioDB usuarioDB = new UsuarioDB(cursor, usuario.getPerfil());
		Persona persona = (Persona) sesion.getAttribute("persona");
		if (usuario.getPerfil() != 13)
			persona = usuarioDB.getPersona(usuario.getIdUsuario());
		ConvocatoriaOBJ convocatoriaOBJ = (ConvocatoriaOBJ) sesion
				.getAttribute("datosConv");
		InscripcionConvDB inscripcionConvDB = new InscripcionConvDB(cursor,
				usuario.getPerfil());
		MovilidadDB movilidadDB = new MovilidadDB(cursor, usuario.getPerfil());
		InscripcionConvOBJ inscripcionConvOBJ = null;
		retorno[0] = "unir";
		switch (accion) {
		case ParametrosOBJ.cmdIngresar:
			if (convocatoriaOBJ != null) {
				System.out.println("------>" + convocatoriaOBJ.getConvAno()+ "  -- " + convocatoriaOBJ.getConvNumero());
				if (convocatoriaOBJ.getConvTipo() == 2) {
					irA = "/movilidad/adminMovilidad.x?accion=2&convano="
							+ convocatoriaOBJ.getConvAno() + "&convnumero="
							+ convocatoriaOBJ.getConvNumero();
					System.out.println("irA:" + irA);
					sesion.setAttribute("persona", persona);
				} if (convocatoriaOBJ.getConvTipo() == 1) {
					req.setAttribute("listaPropuestas", inscripcionConvDB.getListaPropuestas(usuario.getIdUsuario(),
							usuario.getPerfil(),convocatoriaOBJ.getConvId()));
					terminar = req.getParameter("terminar");
					irA = "/InscripcionConv/listaPropuesta.jsp";
					if(persona.getPapel()==3||persona.getPapel()==5)
						req.setAttribute("rol", 1);
					if(persona.getPapel()==1||persona.getPapel()==2)
						req.setAttribute("rol", 2);
					if (terminar != null) {
						if (terminar.equals("si")) {
							//String idProp = req.getParameter("idProp");
							inscripcionConvOBJ = (InscripcionConvOBJ) sesion.getAttribute("inscripcionConvOBJ");
							if (inscripcionConvDB.enviarMail(String.valueOf(inscripcionConvOBJ.getPropId()), persona))
							mensaje = "El registro ha terminado correctamente, por favor verifique en su correo electr�nico los datos de inscripci�n";
							req.setAttribute("terminar", terminar);
						}
					}
				}
			}
			break;
		case ParametrosOBJ.cmdPaso0:
			System.out.println("entroooo");
			sesion.setAttribute("persona", persona);
			sesion.setAttribute("ajaxProyCur",
					inscripcionConvDB.AjaxProyectoCur());
			sesion.setAttribute("listaTotalGrupos",
					inscripcionConvDB.totalGruposInvestigacion());
			if (sesion.getAttribute("ajaxGrupo") == null)
				sesion.setAttribute("ajaxGrupo",
						inscripcionConvDB.AjaxGruposInvestigacion(1, 1));
			req.setAttribute("listaRubrosOBJ",
					inscripcionConvDB.getRubros(convocatoriaOBJ.getConvId(), 1));
			req.setAttribute("listaRubrosOBJ1",
					inscripcionConvDB.getRubros(convocatoriaOBJ.getConvId(), 0));
			irA = "/InscripcionConv/Inscripcion.jsp";
			break;

		case ParametrosOBJ.cmdPaso1:
			ConvocatoriasDB convocatoriasDB = new ConvocatoriasDB(cursor,
					usuario.getPerfil());
			long id = 0;
			try {
				id = inscripcionConvDB.getMaxId(null) + 1;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			inscripcionConvOBJ = (InscripcionConvOBJ) sesion
					.getAttribute("inscripcionConvOBJ");
			if (inscripcionConvDB.insertaInscripcion(inscripcionConvOBJ)) {
				// ******************conseguir el resumen de lo ya inscrito pero
				// falta hacer la cuenta de los rubros

				req.setAttribute("resumen", inscripcionConvDB.getResumen(""
						+ id, inscripcionConvOBJ));
				// ******************consultar el listado de rubros almacenados
				// en la convocatoria a la cual se vinculan.
				sesion.setAttribute("listaComp", convocatoriasDB
						.consultaCompromisos(ParametrosOBJ.ListaCompromiso,
								convocatoriaOBJ.getConvId()));
				irA = "/InscripcionConv/Compromisos.jsp";
			} else {
				mensaje = "La Inscripci�n no pudo ser almacenada...\n Por favor vuelva a intentarlo";
				irA = "/InscripcionConv/Error.jsp";
			}
			retorno[0] = "desviar";
			break;
		case ParametrosOBJ.cmdPaso2:
			int conv = 0;
			if (req.getParameter("propConvId") != null)
				conv = Integer.parseInt(req.getParameter("propConvId"));
			System.out.println("entro" + conv);
			sesion.setAttribute("listaDocOBJ", movilidadDB.getDocumentos(conv));
			System.out.println("Ingreso al paso 02	");
			inscripcionConvOBJ = (InscripcionConvOBJ) sesion
					.getAttribute("inscripcionConvOBJ");
			if (inscripcionConvDB.insertaCompromisos(inscripcionConvOBJ,
					(List<CompromisosOBJ>) sesion.getAttribute("listaComp"))) {
				mensaje = "Compromisos insertados correctamente";
				irA = "/InscripcionConv/Cargar.jsp";
				sesion.setAttribute("listaDocOBJ", movilidadDB.buscarDocumentosInscritos((int)inscripcionConvOBJ.getPropId(),((ConvocatoriaOBJ) sesion
						.getAttribute("datosConv")).getConvId()));
			} else {
				mensaje = "El registro de los compromisos no pudo ser insertado correctamente \n"
						+ inscripcionConvDB.getMensaje();
				irA = "/InscripcionConv/Error.jsp";
			}
			accion = 0;
			retorno[0] = "desviar";
			break;
		case ParametrosOBJ.cmdPaso3:

			int idProp = Integer.parseInt(req.getParameter("idProp"));
			ConvocatoriaOBJ convSeleccionada = (ConvocatoriaOBJ) sesion
					.getAttribute("datosConv");
			sesion.removeAttribute("listaDocOBJ");
			sesion.removeAttribute("inscripcionConvOBJ");
			InscripcionConvOBJ inscConvOBJ = new InscripcionConvOBJ();
			inscConvOBJ.setPropId(idProp);
			sesion.setAttribute("inscripcionConvOBJ", inscConvOBJ);
			sesion.setAttribute("listaDocOBJ", movilidadDB.buscarDocumentosInscritos(idProp,convSeleccionada.getConvId()));
			irA = "/InscripcionConv/Cargar.jsp";
			break;
		}
		retorno[1] = irA;
		retorno[2] = mensaje;
		return retorno;
	}
}
