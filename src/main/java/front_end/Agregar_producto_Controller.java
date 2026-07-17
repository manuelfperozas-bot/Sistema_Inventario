package front_end;

import back_end.Conexion;
import back_end.Tipos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

import java.util.ArrayList;

public class Agregar_producto_Conroler {

    Conexion cn = new Conexion();
    ArrayList<Tipos> tipos = cn.ListaTipo();

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnGuardar;

    @FXML
    private ComboBox<Tipos> cmbTipo;

    @FXML
    private TextField txtCantidad;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtPrecio;

    @FXML
    public void initialize() {

        cmbTipo.setConverter(new StringConverter<Tipos>(){
            @Override
            public String toString(Tipos tipo) {
                return (tipo != null) ? tipo.toString() : "";
            }
            @Override
            public Tipos fromString(String string) {
                return null;
            }
        });

        cmbTipo.getItems().addAll(tipos);
        cmbTipo.getItems().add(new Tipos(-1, "➕ Agregar nueva categoría..."));

        cmbTipo.getSelectionModel().selectedItemProperty().addListener((observable,
                                                                        oldValue, newValue) -> {
            if (newValue != null && newValue.getId_tipo() == -1) {
                abrirVentanaNuevoTipo();
            }
        });

    }

    private void abrirVentanaNuevoTipo() {

        System.out.println("Abriendo ventana para crear un nuevo tipo...");

    }

    @FXML
    void btnCancelar(ActionEvent event) {



    }

    @FXML
    void btnGuardar(ActionEvent event) {

        if(cmbTipo.getSelectionModel().getSelectedItem() != null
                && txtNombre.getText() != null && txtPrecio.getText() != null
                && txtCantidad.getText() != null) {
            int i = cn.RegistrarProducto(txtNombre.getText(),
                    cmbTipo.getValue().getId_tipo(),
                    Integer.parseInt(txtCantidad.getText()), Float.parseFloat(txtPrecio.getText()));
            System.out.println("Enviando producto...");
            switch (i){
                case 0:
                    System.out.println("Solicitud fallida");
                    break;
                case 1:
                    System.out.println("Solicitud exitosa");
                    break;
            }
        }

        else{

            System.out.println("Campos incompletos");

        }

    }


}
