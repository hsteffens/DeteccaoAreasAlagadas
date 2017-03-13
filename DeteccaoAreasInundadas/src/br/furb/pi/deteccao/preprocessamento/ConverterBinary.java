package br.furb.pi.deteccao.preprocessamento;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;


public class ConverterBinary {

	public static Mat converter(Mat img, double limiar){
		Mat destination = new Mat(img.rows(),img.cols(),img.type());
		Imgproc.threshold(img,destination, limiar, 255, 0);
		
		return destination;
		
		
	}
}
