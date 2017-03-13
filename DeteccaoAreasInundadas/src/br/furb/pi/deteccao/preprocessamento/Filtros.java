package br.furb.pi.deteccao.preprocessamento;

import org.opencv.core.Mat;

public class Filtros {

	public void negativo(Mat imagem){
		int rows = imagem.rows();
		int cols = imagem.cols();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				double[] rgb = imagem.get(i, j);
				imagem.put(i, j, 255 - rgb[0], 255 - rgb[1], 255- rgb[2]);
			}
		}
		
	}
}
