package controlador;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    /**
     *
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException { //Compone la escena
        Scene scene = new Scene(loadFXML("visualizaCuentas"), 545, 352); // Carga la escena
        stage.getIcons().add(new Image(CuentaBater.class.getResourceAsStream("/images/logo.png"))); //Carga el logo
        stage.setTitle("Aplicación cuentas bancarias"); //Carga el título
        stage.setScene(scene);
        stage.show();
    }

    private static Parent loadFXML(String fxml) throws IOException { // Carga el fxml

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/vista/"+fxml+".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}