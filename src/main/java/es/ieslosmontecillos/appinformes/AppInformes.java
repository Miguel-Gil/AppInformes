package es.ieslosmontecillos.appinformes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class AppInformes extends Application {
    static Scanner sc = new Scanner(System.in);
    @Override
    public void start(Stage primaryStage) throws ClassNotFoundException, IOException {
        //establecemos la conexión con la BD
        conectaBD();
        //Creamos la escena
        primaryStage.setTitle("AppInformes");
        Pane myPane = (Pane) FXMLLoader.load(AppInformes.class.getResource("AppInformes.fxml"));
        Scene myScene = new Scene(myPane);
        primaryStage.setScene(myScene);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        try {
            DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/test;shutdown=true");
        } catch (Exception ex) {
            System.out.println("No se pudo cerrar la conexion a la BD");
            ex.printStackTrace();
        }
    }

    public static Connection conectaBD() {
        //Establecemos conexión con la BD
        String baseDatos = "jdbc:hsqldb:hsql://localhost/test";
        String usuario = "SA";
        String clave = "";
        Connection conexion = null;
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            conexion = DriverManager.getConnection(baseDatos, usuario, clave);
        } catch (ClassNotFoundException cnfe) {
            System.err.println("Fallo al cargar JDBC");
            cnfe.printStackTrace();
            System.exit(1);
        } catch (SQLException sqle) {
            System.err.println("No se pudo conectar a BD");
            sqle.printStackTrace();
            System.exit(1);
        } catch (Exception ex){
            System.err.println("Imposible Conectar");
            ex.printStackTrace();
            System.exit(1);
        }
        return conexion;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
