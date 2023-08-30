package br.com.escriba.cartorio.request;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CartorioRequest {
	
	private int id;
	
	@NotNull(message = "O nome é obrigatório")
	@Size(max = 150, message = "O nome é no máximo 50 caracteres")
	private String nome;
	
	@Size(max = 250, message = "testetse")
	private String observacao;
	
	@NotNull(message = "A situação é obrigatória")
	private SituacoesRequest situacoesRequest;
	
	@NotNull(message = "Atribuição é obrigatória")
	private AtribuicoesRequest atribuicoesRequest;
}
