package br.com.next.bo;

import java.util.Random;

public class PixBo {

	public String gerarCodigo(int id) {

		String cod = "0123456789QWERTYUIOPLKJHGFDSAZXCVBNM";
		Random rng = new Random();

		char[] text = new char[36];
		for (int i = 0; i < 36; i++) {
			text[i] = cod.charAt(rng.nextInt(cod.length()));
		}
		String numF = digitosFinais(id);
		return (new String(text)+numF);

	}
	
	public String digitosFinais(int id) {
		String numFinal;
		
		if(id <= 9) {
			numFinal = "000"+id;
		}else if(id <= 99) {
			numFinal = "00"+id;
		}else if(id <= 999){
			numFinal = "0"+id;
		}else {
			numFinal = String.valueOf(id);
		}
		
		return numFinal;
	}
}
