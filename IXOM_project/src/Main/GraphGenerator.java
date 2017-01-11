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
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import samThreshold.Flag;

public class GraphGenerator {

	public void generateGraphs(ArrayList<dataPoint> data, String name, ArrayList<Flag> flagList) {

		Stage window = new Stage();
		Group root = new Group();

		final TabPane tabPane = new TabPane();

		tabPane.setPrefSize(1000, 600);
		tabPane.setSide(Side.TOP);
		tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

		BorderPane borderPane = new BorderPane();

		final Tab TurbTab = new Tab();
		setTurbTab(TurbTab, name, data, flagList);

		final Tab CondTab = new Tab();
		setCondTab(CondTab, name, data, flagList);

		final Tab TempTab = new Tab();
		setTempTab(TempTab, name, data, flagList);

		tabPane.getTabs().addAll(TurbTab, CondTab, TempTab);
		borderPane.setCenter(tabPane);
		root.getChildren().add(borderPane);

		Scene scene = new Scene(root, Color.WHITE);
		window.setTitle("Line Chart Sample");

		window.setScene(scene);
		window.show();

	}

	@SuppressWarnings("unchecked")
	private void setCondTab(Tab CondTab, String name, ArrayList<dataPoint> data, ArrayList<Flag> flagList) {

		final NumberAxis xAxis = new NumberAxis();
		final NumberAxis yAxis = new NumberAxis();
		xAxis.setLabel("Time");
		yAxis.setLabel("Conductivity");
		// creating the chart
		final AreaChart<Number, Number> lineChart = new AreaChart<Number, Number>(xAxis, yAxis);
		lineChart.setTitle("Conductivity - " + name);
		// defining a series
		XYChart.Series<Number, Number> cpf = new XYChart.Series<Number, Number>();
		XYChart.Series<Number, Number> caustic = new XYChart.Series<Number, Number>();
		XYChart.Series<Number, Number> rinse = new XYChart.Series<Number, Number>();
		XYChart.Series<Number, Number> acid = new XYChart.Series<Number, Number>();
		XYChart.Series<Number, Number> rinse2 = new XYChart.Series<Number, Number>();
		Rectangle rec = new Rectangle(200,200);
		
	
		
		cpf.setName("Pre Rinse");
		caustic.setName("Caustic");
		rinse.setName("Rinse");
		acid.setName("Acid");
		rinse2.setName("Final Rinse");

		// populating the series with data

		int count = 0;

		for (dataPoint d : data) {
			if (d.getZone() == 1) {
				cpf.getData().add(new XYChart.Data<Number, Number>(count, d.getConductivity()));
				count++;
			}
			if (d.getZone() == 2) {
				caustic.getData().add(new XYChart.Data<Number, Number>(count, d.getConductivity()));
				count++;
			}
			if (d.getZone() == 3) {
				rinse.getData().add(new XYChart.Data<Number, Number>(count, d.getConductivity()));
				count++;
			}
			if (d.getZone() == 4) {
				acid.getData().add(new XYChart.Data<Number, Number>(count, d.getConductivity()));
				count++;
			}
			if (d.getZone() == 5) {
				rinse2.getData().add(new XYChart.Data<Number, Number>(count, d.getConductivity()));
				count++;
			}
		}

		lineChart.getData().addAll(cpf, caustic, rinse, acid, rinse2);
		CondTab.setText("Conductivity");
		CondTab.setContent(lineChart);
	}

	@SuppressWarnings("unchecked")
	private void setTurbTab(Tab TurbTab, String name, ArrayList<dataPoint> data, ArrayList<Flag> flagList) {
		final NumberAxis xAxis = new NumberAxis();
		final NumberAxis yAxis = new NumberAxis();
		xAxis.setLabel("Time");
		yAxis.setLabel("Turbidity");
		// creating the chart
		final AreaChart<Number, Number> lineChart = new AreaChart<Number, Number>(xAxis, yAxis);
		lineChart.setTitle("Turbidity - " + name);
		// defining a series
		XYChart.Series<Number, Number> cpf = new XYChart.Series<Number, Number>();
		XYChart.Series<Number, Number> caustic = new XYChart.Series<Number, Number>();
		XYChart.Series<Number, Number> rinse = new XYChart.Series<Number, Number>();
		XYChart.Series<Number, Number> acid = new XYChart.Series<Number, Number>();
		XYChart.Series<Number, Number> rinse2 = new XYChart.Series<Number, Number>();
		cpf.setName("Pre Rinse");
		caustic.setName("Caustic");
		rinse.setName("Rinse");
		acid.setName("Acid");
		rinse2.setName("Final Rinse");

		// populating the series with data

		int count = 0;
		for (dataPoint d : data) {
			if (d.getZone() == 1) {
				cpf.getData().add(new XYChart.Data<Number, Number>(count, d.getTurbidity()));
				count++;
			}
			if (d.getZone() == 2) {
				caustic.getData().add(new XYChart.Data<Number, Number>(count, d.getTurbidity()));
				count++;
			}
			if (d.getZone() == 3) {
				rinse.getData().add(new XYChart.Data<Number, Number>(count, d.getTurbidity()));
				count++;
			}
			if (d.getZone() == 4) {
				acid.getData().add(new XYChart.Data<Number, Number>(count, d.getTurbidity()));
				count++;
			}
			if (d.getZone() == 5) {
				rinse2.getData().add(new XYChart.Data<Number, Number>(count, d.getTurbidity()));
				count++;
			}
		}
		lineChart.getData().addAll(cpf, caustic, rinse, acid, rinse2);
		TurbTab.setText("Turbidity");
		TurbTab.setContent(lineChart);
	}

	@SuppressWarnings("unchecked")
	private void setTempTab(Tab TempTab, String name, ArrayList<dataPoint> data, ArrayList<Flag> flagList) {

		final NumberAxis xAxis = new NumberAxis();
		final NumberAxis yAxis = new NumberAxis();
		xAxis.setLabel("Time");
		yAxis.setLabel("Temperature");
		// creating the chart
		final AreaChart<Number, Number> lineChart = new AreaChart<Number, Number>(xAxis, yAxis);
		lineChart.setTitle("Temperature - " + name);
		// defining a series
		XYChart.Series<Number, Number> cpf = new XYChart.Series<Number, Number>();
		XYChart.Series<Number, Number> caustic = new XYChart.Series<Number, Number>();
		XYChart.Series<Number, Number> rinse = new XYChart.Series<Number, Number>();
		XYChart.Series<Number, Number> acid = new XYChart.Series<Number, Number>();
		XYChart.Series<Number, Number> rinse2 = new XYChart.Series<Number, Number>();
		XYChart.Series<Number, Number> flags = new XYChart.Series<Number, Number>();

		cpf.setName("Pre Rinse");
		caustic.setName("Caustic");
		rinse.setName("Rinse");
		acid.setName("Acid");
		rinse2.setName("Final Rinse");

		// populating the series with data

		int count = 0;
		for (dataPoint d : data) {
			if (d.getZone() == 1) {
				cpf.getData().add(new XYChart.Data<Number, Number>(count, d.getTemp()));
				
			
				for (Flag f : flagList) {
					if (f.getType().equals("Temperature") && (f.getStartTime().equals(d.getTime()) || f.getEndTime().equals(d.getTime()))) {
						flags.getData().add(new XYChart.Data<Number, Number>(count, 70, Color.BLACK));
					}
				}
				
				count++;
			}
			if (d.getZone() == 2) {
				caustic.getData().add(new XYChart.Data<Number, Number>(count, d.getTemp()));
				
				for (Flag f : flagList) {
					if (f.getType().equals("Temperature") && (f.getStartTime().equals(d.getTime()) || f.getEndTime().equals(d.getTime()))) {
						flags.getData().add(new XYChart.Data<Number, Number>(count, 70));
					}
				}
			
				count++;
			}
			if (d.getZone() == 3) {
				rinse.getData().add(new XYChart.Data<Number, Number>(count, d.getTemp()));
				
				for (Flag f : flagList) {
					if (f.getType().equals("Temperature") && (f.getStartTime().equals(d.getTime()) || f.getEndTime().equals(d.getTime()))) {
						flags.getData().add(new XYChart.Data<Number, Number>(count, 70));
					}
				}
				count++;
				
			}
			if (d.getZone() == 4) {
				acid.getData().add(new XYChart.Data<Number, Number>(count, d.getTemp()));
			/*
				for (Flag f : flagList) {
					if (f.getType().equals("Temperature") && (f.getStartTime().equals(d.getTime()) || f.getEndTime().equals(d.getTime()))) {
						flags.getData().add(new XYChart.Data<Number, Number>(count, 70));
					}
				}
			*/
				count++;
			}
			if (d.getZone() == 5) {
				rinse2.getData().add(new XYChart.Data<Number, Number>(count, d.getTemp()));
			
				for (Flag f : flagList) {
					if (f.getType().equals("Temperature") && (f.getStartTime().equals(d.getTime()) || f.getEndTime().equals(d.getTime()))) {
						flags.getData().add(new XYChart.Data<Number, Number>(count, 70));
					}
				}
				
				count++;
			}
		}
		
		lineChart.getData().addAll(cpf, caustic, rinse, acid, rinse2, flags);
		TempTab.setText("Temperature");
		TempTab.setContent(lineChart);
	}

}
