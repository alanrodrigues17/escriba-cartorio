package br.com.escriba.cartorio.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AtribuicoesDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int id;
	private String nome; 

}
