package cidc.general.obj;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;

import cidc.general.db.BaseDB;
import cidc.general.db.CursorDB;


public class CargarDocumento{
	private CursorDB cursorDB=new CursorDB();
	private BaseDB baseDB=new BaseDB(cursorDB,1);
	private String nombreArchivo;
	private static char sep=java.io.File.separatorChar;

	public boolean cargar(HttpServletRequest req, String nombre, String carpeta){
		boolean retorno=true;
		try {
			String path=req.getRealPath(req.getContextPath()).substring(0,req.getRealPath(req.getContextPath()).lastIndexOf(sep));
			path=path+sep+"Documentos"+sep+carpeta+sep;
			System.out.println(path);
			DiskFileUpload cargar = new DiskFileUpload();
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(10240*1024);
		//	ServletFileUpload cargar = new ServletFileUpload(factory);
			File sitio=new File(path);
	        factory.setRepository(sitio);
	        cargar.setSizeMax(10240*1024);
	    //    upload.setFileSizeMax(1024*1024);

	        List listaItems = cargar.parseRequest(req);

	        if(listaItems == null || listaItems.size()==0){
	        	System.out.println("no Se encontraron archivos para cargar");
	        	retorno=false;
	        }
	        else{
		        Iterator i = listaItems.iterator();
		        FileItem item = null;

		        while (i.hasNext())
		        {
		        	item = (FileItem) i.next();
		        	if (!item.isFormField()) {
			            nombreArchivo = item.getName();
			            File fichero = new File(path+nombreArchivo);
			            nombreArchivo=nombre+nombreArchivo.substring(nombreArchivo.lastIndexOf("."),nombreArchivo.length());
			            System.out.println(nombreArchivo.substring(nombreArchivo.lastIndexOf(".")));
			            fichero = new  File(sitio +(sep+ nombreArchivo));
			            // escribimos el fichero en el disco duro del servidor
			            item.write(fichero);
			            System.out.println("Carga completa del Archivo --------->"+nombreArchivo);
			            break;
		        	}
		        }
	        }
	    }
	    catch(Exception e){
	    	System.out.println("Falla la carga del Archivo");
	    	baseDB.lanzaExcepcion(e);
	        retorno=false;
	    }
	    return retorno;
    }

	public String cargar(String path, FileItem archivo, long idArchivo,String carpeta,String nombre) throws Exception{
		File sitio=new File(path);
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(10240*1024);
        factory.setRepository(sitio);

        String ruta=path+sep+"Documentos"+sep+carpeta+sep+nombre+idArchivo+archivo.getName().substring(archivo.getName().lastIndexOf("."),archivo.getName().length());
        String nombreArchivo="Articulo_"+idArchivo+archivo.getName().substring(archivo.getName().lastIndexOf("."),archivo.getName().length());
      //  System.out.println(ruta);
        archivo.write(new File(ruta));
		return nombreArchivo;
	}

	public String cargarGenerico(String path, FileItem archivo, String carpeta,String prefijo,long idArchivo) throws Exception{
		File sitio=new File(path);
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(10240*1024);
        factory.setRepository(sitio);
        String nombreArchivo=prefijo+idArchivo+archivo.getName().substring(archivo.getName().lastIndexOf("."),archivo.getName().length());
        String ruta=path+sep+"Documentos"+sep+carpeta+sep+nombreArchivo;
        archivo.write(new File(ruta));
		return nombreArchivo;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
}
