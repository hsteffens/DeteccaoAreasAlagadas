package br.furb.pi.deteccao;

import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

public class Imagem {

	private Mat mat;
	
	public Imagem(Mat mat) {
		this.mat = mat;
	}

	public Imagem(String enderecoImagem) {
		Mat imagem = Imgcodecs.imread(enderecoImagem);
		this.mat = imagem;
	}

	public void aplicarNegativo(Mat imagem){
		int rows = imagem.rows();
		int cols = imagem.cols();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				double[] rgb = imagem.get(i, j);
				imagem.put(i, j, 255 - rgb[0], 255 - rgb[1], 255- rgb[2]);
			}
		}
		
	}

	public Mat getMat() {
		return mat;
	}

	public void setMat(Mat imagem) {
		this.mat = imagem;
	}


}
