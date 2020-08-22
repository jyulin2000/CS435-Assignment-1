import java.io.File;

public class Main {
	public static void main(String[] args) {
		File inputDir = new File(args[0]);
		
		// Find every xml file, parse and annotate corresgonding image appropriately
		// We assume there is a matching *.png to every *.xml
		for (File file : inputDir.listFiles()) {
			if (file.getName().endsWith(".xml")) {
				String name = file.getName();
				
				System.out.println(name.substring(0, name.length() - 4));
			}
		}
	}
}