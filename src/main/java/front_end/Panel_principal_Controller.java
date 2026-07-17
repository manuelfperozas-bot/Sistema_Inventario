package front_end;

import applicacion.App;
import back_end.Conexion;
import back_end.Precio_Dolar;
import back_end.Productos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Panel_principal_Controller {

    Precio_Dolar Dolar = new Precio_Dolar();
    Conexion cn = new Conexion();

    @FXML
    private void initialize() {

        btnTasaBCV.setText(String.format("💵 BCV: %6.2f Bs.", Dolar.getPrecio()));

        MostrarTabla();

    }

    public void MostrarTabla(){

        ArrayList<Productos> productos = cn.ListaProductos();

        ObservableList<Productos> observableProductos = FXCollections.observableArrayList(productos);

        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colCategoria.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colStock.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        colprecio.setCellValueFactory(new PropertyValueFactory<>("precio"));

        tablaProductos.setItems(observableProductos);

    }

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnTasaBCV;

    @FXML
    private TableColumn<Productos, String> colCategoria;

    @FXML
    private TableColumn<Productos, String> colNombre;

    @FXML
    private TableColumn<Productos, Integer> colStock;

    @FXML
    private TableColumn<Productos, Float> colprecio;

    @FXML
    private TableView<Productos> tablaProductos;

    @FXML
    private TextField txtBuscar;

    @FXML
    void btnAgregar(ActionEvent event) throws Exception {

        btnAgregar.setDisable(true);
        App.app.abrirAgregarProducto();
        MostrarTabla();
        btnAgregar.setDisable(false);

    }

    @FXML
    void btnEditar(ActionEvent event) {

    }

    @FXML
    void btnInventario(ActionEvent event) {

    }

    @FXML
    void btnRegCambios(ActionEvent event) {

    }

    @FXML
    void btnReportes(ActionEvent event) {

    }

    @FXML
    void btnSalVent(ActionEvent event) {

    }

    @FXML
    void btnTasaBCV(ActionEvent event) {

    }

    @FXML
    void txtBuscar_change(KeyEvent event) {

        ArrayList<Productos> t_buscar = cn.BuscarProductos(txtBuscar.getText());
        RellenarTabla(t_buscar);

    }

    private void RellenarTabla(ArrayList<Productos> i) {

        ObservableList<Productos> observableProductos = FXCollections.observableArrayList(i);

        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colCategoria.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colStock.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        colprecio.setCellValueFactory(new PropertyValueFactory<>("precio"));

        tablaProductos.setItems(observableProductos);

    }

    private void cerrar() {

        Stage stage = (Stage) btnAgregar.getScene().getWindow();
        stage.close();

    }

}
