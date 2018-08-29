package app;

import chart.BetreuerDAO;
import chart.BetreuerDAO;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class BarChartDemo extends Application{
	private BarChart bar;
	private BetreuerDAO dao;
	private Label caption = new Label("");

	@Override
	public void start(Stage primaryStage) throws Exception {
		    BetreuerDAO dao = new BetreuerDAO();

			CategoryAxis xAxis = new CategoryAxis();
			xAxis.setLabel("Geschlecht");

			NumberAxis yAxis = new NumberAxis();
			yAxis.setLabel("Anzahl (in Stueck)");

			BarChart<String, Number> chart = new BarChart<>(xAxis, yAxis);
			chart.setTitle("Ansicht");
			// Daten ermitteln
			ObservableList<XYChart.Series<String,Number>> chartData =
			dao.getCountrySeries();
			chart.setData(chartData);
			StackPane root = new StackPane(chart);
			Scene scene = new Scene(root, 1200,800);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Bar Chart");
			primaryStage.show();

	}

	public void setBarChartData() throws Exception{
		 dao = new BetreuerDAO();


	}

	public static void main(String[] args) {
		launch(args);
	}


}
