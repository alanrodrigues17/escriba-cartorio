package br.com.escriba.cartorio.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SituacoesRequest {
	
	private String id;
	
	@NotNull(message = "O nome é obrigatório")
	@Size(max = 50, message = "O nome é no máximo 50 caracteres")
	private String nome;
	
	@NotNull(message = "A situação é obrigatória")
	private String situacao;

}
