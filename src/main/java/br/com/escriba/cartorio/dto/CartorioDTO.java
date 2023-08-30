package br.com.escriba.cartorio.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartorioDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int id;
	private String nome;
}
