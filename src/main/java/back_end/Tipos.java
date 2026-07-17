package back_end;

public class Tipos {

    private int id_tipo;
    private String tipo;

    public Tipos(int id_tipo, String tipo){
        this.id_tipo = id_tipo;
        this.tipo = tipo;
    }

    public int getId_tipo() {
        return id_tipo;
    }

    public String getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return this.tipo;
    }

    public boolean ExisteTipo(String nombre){
        return tipo.equals(nombre);
    }

}
