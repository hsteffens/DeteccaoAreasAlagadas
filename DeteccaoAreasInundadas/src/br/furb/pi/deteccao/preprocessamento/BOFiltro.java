package br.furb.pi.deteccao.preprocessamento;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class BOFiltro {

	public static Mat negativo(Mat imagem){
		int rows = imagem.rows();
		int cols = imagem.cols();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				double[] rgb = imagem.get(i, j);
				imagem.put(i, j, 255 - rgb[0], 255 - rgb[1], 255- rgb[2]);
			}
		}
		
		return imagem;
	}
	
	public static Mat tornarEmTonsDeCinza(Mat imgOrigem){
		Mat imgFinal = new Mat(imgOrigem.rows(), imgOrigem.cols(), CvType.CV_8UC1);
		Imgproc.cvtColor(imgOrigem, imgFinal, Imgproc.COLOR_BGR2GRAY);
		
		return imgFinal;
	}
	
	public static Mat aplicarAbertura(Mat imagem, double limiar){
		Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT,new Size(2,2.5));
		
		Mat destination = new Mat(imagem.rows(),imagem.cols(),imagem.type());
		Imgproc.threshold(imagem,destination, limiar, 255, 0);
		
		Mat dst = new Mat(imagem.rows(), imagem.cols(), imagem.type());
		Imgproc.morphologyEx(imagem, dst, Imgproc.MORPH_OPEN, kernel);
		
		return imagem;
	}
	
	public static Mat subtracao(Mat img1, Mat img2){
		Mat destination = new Mat(img1.rows(),img1.cols(),img1.type());
		Core.subtract(img2,img1,destination);
		
		return destination;
	}
	
	public static Mat adicao(Mat img1, Mat img2, int limiar){
		Mat destination = new Mat(img1.rows(),img1.cols(),img1.type());
		Mat img = new Mat(img1.rows(),img1.cols(),img1.type());
		for (int i = 0; i < img1.rows(); i++) {
			for (int j = 0; j < img1.cols(); j++) {
				final double[] rgb2 = img2.get(i, j);
				
				img.put(i, j, rgb2[0] + limiar);
			}
		}
		
		Core.add(img1, img, destination);
		
		return destination;
	}
	
	public static Mat sobreposicao(Mat imagemSobreposta, Mat imagemHaSobrepor){
		final Mat mat = new Mat();
		Core.addWeighted(imagemSobreposta, 1.0, imagemHaSobrepor, 2.0,0, mat);
		
		return mat;
	}
}
