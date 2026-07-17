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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;

public class Agregar_tipo_Controller {

    Conexion cn = new Conexion();
    ArrayList<Tipos> tipos = cn.ListaTipo();

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnGuardar;

    @FXML
    private TextField txtNombreTipo;

    @FXML
    void btnCancelar(ActionEvent event) throws Exception {

        cerrar();

    }

    @FXML
    void btnGuardar(ActionEvent event) throws Exception{

        if(!txtNombreTipo.getText().trim().isEmpty()){

            String nombre = txtNombreTipo.getText().trim();
            int i = cn.RegistrarTipo(nombre);

            switch (i){
                case 0: AlertaError("Datos duplicados","Categoria ya existente");
                break;

                case 1: cerrar();
                break;
            }

        }else{

            AlertaError("Campos incompletos",
                    "Rellene todos los campos solicitados");

        }

    }

    private void AlertaError(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR); // Puedes usar ERROR, WARNING o INFORMATION
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    private void cerrar() throws Exception{

        FXMLLoader loader = new FXMLLoader(getClass().
                getResource(Paths.AgregarProducto));

        Parent pane = loader.load();

        Scene scene = new Scene(pane);

        Stage stage = (Stage) btnCancelar.getScene().getWindow();

        stage.setScene(scene);

        stage.show();

    }

}
