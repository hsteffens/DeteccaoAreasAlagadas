package br.furb.pi.deteccao.preprocessamento;

import org.opencv.core.Core;
import org.opencv.core.Mat;

import br.furb.pi.deteccao.Imagem;

public class Processamento {

	public static Imagem subtracao(Mat img1, Mat img2){
		Mat destination = new Mat(img1.rows(),img1.cols(),img1.type());
		
		Core.subtract(img2,img1,destination);
		
		return new Imagem(destination);
	}
	
	public static Imagem adicao(Mat img1, Mat img2, int limiar){
		Mat destination = new Mat(img1.rows(),img1.cols(),img1.type());
		for (int i = 0; i < img1.rows(); i++) {
			for (int j = 0; j < img1.cols(); j++) {
				final double[] rgb2 = img2.get(i, j);
				
				img2.put(i, j, rgb2[0] + limiar);
				
			}
		}
		
		Core.add(img1, img2, destination);
		
		return new Imagem(destination);
	}
}

