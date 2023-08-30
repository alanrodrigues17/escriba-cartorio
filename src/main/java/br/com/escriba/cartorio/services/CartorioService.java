package br.com.escriba.cartorio.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.escriba.cartorio.domain.AtribuicoesCartorio;
import br.com.escriba.cartorio.domain.Cartorio;
import br.com.escriba.cartorio.domain.SituacoesCartorio;
import br.com.escriba.cartorio.dto.CartorioDTO;
import br.com.escriba.cartorio.dto.SituacoesDTO;
import br.com.escriba.cartorio.repository.CartorioRepository;
import br.com.escriba.cartorio.request.CartorioRequest;
import br.com.escriba.cartorio.resources.exception.types.ObjectNotFoundException;

@Service
public class CartorioService {
	@Autowired
	private CartorioRepository cartorioRepository;
	
	public Cartorio buscarPorId(int id) {
		Optional<Cartorio> obj = cartorioRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id));
	}
	
	public void salvarCartorio(CartorioRequest cartorioRequest) throws Exception {

		List<Cartorio> listaCartorio = cartorioRepository.findByName(cartorioRequest.getNome());

		if (listaCartorio.size() > 0) {
			throw new Error();
		}

		AtribuicoesCartorio atribuicoesCartorio = AtribuicoesCartorio.builder()
				.nome(cartorioRequest.getAtribuicoesRequest().getNome())
				.situacao(cartorioRequest.getAtribuicoesRequest().getSituacao()).build();

		SituacoesCartorio situacaoCartorio = SituacoesCartorio.builder()
				.nome(cartorioRequest.getSituacoesRequest().getNome()).build();

		Cartorio cartorio = Cartorio.builder().atribuicoesCartorio(atribuicoesCartorio)
				.situacoesCartorio(situacaoCartorio).nome(cartorioRequest.getNome())
				.observacao(cartorioRequest.getObservacao()).build();

		cartorioRepository.save(cartorio);
	}
	
	public Page<CartorioDTO> buscarTodos(Pageable pageable) {
		Page<Cartorio> page = cartorioRepository.findAll(pageable);
		

		Page<CartorioDTO> page2 = page
				.map(entity -> new CartorioDTO(entity.getId(), entity.getNome()));


		return page2;
	}

	
	public void atualizarCartorio(int id, CartorioRequest cartorioRequest) {
		Cartorio cartorio = buscarPorId(id);
		cartorio.getAtribuicoesCartorio().setNome(cartorioRequest.getAtribuicoesRequest().getNome());
		cartorio.getAtribuicoesCartorio().setSituacao(cartorioRequest.getAtribuicoesRequest().getSituacao());
		cartorio.getSituacoesCartorio().setNome(cartorioRequest.getSituacoesRequest().getNome());
		cartorio.setNome(cartorioRequest.getNome());
		cartorio.setObservacao(cartorioRequest.getObservacao());

		cartorioRepository.save(cartorio);
	}

	public void deleteById(int id) {
		cartorioRepository.deleteById(id);
	}
	
}
