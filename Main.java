import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		File inputDir = new File(args[0] + File.separator);
		File outputDir = new File(Paths.get(".").toAbsolutePath().normalize().toString() + File.separator + "output" + File.separator);
		outputDir.mkdir();
		
		// Find every xml file, parse and annotate corresponding image appropriately
		// We assume there is a matching *.png to every *.xml
		for (File file : inputDir.listFiles()) {
			if (file.getName().endsWith(".xml")) {
				String name = file.getName();
				name = name.substring(0, name.length() - 4);
				File xmlFile = new File(inputDir.toString() + File.separator + name + ".xml");
				XMLParser parser = new XMLParser(xmlFile);
				if (parser.isValidXML()) {
					List<int[]> boundaries = parser.getBoundaries();
					
					File imgFile = new File(inputDir.toString() + File.separator + name + ".png");
					ImageAnnotator img = new ImageAnnotator(imgFile);
					
					for (int[] bound : boundaries) {
						// System.out.println("Bounds: " + bound[0] + ", " + bound[1] + ", "+ bound[2] + ", "+ bound[3]);
						img.outlineRegion(bound[0], bound[1], bound[2], bound[3]);
					}
					
					File outputFile = new File(outputDir.toString() + File.separator + name + "-ANNOTATED.png");
					img.writeToNewFile(outputFile);
					System.out.println(outputFile.getName() + " created.");
				} else {
					System.out.println(xmlFile.getName() + " is not a valid XML document. Skipping to next file.");
				}
			}
		}
	}
}