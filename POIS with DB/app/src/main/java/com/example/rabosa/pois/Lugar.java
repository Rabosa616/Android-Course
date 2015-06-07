package com.example.rabosa.pois;

/**
 * Created by Rabosa on 06/06/2015.
 */
public class Lugar {
    private TipoLugar tipo;
    private String nombre;
    private String direccion;
    private GeoPunto posicion;
    private String foto;
    private int telefono;
    private String url;
    private String comentario;
    private long fecha;
    private float valoracion;

    public Lugar(String nombre, String direccion, double longitud,
                 double latitud, TipoLugar tipo, int telefono,
                 String url, String comentario, int valoracion) {
        fecha = System.currentTimeMillis();
        posicion = new GeoPunto(longitud, latitud);
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.url = url;
        this.comentario = comentario;
        this.valoracion = valoracion;
        fecha = System.currentTimeMillis();
        posicion = new GeoPunto(0,0);
        this.tipo = tipo;
    }

    public Lugar() {
        fecha = System.currentTimeMillis();
        posicion = new GeoPunto(0,0);
        this.tipo = TipoLugar.OTROS;

    }

    @Override
    public String toString() {

        return "Lugar [nombre="+ nombre+ ", direccion="+ direccion
                + ", posicion="+ posicion+ ", foto="+ foto+ ", telefono="
                + telefono+ ", url="+ url+ ", comentario="+ comentario
                + ", fecha="+ fecha+ ", valoracion="+ valoracion+ "]";
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public int getTelefono() {
        return telefono;
    }

    public String getUrl() {
        return url;
    }

    public String getComentario() {
        return comentario;
    }

    public long getFecha() {
        return fecha;
    }

    public float getValoracion() {
        return valoracion;
    }

    public void setValoracion(float valoracion) {
        this.valoracion = valoracion;
    }

    public TipoLugar getTipo() {
        return tipo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTipo(TipoLugar tipo) {
        this.tipo = tipo;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public GeoPunto getPosicion() {
        return posicion;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getFoto() {
        return foto;
    }

    public void setPosicion(GeoPunto posicion) {
        this.posicion = posicion;
    }

    public void setFecha(long fecha) {
        this.fecha = fecha;
    }
}