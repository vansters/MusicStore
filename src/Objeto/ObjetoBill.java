package Objeto;

import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author Home
 */
public class ObjetoBill implements Serializable{
    public HashMap mapaBill;
    
    public ObjetoBill (HashMap mapaCanciones){
        this.mapaBill = mapaCanciones;
    }

    public HashMap getMapaCanciones() {
        return mapaBill;
    }

    public void setMapaCanciones(HashMap mapaCanciones) {
        this.mapaBill = mapaCanciones;
    }
    
    
}
