package br.furb.pi.deteccao;

import java.io.IOException;

import org.opencv.core.Core;
import org.opencv.core.Mat;

import br.furb.pi.deteccao.preprocessamento.BOFiltro;
import br.furb.pi.deteccao.preprocessamento.ConverterBinary;
import br.furb.swing.Component;

public class Main {

	public static void main(String[] args) throws IOException {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		/*Load images*/
		Imagem cheia = new Imagem("img/cheia.jpg");
		Imagem baixa = new Imagem("img/baixa.jpg");
		
		/*Pré-processamento*/
		final Mat cheiaNegativo = BOFiltro.negativo(cheia.getMat());
		final Mat baixaNegativo = BOFiltro.negativo(baixa.getMat());
		
		final Mat cheiaTonsDeCinza = BOFiltro.tornarEmTonsDeCinza(cheiaNegativo);
		final Mat baixaTonsDeCinza = BOFiltro.tornarEmTonsDeCinza(baixaNegativo);
		
		/*Pré-processamento morfológico*/
		final Mat cheiaRealcada = BOFiltro.adicao(cheiaTonsDeCinza,cheiaTonsDeCinza, 50);
		final Mat baixaRealcada = BOFiltro.adicao(baixaTonsDeCinza,baixaTonsDeCinza, 70);
		
		final Mat cheiaBinarizada = ConverterBinary.converter(cheiaRealcada, 170);
		final Mat baixaBinarizada = ConverterBinary.converter(baixaRealcada, 210);

		/*Pós-processamento morfológico*/
		final Mat cheiaAbertura = BOFiltro.aplicarAbertura(cheiaBinarizada, 150);
		final Mat baixaAbertura = BOFiltro.aplicarAbertura(baixaBinarizada, 130);
		
		/*Extração das Áreas Inundadas*/
		final Mat imageSubtraida = BOFiltro.subtracao(baixaAbertura, cheiaAbertura);
		
		final Mat sobreposicao = BOFiltro.sobreposicao(baixaTonsDeCinza, imageSubtraida);
		
		Component.openJFrame(sobreposicao);
	}

}
