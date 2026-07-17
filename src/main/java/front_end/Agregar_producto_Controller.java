package front_end;

import Utils.Paths;
import back_end.Conexion;
import back_end.Tipos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.util.ArrayList;

public class Agregar_producto_Controller {

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
    private void initialize(){

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
                try {
                    abrirVentanaNuevoTipo();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }

    private void abrirVentanaNuevoTipo() throws Exception {

        System.out.println("Abriendo ventana para crear un nuevo tipo...");

        FXMLLoader loader = new FXMLLoader(getClass().
                getResource(Paths.AgregarTipo));

        Parent pane = loader.load();

        Scene scene = new Scene(pane);

        Stage stage = (Stage) btnCancelar.getScene().getWindow();

        stage.setScene(scene);

        stage.show();

    }

    @FXML
    void btnCancelar(ActionEvent event) {

        cerrar();

    }

    @FXML
    void btnGuardar(ActionEvent event) {

        if(cmbTipo.getSelectionModel().getSelectedItem() != null
                && !cmbTipo.getSelectionModel().getSelectedItem().getTipo().trim().isEmpty()
                && !txtNombre.getText().trim().isEmpty()
                && !txtPrecio.getText().trim().isEmpty()
                && !txtCantidad.getText().trim().isEmpty()) {
            try {
                String nombre = txtNombre.getText();
                int ID_tipo = cmbTipo.getSelectionModel().getSelectedItem().getId_tipo();
                int cantidad = Integer.parseInt(txtCantidad.getText());
                float precio = Float.parseFloat(txtPrecio.getText());
                int i = cn.RegistrarProducto(nombre, ID_tipo, cantidad, precio);
                System.out.println("Enviando producto...");
                switch (i) {
                    case 0:
                        System.out.println("Solicitud fallida");
                        AlertaError("Solicitud fallida",
                                "El servidor no proceso de forma correcta los datos");
                        break;
                    case 1:
                        System.out.println("Solicitud exitosa");
                        cerrar();
                        break;
                }
            }catch (Exception e){
                    AlertaError("Error en el campo",
                            "Tipo de dato introducido distinto al solicitado");
                }
        } else{
            AlertaError("Campos incompletos",
                    "Rellene todos los campos solicitados (*)");
            System.out.println("Campos incompletos");
        }
    }

    private void cerrar() {

        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();

    }

    private void AlertaError(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR); // Puedes usar ERROR, WARNING o INFORMATION
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

}
