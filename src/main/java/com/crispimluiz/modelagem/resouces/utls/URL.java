package com.crispimluiz.modelagem.resouces.utls;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {

	// Quando aparece espaços em branco quando o cliente digita o nome
	public static String decodeParam(String s) {
		try {
			return URLDecoder.decode(s, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

	// Transformar o String que vem da tela em Integer
	public static List<Integer> decodeIntList(String s) {
		String[] vet = s.split(",");
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < vet.length; i++) {
			list.add(Integer.parseInt(vet[i]));
		}
		return list;
		// Esse array faz a mesma coisa do de cima
		// return Arrays.asList(s.split(",")).stream().map(x ->
		// Integer.parseInt(x)).collect(Collectors.toList());
	}
}
