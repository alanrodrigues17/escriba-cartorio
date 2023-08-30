package br.com.escriba.cartorio.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Cartorio {
	
	@Id
	@Column(nullable = false)
	@SequenceGenerator(name = "SeqA", sequenceName = "seq_tipo_a")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SeqA")
	private int id;
	
	@Column(length = 150, nullable = false)
	private String nome;
	
	@Column(length = 200)
	private String observacao;
	 
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(nullable = false)
	private AtribuicoesCartorio atribuicoesCartorio;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(nullable = false)
	private SituacoesCartorio situacoesCartorio;
}
