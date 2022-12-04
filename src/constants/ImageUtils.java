package constants;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

//import org.jdesktop.swingx.util.GraphicsUtilities;


public final class ImageUtils {
	private ImageUtils() {
	}
	
	public static Image readImageFromFile(File file) {
		Image image = null;
		
		try {
			image = ImageIO.read(file);
		}
		catch (Exception e) {
		}
		
		return image;
	}
	
	public static ImageIcon readImageIconFromFile(File file) {
		Image image = readImageFromFile(file);
		return new ImageIcon(image);
	}
	
	public static void saveImageIconToFile(ImageIcon ic, String fileName) {
		saveImageIconToFile(ic, new File(fileName));
	}
	
	public static void saveImageIconToFile(ImageIcon ic, File file) {
		try {
			ImageIO.write( toBufferedImage(ic) , "jpg", file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static BufferedImage toBufferedImage(Image image) {
		if (image instanceof BufferedImage) {
			return ((BufferedImage) image);
		} else {
			image = new ImageIcon(image).getImage();
			BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
			Graphics g = bufferedImage.createGraphics();
			g.drawImage(image, 0, 0, null);
			g.dispose();
			
			return (bufferedImage);
		}
	}
	
	public static BufferedImage toBufferedImage(ImageIcon imageIcon) {
		return toBufferedImage(imageIcon.getImage());
	}
	
	public static ImageIcon resizeIconTo(ImageIcon icon, int newWidth, int newHeight) {
		Image image = icon.getImage();
		if (newWidth == 0 || newHeight == 0){
			return icon;
		}
		return new ImageIcon(image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH));
	}

	public static ImageIcon resizeIconToWidth(ImageIcon icon, int newWidth,
			boolean keepProportions) {
		int width = icon.getIconWidth();
		int height = icon.getIconHeight();

		if (keepProportions) {
			height = (int) ((double) height * (double) newWidth / (double) width);
		}

		return resizeIconTo(icon, newWidth, height);
	}

	public static ImageIcon resizeIconToHeight(ImageIcon icon, int newHeight, boolean keepProportions) {
		int width = icon.getIconWidth();
		int height = icon.getIconHeight();

		if (keepProportions) {
			width = (int) ((double) width * (double) newHeight / (double) height);
		}

		return resizeIconTo(icon, width, newHeight);
	}
	
	public static ImageIcon resizeIconToOptimized(ImageIcon icon, int newWidth, int newHeight){
		int width = icon.getIconWidth();
		int height = icon.getIconHeight();
		
		if (width >= height){
			return resizeIconToHeight(icon, newHeight, true);
		}
		else{
			return resizeIconToWidth(icon, newWidth, true);
		}
	}
}
