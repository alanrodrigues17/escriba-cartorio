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

import br.com.escriba.cartorio.services.SituacoesService;
import br.com.escriba.cartorio.domain.SituacoesCartorio;
import br.com.escriba.cartorio.dto.SituacoesDTO;
import br.com.escriba.cartorio.request.SituacoesRequest;
import jakarta.validation.Valid;


@RestController
@RequestMapping("situacao")
public class SituacoesCartorioController {
	@Autowired
	private SituacoesService situacaoService;
	
	@GetMapping("/{id}")
	public ResponseEntity<SituacoesCartorio> buscarPorId(@Valid @PathVariable int id) {
		SituacoesCartorio response = situacaoService.buscarPorId(id);
		return ResponseEntity.ok().body(response);
	}
	
	@GetMapping()
	public List<SituacoesDTO> buscarTodos(@Valid @PageableDefault(value = 10) Pageable pageable) {
		Page<SituacoesDTO> response = situacaoService.buscarTodos(pageable);
		return response.getContent();
	}

	@PostMapping
	public ResponseEntity<HttpStatus> incluir(@RequestBody SituacoesRequest situacoesRequest) {
		situacaoService.salvarSituacao(situacoesRequest);
		return ResponseEntity.ok().body(HttpStatus.CREATED);
	}

	@PutMapping("/atualizar-situacao/{id}")
	public ResponseEntity<HttpStatus> atualizar(@Valid @PathVariable int id,
			@RequestBody SituacoesRequest situacoesRequest) throws Exception {
		situacaoService.atualizarSituacao(id, situacoesRequest);
		return ResponseEntity.ok().body(HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteById(@Valid @PathVariable int id) {
		situacaoService.excluirSituacao(id);
		return ResponseEntity.ok().body(HttpStatus.OK);
	}

	
}
