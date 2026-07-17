package back_end;
import Utils.Credenciales;

import java.sql.*;
import java.util.ArrayList;

public class Conexion {

    Connection cn;
    PreparedStatement ps;
    ResultSet rs;
    public Conexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventario",
                    Credenciales.Usuario,Credenciales.Contraseña);
            System.out.println("Conexion establecida!");
        } catch (Exception e) {
            System.out.println("Error al conectar" + e);
        }
    }

    public int RegistrarTipo(String tipo){
        int res = 0;
        try {
            ps = cn.prepareStatement("call agregar_tipo(?)");
            ps.setString(1, tipo);
            res = ps.executeUpdate();
            System.out.println("Registro agregado exitosamente");
        }catch (Exception e){
            System.out.println("Error al agregar" + e);
        }
        return res;
    }

    public int RegistrarProducto(String nombre,int id_tipo,int cantidad,float precio){
        int res = 0;
        try {
            ps = cn.prepareStatement("call agregar_producto(?,?,?,?)");
            ps.setString(1,nombre);
            ps.setInt(2,id_tipo);
            ps.setInt(3,cantidad);
            ps.setFloat(4,precio);
            res = ps.executeUpdate();
            System.out.println("Registro agregado exitosamente");
        } catch (Exception e){
            System.out.println("Error al agregar" + e);
        }
        return res;
    }

    public int RestarCantidad(int id_producto,int cantidad){
        int res = 0;
        try {
            ps = cn.prepareStatement("call restar_cantidad(?,?)");
            ps.setInt(1, id_producto);
            ps.setInt(2,cantidad);
            res = ps.executeUpdate();
            System.out.println("Cantidad restada exitosamente");
        }catch (Exception e){
            System.out.println("Error al restar" + e);
        }
        return res;
    }

    public ArrayList<Productos> ListaProductos(){
        ArrayList<Productos> productos = new ArrayList<>();

        try (PreparedStatement ps = cn.prepareStatement("select * from producto_tipo_catidad");
             ResultSet rs = ps.executeQuery()) {

            while(rs.next()){
                String nombre = rs.getString("nombre");
                String tipo = rs.getString("tipo");
                int cantidad = rs.getInt("Cantidad");
                float precio = rs.getFloat("precio");

                productos.add(new Productos(nombre, tipo, cantidad, precio));
            }

            System.out.println("Listado Completado");
        } catch (Exception e){
            System.out.println("Error al listar " + e);
        }
        return productos;
    }

    public ArrayList<Tipos> ListaTipo(){
        ArrayList<Tipos> tipos = new ArrayList<>();

        try (PreparedStatement ps = cn.prepareStatement("select * from tipos");
        ResultSet rs = ps.executeQuery()){
            while(rs.next()){
                int id_tipo = rs.getInt("ID_tipo");
                String tipo = rs.getString("tipo");
                tipos.add(new Tipos(id_tipo, tipo));
            }

        }catch (Exception e){
            System.out.println("Error al listar" + e);
        }

        return tipos;
    }

    public ArrayList<Productos> BuscarProductos(String buscar){
        ArrayList<Productos> productos = new ArrayList<>();

        try (PreparedStatement ps = cn.prepareStatement(
                "SELECT * FROM producto_tipo_catidad WHERE nombre LIKE ? OR tipo LIKE ?")) {

            ps.setString(1, "%" + buscar + "%");
            ps.setString(2, "%" + buscar + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String nombre = rs.getString("nombre");
                    String tipo = rs.getString("tipo");
                    int cantidad = rs.getInt("Cantidad");
                    float precio = rs.getFloat("precio");

                    productos.add(new Productos(nombre, tipo, cantidad, precio));
                }
            }
        } catch (Exception e){
            System.out.println("Error al listar " + e);
        }

        return productos;
    }

}
