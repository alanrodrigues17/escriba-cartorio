package br.com.escriba.cartorio.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.escriba.cartorio.repository.SituacoesRepository;
import br.com.escriba.cartorio.domain.SituacoesCartorio;
import br.com.escriba.cartorio.dto.SituacoesDTO;
import jakarta.validation.ConstraintViolationException;

import br.com.escriba.cartorio.request.SituacoesRequest;
import br.com.escriba.cartorio.resources.exception.types.ObjectNotFoundException;

@Service
public class SituacoesService {
	@Autowired
	private SituacoesRepository situacoesRepository;

	public SituacoesCartorio buscarPorId(Integer id) {
		Optional<SituacoesCartorio> obj = situacoesRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id));
	}

	public void salvarSituacao(SituacoesRequest situacoesRequest) {

		SituacoesCartorio situacoesCartorio = SituacoesCartorio.builder().nome(situacoesRequest.getNome()).build();

		situacoesRepository.save(situacoesCartorio);
	}

	public void atualizarSituacao(int id, SituacoesRequest situacoesRequest) {
		SituacoesCartorio situacoesCartorio = buscarPorId(id);

		situacoesCartorio.setNome(situacoesRequest.getNome());
		situacoesRepository.save(situacoesCartorio);
	}
	

	public Page<SituacoesDTO> buscarTodos(Pageable pageable) {
		Page<SituacoesCartorio> page = situacoesRepository.findAll(pageable);

		Page<SituacoesDTO> page2 = page
				.map(entity -> new SituacoesDTO(entity.getId(), entity.getNome()));

		return page2;
	}
	
	
	public void excluirSituacao(int id) {

		try {
			situacoesRepository.deleteById(id);
		} catch (ConstraintViolationException e) {
			throw new ConstraintViolationException(null);
		}
	}

}
