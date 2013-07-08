package Cliente;

import Objeto.Objeto;
import Objeto.modeloCancion;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Home
 */
public class main_Cliente{
    
    public HashMap<String, modeloCancion> canciones = new HashMap<>();
    public ArrayList listaCanciones = new ArrayList();
    public int x;

    public main_Cliente() {

    }

    public void recibirCatalogo() {
        try {
            Socket cl = new Socket(InetAddress.getByName("localhost"), 8023);
            System.out.println("Conexion con Servidor Exitosa");
            try (ObjectInputStream ois = new ObjectInputStream(cl.getInputStream())) {
                System.out.println("Flujo de Datos Asociado Corectamente en " + cl.getInetAddress());
                Objeto ob_in = (Objeto) ois.readObject();
                System.out.println("Objeto Recibido Exitosamente desde " + cl.getInetAddress());
                canciones = ob_in.mapaCanciones;
                System.out.println("El Numero de Canciones Recibidas es :  " + canciones.size());
                ois.close();
                cl.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Se encontro un Error en " + e);
        } 
    }

    public void recibirImagenes() {
        System.out.println("Comenzando la Recepción de Archivos");
        BufferedOutputStream bos = null;
        BufferedInputStream bis = null;
        try {
            Socket cl = new Socket(InetAddress.getByName("localhost"), 8023);
            for (x = 0; x < listaCanciones.size(); x++) {
                modeloCancion itemRola = (modeloCancion) listaCanciones.get(x);
                bos = new BufferedOutputStream(new FileOutputStream("Img_Cliente/" + itemRola.pathimg));
                bis = new BufferedInputStream(cl.getInputStream());
                
                int leidos = 0;
                byte[] buf = new byte[1024];

                while ((leidos = bis.read(buf, 0, buf.length)) == 1024) {
                    bos.write(buf, 0, leidos);
                    bos.flush();
                }//while
                bos.write(buf, 0, leidos);
                bos.flush();
                System.out.println("Copiado en Img_Cliente/" + itemRola.pathimg);
            }
            bis.close();
            bos.close();
            cl.close();
        } catch (Exception e) {
            System.out.println("Se encontro un Error en " + e);
        }
    }

    public void convertirMaptoList() {
        for (x = 1; x <= canciones.size(); x++) {
            modeloCancion Rola = (modeloCancion) canciones.get(String.valueOf(x));
            modeloCancion Song = new modeloCancion(Rola.idcancion, Rola.nombre, Rola.autor, Rola.año, Rola.precio, Rola.pathimg,Rola.numDesc);
            listaCanciones.add(Song);
        }
    }

    /*
    public static void main(String[] args) {
        main_Cliente Cliente = new main_Cliente();
        Cliente.recibirCatalogo();
        Cliente.convertirMaptoList();
        Cliente.recibirImagenes();
    }*/
}
