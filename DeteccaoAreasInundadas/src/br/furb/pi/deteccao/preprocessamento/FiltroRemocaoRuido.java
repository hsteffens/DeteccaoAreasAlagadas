package br.furb.pi.deteccao.preprocessamento;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import br.furb.pi.deteccao.Imagem;

public class FiltroRemocaoRuido {

	public static Imagem removerRuido(Imagem imagem, double mascara){
		Mat mat = imagem.getMat();
		Mat kernel = new Mat();
		
		Mat dst = new Mat(mat.rows(), mat.cols(), mat.type());
		Imgproc.morphologyEx(mat, dst, Imgproc.MORPH_BLACKHAT, kernel);
		
		imagem.setMat(dst);

		return imagem;
	}
}
