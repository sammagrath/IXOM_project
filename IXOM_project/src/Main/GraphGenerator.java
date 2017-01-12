package Main;

import java.util.ArrayList;

import Read_Data.dataPoint;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.Axis;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import samThreshold.Flag;
import timeconverter.TimeConverter;

public class GraphGenerator {
	
	private TimeConverter timeConverter;

	public void generateGraphs(ArrayList<dataPoint> data, String name, ArrayList<Flag> flagList,
			ArrayList<String> phaseNames) {

		Stage window = new Stage();
		Group root = new Group();

		final TabPane tabPane = new TabPane();

		tabPane.setPrefSize(1000, 600);
		tabPane.setSide(Side.TOP);
		tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

		BorderPane borderPane = new BorderPane();

		final Tab TurbTab = new Tab();
		setTurbTab(TurbTab, name, data, flagList, phaseNames);

		final Tab CondTab = new Tab();
		setCondTab(CondTab, name, data, flagList, phaseNames);

		final Tab TempTab = new Tab();
		setTempTab(TempTab, name, data, flagList, phaseNames);

		tabPane.getTabs().addAll(TurbTab, CondTab, TempTab);
		borderPane.setCenter(tabPane);
		root.getChildren().add(borderPane);

		Scene scene = new Scene(root, Color.WHITE);
		window.setTitle("Line Chart Sample");
		scene.getStylesheets().add("viper.css");

		window.setScene(scene);
		window.show();

	}

	@SuppressWarnings("unchecked")
	private void setCondTab(Tab CondTab, String name, ArrayList<dataPoint> data, ArrayList<Flag> flagList,
			ArrayList<String> phaseNames) {
		
		timeConverter = new TimeConverter();

		final NumberAxis xAxis = new NumberAxis();
		final NumberAxis yAxis = new NumberAxis();
		xAxis.setLabel("Time");
		yAxis.setLabel("Conductivity");
		// creating the chart
		final AreaChart<Number, Number> lineChart = new AreaChart<Number, Number>(xAxis, yAxis);
		lineChart.setTitle("Conductivity - " + name);
		// defining a series

		int count = 0;
		int zonecount = 1;

		ArrayList<Series<Number, Number>> serieslist = new ArrayList<Series<Number, Number>>();

		for (String phasename : phaseNames) {
			
			XYChart.Series<Number, Number> series = new XYChart.Series<Number, Number>();
			series.setName(phasename);
			serieslist.add(series);

		}
		for (dataPoint d : data) {
			if (d.getZone() == zonecount) {
				System.out.println(d.getTime());
				System.out.println(timeConverter.HMSToDec(d.getTime()));
				
				serieslist.get(zonecount-1).getData().add(new XYChart.Data<Number, Number>((timeConverter.HMSToDec(d.getTime())*86400), d.getConductivity()));
				
				
				count++;
			}
			if (d.getZone() != zonecount) {
				zonecount++;
			}
		}
		
		lineChart.getData().addAll(serieslist);

		// populating the series with data

		CondTab.setText("Conductivity");
		CondTab.setContent(lineChart);
	}

	@SuppressWarnings("unchecked")
	private void setTurbTab(Tab TurbTab, String name, ArrayList<dataPoint> data, ArrayList<Flag> flagList,
			ArrayList<String> phaseNames) {
		final NumberAxis xAxis = new NumberAxis();
		final NumberAxis yAxis = new NumberAxis();
		xAxis.setLabel("Time");
		yAxis.setLabel("Turbidity");
		// creating the chart
		final AreaChart<Number, Number> lineChart = new AreaChart<Number, Number>(xAxis, yAxis);
		lineChart.setTitle("Turbidity - " + name);
		// defining a series
		int count = 0;
		int zonecount = 1;

		ArrayList<Series<Number, Number>> serieslist = new ArrayList<Series<Number, Number>>();

		for (String phasename : phaseNames) {
			
			XYChart.Series<Number, Number> series = new XYChart.Series<Number, Number>();
			series.setName(phasename);
			serieslist.add(series);

		}
		for (dataPoint d : data) {
			if (d.getZone() == zonecount) {
				serieslist.get(zonecount-1).getData().add(new XYChart.Data<Number, Number>(count, d.getTurbidity()));
				count++;
			}
			if (d.getZone() != zonecount) {
				zonecount++;
			}
		}
		
		lineChart.getData().addAll(serieslist);
		TurbTab.setText("Turbidity");
		TurbTab.setContent(lineChart);
	}

	@SuppressWarnings("unchecked")
	private void setTempTab(Tab TempTab, String name, ArrayList<dataPoint> data, ArrayList<Flag> flagList,
			ArrayList<String> phaseNames) {

		final NumberAxis xAxis = new NumberAxis();
		final NumberAxis yAxis = new NumberAxis();
		xAxis.setLabel("Time");
		yAxis.setLabel("Temperature");
		// creating the chart
		final AreaChart<Number, Number> lineChart = new AreaChart<Number, Number>(xAxis, yAxis);
		lineChart.setTitle("Temperature - " + name);
		// defining a series
		int count = 0;
		int zonecount = 1;

		ArrayList<Series<Number, Number>> serieslist = new ArrayList<Series<Number, Number>>();

		for (String phasename : phaseNames) {
			
			XYChart.Series<Number, Number> series = new XYChart.Series<Number, Number>();
			series.setName(phasename);
			serieslist.add(series);

		}
		for (dataPoint d : data) {
			if (d.getZone() == zonecount) {
				serieslist.get(zonecount-1).getData().add(new XYChart.Data<Number, Number>(count, d.getTemp()));
				count++;
			}
			if (d.getZone() != zonecount) {
				zonecount++;
			}
		}
		
		lineChart.getData().addAll(serieslist);
		

		TempTab.setText("Temperature");
		TempTab.setContent(lineChart);
	}

}
