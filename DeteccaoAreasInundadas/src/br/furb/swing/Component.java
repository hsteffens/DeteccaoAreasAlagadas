package br.furb.swing;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;

public class Component {

	public static void openJFrame(Mat imagem){
		  MatOfByte matOfByte = new MatOfByte();
		    Imgcodecs.imencode(".jpg", imagem, matOfByte);
		    
		    byte[] byteArray = matOfByte.toArray();
		    BufferedImage bufImage = null;
		    try {
		        InputStream in = new ByteArrayInputStream(byteArray);
		        bufImage = ImageIO.read(in);
		        JFrame frame = new JFrame();
		        frame.getContentPane().add(new JLabel(new ImageIcon(bufImage)));
		        frame.pack();
		        frame.setVisible(true);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
	}
}
