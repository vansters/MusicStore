package Objeto;

import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author Home
 */
public class Objeto implements Serializable{
    public HashMap mapaCanciones;
    
    public Objeto (HashMap mapaCanciones){
        this.mapaCanciones = mapaCanciones;
    }

    public HashMap getMapaCanciones() {
        return mapaCanciones;
    }

    public void setMapaCanciones(HashMap mapaCanciones) {
        this.mapaCanciones = mapaCanciones;
    }
    
    
}
