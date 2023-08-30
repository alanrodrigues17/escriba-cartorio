package br.com.escriba.cartorio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.escriba.cartorio.domain.AtribuicoesCartorio;
import br.com.escriba.cartorio.dto.AtribuicoesDTO;
import br.com.escriba.cartorio.request.AtribuicoesRequest;
import br.com.escriba.cartorio.services.AtribuicoesService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("atribuicao")
public class AtribuicoesCartorioController {
	@Autowired
	private AtribuicoesService atribuicoesService;
	
	@GetMapping("/{id}")
	public ResponseEntity<AtribuicoesCartorio> buscarPorId(@Valid @PathVariable int id) {
		AtribuicoesCartorio response = atribuicoesService.buscarPorId(id);
		return ResponseEntity.ok().body(response);
	}
	
	@GetMapping()
	public List<AtribuicoesDTO> buscarTodos(@Valid @PageableDefault(value = 10) Pageable pageable) {
		Page<AtribuicoesDTO> response = atribuicoesService.buscarTodos(pageable);
		return response.getContent();
	}
	
	@PostMapping
	public ResponseEntity<HttpStatus> incluir(@RequestBody AtribuicoesRequest atribuicoesRequest) {
		atribuicoesService.salvarAtribuicao(atribuicoesRequest);
		return ResponseEntity.ok().body(HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<HttpStatus> atualizar(@Valid @PathVariable int id,
			@RequestBody AtribuicoesRequest atribuicoesRequest) throws Exception {
		atribuicoesService.atualizarAtribuicoes(id, atribuicoesRequest);
		return ResponseEntity.ok().body(HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteById(@Valid @PathVariable int id) {
		atribuicoesService.excluirAtribuicao(id);
		return ResponseEntity.ok().body(HttpStatus.OK);
	}

}
