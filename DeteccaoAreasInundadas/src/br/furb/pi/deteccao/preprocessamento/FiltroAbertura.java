package br.furb.pi.deteccao.preprocessamento;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import br.furb.pi.deteccao.Imagem;

public class FiltroAbertura {

	public static Imagem removerRuido(Imagem imagem, double limiar){
		Mat mat = imagem.getMat();
		Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT,new Size(2,2.5));
		
		Mat destination = new Mat(mat.rows(),mat.cols(),mat.type());
		Imgproc.threshold(mat,destination, limiar, 255, 0);
		
		Mat dst = new Mat(mat.rows(), mat.cols(), mat.type());
		Imgproc.morphologyEx(mat, dst, Imgproc.MORPH_OPEN, kernel);
		
		imagem.setMat(dst);

		return imagem;
	}
}
