package Main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.AreaChart;
import javafx.scene.image.WritableImage;
import javafx.stage.DirectoryChooser;

public class ExportData {

	public void exportData(ArrayList<AreaChart<Number,Number>> graphList, String name){
		
			for(AreaChart<Number,Number> a : graphList){
				
				WritableImage image = a.snapshot(new SnapshotParameters(), null);
				
				File file = new File("C:\\" + name + "\\ " + a.getTitle() + " graph.png");
				
				 try {
				        ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
				    } catch (IOException e) {
				        // TODO: handle exception here
				    }
				
			}	
	}
}
