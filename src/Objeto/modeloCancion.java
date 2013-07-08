
package Objeto;

import java.io.Serializable;

/**
 *
 * @author Home
 */
public class modeloCancion implements Serializable{
    public String idcancion;
    public String nombre;
    public String autor;
    public String año;
    public int precio;
    public String pathimg;
    public int numDesc;
    
    public modeloCancion(String idcancion,String nombre, String autor, String año, int precio, String pathimg,int numDesc){
        this.idcancion = idcancion;
        this.nombre = nombre;
        this.autor = autor;
        this.año = año;
        this.precio = precio;
        this.pathimg = pathimg;
        this.numDesc =  numDesc;
    }

    public String getIdcancion() {
        return idcancion;
    }

    public void setIdcancion(String idcancion) {
        this.idcancion = idcancion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getAño() {
        return año;
    }

    public void setAño(String año) {
        this.año = año;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getPathimg() {
        return pathimg;
    }

    public void setPathimg(String pathimg) {
        this.pathimg = pathimg;
    }

    public int getNumDesc() {
        return numDesc;
    }

    public void setNumDesc(int numDesc) {
        this.numDesc = numDesc;
    }
    
    
}
