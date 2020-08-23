import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.Math;
import javax.imageio.ImageIO;

public class ImageAnnotator {
	BufferedImage img;
	int width;
	int height;
	int outlineColor;
	int lineWidth;
	
	public ImageAnnotator(File imageFile) {
		try {
			img = ImageIO.read(imageFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		width = img.getWidth();
		height = img.getHeight();
		
		lineWidth = 4;
		
		outlineColor = new Color(255, 255, 0).getRGB(); // Yellow
	}
	
	// (x1, y1) is top-left corner of rectangular region, (x2, y2) is bottom-right
	public void outlineRegion(int x1, int y1, int x2, int y2) {
		if (x2 >= width) x2 = width - 1;
		if (y2 >= height) y2 = height - 1;
		for (int x = x1; x < x2; x++) {
			for (int n = Math.max(0, y1-lineWidth); n <= Math.min(height-1, y1+lineWidth); n++) {
				img.setRGB(x, n, outlineColor);
			}
			for (int n = Math.max(0, y2-lineWidth); n <= Math.min(height-1, y2+lineWidth); n++) {
				img.setRGB(x, n, outlineColor);
			}
		}
		
		for (int y = y1; y < y2; y++) {
			for (int n = Math.max(0, x1-lineWidth); n <= Math.min(width-1, x1+lineWidth); n++) {
				img.setRGB(n, y, outlineColor);
			}
			for (int n = Math.max(0, x2-lineWidth); n <= Math.min(width-1, x2+lineWidth); n++) {
				img.setRGB(n, y, outlineColor);
			}
		}
	}
	
	public void writeToNewFile(File outputFile) {
		try {
			ImageIO.write(img, "png", outputFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
