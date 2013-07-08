package Servidor;

import Objeto.Objeto;
import Objeto.modeloCancion;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Home
 */
public class main_Servidor {

    public HashMap<String, modeloCancion> mapaCanciones = new HashMap<>();
    public ArrayList listaCanciones = new ArrayList();
    public int x;

    public main_Servidor() {
    }
    // Envio del Catalogo 

    public void enviarCatalogo() {
        Socket cl;
        try (ServerSocket ss = new ServerSocket(8023)) {
            System.out.println("Servicio Iniciado ...");
            System.out.println("Esperando un Cliente ...");
            cl = ss.accept();
            System.out.println("Cliente Conectado desde " + cl.getInetAddress());
            try (ObjectOutputStream oos = new ObjectOutputStream(cl.getOutputStream())) {
                System.out.println("Flujo de Datos Asociado Correctamente desde " + cl.getInetAddress());
                llenarMapa();
                Objeto ob_out = new Objeto(mapaCanciones);
                oos.writeObject(ob_out);
                System.out.println("Objeto Enviado Exitosamente a " + cl.getInetAddress());
                oos.flush();
                oos.close();
            }
            cl.close();
            ss.close();
            System.out.println("Servicio Terminado para " + cl.getInetAddress());
        } catch (Exception e) {
            System.out.println("Se encontro un Error en " + e);
        }
    }
    

    // Envio de la Imagenes 
    public void enviarImagenes() {
        System.out.println("Comenzando la Tranferencia de Archivos");
        llenarMapa();
        try {
            Socket cl;
            try (ServerSocket ss = new ServerSocket(8023); Socket c = ss.accept()) {
                BufferedInputStream bis = null;
                BufferedOutputStream bos = null;
                for (x = 0; x < listaCanciones.size(); x++) {
                    try {
                        modeloCancion itemRola = (modeloCancion) listaCanciones.get(x);
                        bis = new BufferedInputStream(new FileInputStream("Img_Servidor/" + itemRola.pathimg));
                        bos = new BufferedOutputStream(c.getOutputStream());

                        byte[] buf = new byte[1024];
                        System.out.println("Tamaño Archivo:" + bis.available() + "bytes.." + "Img_Servidor/" + itemRola.pathimg);
                        int b_leidos;
                        while ((b_leidos = bis.read(buf, 0, buf.length)) != -1) {
                            bos.write(buf, 0, b_leidos);
                            bos.flush();
                        }
                    } catch (FileNotFoundException ex) {
                        System.out.println("Se encontro un Error en " + ex);
                        Logger.getLogger(main_Servidor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                bos.close();
                bis.close();
            }
        } catch (IOException ex) {
            System.out.println("Se encontro un Error en " + ex);
            Logger.getLogger(main_Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public void llenarMapa() {
        mapaCanciones.put("1", new modeloCancion("0", "Not Giving Up on Love", "Armin van B. vs Sophie Ellis Bextor", "2010", 59, "Portada_1.jpg",0));
        mapaCanciones.put("2", new modeloCancion("1", "In For The Kill", "La Roux", "2011", 64, "Portada_2.jpg",0));
        mapaCanciones.put("3", new modeloCancion("2", "Hot Mess", "Chromeo ft. La Roux", "2012", 43, "Portada_3.jpg",0));
        mapaCanciones.put("4", new modeloCancion("3", "Ellie Goulding", "Lights", "2011", 33, "Portada_4.jpg",0));
        mapaCanciones.put("5", new modeloCancion("4", "Metallica", "Seek And Destroy", "1991", 52, "Portada_5.jpg",0));
        mapaCanciones.put("6", new modeloCancion("5", "Question", "System of a Down", "1998", 63, "Portada_6.jpg",0));
        mapaCanciones.put("7", new modeloCancion("6", "Something About", "Daft Punk", "1997", 49, "Portada_7.jpg",0));
        mapaCanciones.put("8", new modeloCancion("7", "Artificial Boy", "Fron Line Assembly", "2009", 57, "Portada_8.jpg",0));
        mapaCanciones.put("9", new modeloCancion("8", "Crush Crush Crush", "Paramore", "2008", 51, "Portada_9.jpg",0));
        mapaCanciones.put("10", new modeloCancion("9", "A Little Peas Of Heaven", "Avenged Sevetfold", "2007", 39, "Portada_10.jpg",0));
    }

    public void convertirMaptoList() {
        for (x = 1; x <= mapaCanciones.size(); x++) {
            modeloCancion Rola = (modeloCancion) mapaCanciones.get(String.valueOf(x));
            modeloCancion Song = new modeloCancion(Rola.idcancion, Rola.nombre, Rola.autor, Rola.año, Rola.precio, Rola.pathimg,Rola.numDesc);
            listaCanciones.add(Song);
        }
    }

    public static void main(String[] args) {
        main_Servidor Servidor = new main_Servidor();
        Servidor.enviarCatalogo();
        Servidor.convertirMaptoList();
        Servidor.enviarImagenes();
    }
}
