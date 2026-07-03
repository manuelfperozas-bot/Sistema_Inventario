package applicacion;

import Utils.Paths;
import front_end.Panel_principal_Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class App extends Application {

    public static void main(String[] args) {

        launch();

    }

    @Override
    public void start(Stage stage) throws Exception {

        abrirPanelPrincipal();

    }

    public void abrirPanelPrincipal() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.PanelPrincipal));

        Parent pane = loader.load();

        Panel_principal_Controller controller = loader.getController();

        Scene scene = new Scene(pane);

        Stage stage = new Stage();

        stage.setScene(scene);

        stage.show();

        controller.TasaBCV();

        controller.RellenarTabla();
    }

}
