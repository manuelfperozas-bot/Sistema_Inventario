package back_end;

public class Productos {

    private String nombre;
    private String tipo;
    private int cantidad;
    private  float precio;

    public Productos(String nombre, String tipo, int cantidad,  float precio) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.precio = precio;
    }


    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public float getPrecio() {
        return precio;
    }
}
