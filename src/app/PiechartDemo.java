package app;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import chart.BetreuerDAO;
import chart.BetreuerDAO;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class PiechartDemo extends Application{
	private PieChart pie = new PieChart();
	private BetreuerDAO dao;
	private Label caption = new Label("");

	@Override
	public void start(Stage primaryStage) throws Exception {

		AnchorPane root = new AnchorPane();

        setPieChartData();
        caption.setTextFill(Color.BLACK);
        caption.setStyle("-fx-font: 10 arial;");

        pie.setPrefSize(700, 700);
        root.getChildren().addAll(pie, caption);

        Scene scene = new Scene(root, 800, 800);

        primaryStage.setTitle("PieChart Durchschnittliches Gehalt je Abteilung");

        primaryStage.setScene(scene);
        primaryStage.show();

	}

	/**
	 * Mit dieser Methode greifen wir auf die Querie PieChart zu
	 * In der Piechart selber wird per MousePressed der Wert angezeigt
	 * Der Wert ins Label und mit DecimalFormat ins Deutsche Format
	 * gesetzt
	 * @throws Exception
	 */
	public void setPieChartData() throws Exception{
		 dao = new BetreuerDAO();
	     pie = new PieChart(dao.getGehaltGraphStatistics());

	     //Wir wollen, das wenn wir mit der Mouse in das Piechart klicken
	     //den Wert angezeigt bekommen.Deshalb EventHandler
	        for (final PieChart.Data data : pie.getData()) {
	            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
	                @Override
	                public void handle(MouseEvent e) {
	                    caption.setTranslateX(e.getSceneX());
	                    caption.setTranslateY(e.getSceneY());
	                    double wert = (double)data.getPieValue();

	                    //Formatierung ins Deutsche Format
	                    DecimalFormat df = new DecimalFormat("###,##0.00");
						df.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.GERMAN));
						String formattedDob = df.format(wert);

	                    caption.setText(formattedDob);
	               	                }
	            });
	        }
	}

	public static void main(String[] args) {
		launch(args);
	}

}
