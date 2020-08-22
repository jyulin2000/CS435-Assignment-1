import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageAnnotator {
	BufferedImage img;
	
	public ImageAnnotator(File imageFile) throws IOException {
		img = ImageIO.read(imageFile);
	}
	
	// (x1, y1) is top-left corner of rectangular region, (x2, y2) is bottom-right
	public void highlightRegion(int x1, int y1, int x2, int y2) {
		
	}
}
