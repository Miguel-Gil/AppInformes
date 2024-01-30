package es.ieslosmontecillos.appinformes;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.*;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class AppInformesController {
    public static Connection conexion = AppInformes.conectaBD();
    AppInformes appInformes = new AppInformes();

    @javafx.fxml.FXML
    private MenuBar menuBar;
    @javafx.fxml.FXML
    private Menu informes;
    @javafx.fxml.FXML
    private MenuItem listadoFacturas;
    @javafx.fxml.FXML
    private MenuItem ventasTotales;
    @javafx.fxml.FXML
    private MenuItem facturasPorCliente;
    @javafx.fxml.FXML
    private MenuItem subinforme;
    @javafx.fxml.FXML
    private MenuItem salir;
    @javafx.fxml.FXML
    private AnchorPane root;
    @javafx.fxml.FXML
    private Button btnIntroducir;
    @javafx.fxml.FXML
    private TextField TfIntroducir;
    @javafx.fxml.FXML
    private Text TxItroducir;

    @javafx.fxml.FXML
    public void loadListadoFacturas(ActionEvent actionEvent) {
        try {
            JasperReport jr = (JasperReport)JRLoader.loadObject(AppInformes.class.getResource("ListadoFacturas.jasper"));
            //Map de parámetros
            Map parametros = new HashMap();
            JasperPrint jp = (JasperPrint) JasperFillManager.fillReport(jr, parametros, conexion);
            JasperViewer.viewReport(jp,false);
        } catch (JRException ex) {
            System.out.println("Error al recuperar el jasper");
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    @javafx.fxml.FXML
    public void loadVentasTotales(ActionEvent actionEvent) {
        try {
            JasperReport jr = (JasperReport) JRLoader.loadObject(AppInformes.class.getResource("VentasTotales.jasper"));
            //Map de parámetros
            Map parametros = new HashMap();
            JasperPrint jp = (JasperPrint) JasperFillManager.fillReport(jr, parametros, conexion);
            JasperViewer.viewReport(jp, false);
        } catch (JRException ex) {
            System.out.println("Error al recuperar el jasper");
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    @javafx.fxml.FXML
    public void loadFacturasPorCliente(ActionEvent actionEvent) {

        btnIntroducir.setText("Introducir ID Cliente");

        btnIntroducir.setOnAction((ActionEvent a) -> {
            try {
                JasperReport jr = (JasperReport) JRLoader.loadObject(AppInformes.class.getResource("FacturasPorClientes.jasper"));
                //Map de parámetros
                Map parametros = new HashMap();
                parametros.put("IDCliente", Integer.parseInt(TfIntroducir.getText()));
                JasperPrint jp = (JasperPrint) JasperFillManager.fillReport(jr, parametros, conexion);
                JasperViewer.viewReport(jp, false);
            } catch (JRException ex) {
                System.out.println("Error al recuperar el jasper");
                JOptionPane.showMessageDialog(null, ex);
            }
        });

    }

    @javafx.fxml.FXML
    public void loadSubinformes(ActionEvent actionEvent) {
        try {
            JasperReport jr = (JasperReport) JRLoader.loadObject(AppInformes.class.getResource("SubinformeFacturas.jasper"));
            //Map de parámetros
            Map parametros = new HashMap();
            JasperPrint jp = (JasperPrint) JasperFillManager.fillReport(jr, parametros, conexion);
            JasperViewer.viewReport(jp, false);
        } catch (JRException ex) {
            System.out.println("Error al recuperar el jasper");
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    @javafx.fxml.FXML
    public void Close(ActionEvent actionEvent) throws Exception {
        appInformes.stop();
    }


}