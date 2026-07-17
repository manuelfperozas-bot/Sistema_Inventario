package applicacion;

import Utils.Paths;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class App extends Application {

    public static App app;

    public static void main(String[] args) {

        launch();

    }

    @Override
    public void start(Stage stage) throws Exception {

        app = this;

        abrirPanelPrincipal();

    }

    public void abrirPanelPrincipal() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.PanelPrincipal));

        Parent pane = loader.load();

        Scene scene = new Scene(pane);

        Stage stage = new Stage();

        stage.setScene(scene);

        stage.show();

    }

    public void abrirAgregarProducto() throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.AgregarProducto));

        Parent pane = loader.load();

        Scene scene = new Scene(pane);

        Stage stage = new Stage();

        stage.setScene(scene);

        stage.showAndWait();

    }

    public void abrirAgregarTipo() throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.AgregarTipo));

        Parent pane = loader.load();

        Scene scene = new Scene(pane);

        Stage stage = new Stage();

        stage.setScene(scene);

        stage.show();

    }

}
