package br.com.escriba.cartorio.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.escriba.cartorio.domain.AtribuicoesCartorio;
import br.com.escriba.cartorio.domain.SituacoesCartorio;
import br.com.escriba.cartorio.dto.AtribuicoesDTO;
import br.com.escriba.cartorio.dto.SituacoesDTO;
import br.com.escriba.cartorio.repository.AtribuicoesRepository;
import br.com.escriba.cartorio.request.AtribuicoesRequest;
import br.com.escriba.cartorio.request.SituacoesRequest;
import br.com.escriba.cartorio.resources.exception.types.ObjectNotFoundException;
import jakarta.validation.ConstraintViolationException;

@Service
public class AtribuicoesService {
	@Autowired
	private AtribuicoesRepository atribuicoesRepository;
	
	public AtribuicoesCartorio buscarPorId(Integer id) {
		Optional<AtribuicoesCartorio> obj = atribuicoesRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + AtribuicoesCartorio.class.getName()));
	}
	
	public void salvarAtribuicao(AtribuicoesRequest atribuicoesRequest) {

		AtribuicoesCartorio atribuicoesCartorio = AtribuicoesCartorio.builder().nome(atribuicoesRequest.getNome())
				.situacao(atribuicoesRequest.getSituacao()).build();

		atribuicoesRepository.save(atribuicoesCartorio);
	}
	
	public void atualizarAtribuicoes(int id, AtribuicoesRequest atribuicoesRequest) {
		AtribuicoesCartorio atribuicoesCartorio = buscarPorId(id);

		atribuicoesCartorio.setNome(atribuicoesRequest.getNome());
		atribuicoesCartorio.setSituacao(atribuicoesRequest.getSituacao());

		atribuicoesRepository.save(atribuicoesCartorio);
	}
	
	public Page<AtribuicoesDTO> buscarTodos(Pageable pageable) {
		Page<AtribuicoesCartorio> page = atribuicoesRepository.findAll(pageable);

		Page<AtribuicoesDTO> page2 = page
				.map(entity -> new AtribuicoesDTO(entity.getId(), entity.getNome()));

		return page2;
	}
	
	
	public void excluirAtribuicao(int id) {

		try {
			atribuicoesRepository.deleteById(id);
		} catch (ConstraintViolationException e) {
			throw new ConstraintViolationException(null);
		}
	}
}
