package br.com.escriba.cartorio.controller;

import java.util.List;
import java.util.Optional;

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
import br.com.escriba.cartorio.domain.Cartorio;
import br.com.escriba.cartorio.dto.CartorioDTO;
import br.com.escriba.cartorio.request.CartorioRequest;
import br.com.escriba.cartorio.services.CartorioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("cartorio")
public class CartorioController {
	@Autowired
	private CartorioService cartorioService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Cartorio> buscarPorId(@Valid @PathVariable int id) {
		Cartorio response = cartorioService.buscarPorId(id);
		return ResponseEntity.ok().body(response);
	}
	
	@GetMapping()
	public List<CartorioDTO> buscarTodos(@Valid @PageableDefault(value = 10) Pageable pageable) {
		Page<CartorioDTO> response = cartorioService.buscarTodos(pageable);
		return response.getContent();
	}
	
	@PostMapping
	public ResponseEntity<HttpStatus> incluir(@Valid @RequestBody CartorioRequest cartorioRequest) throws Exception {
		cartorioService.salvarCartorio(cartorioRequest);
		return ResponseEntity.ok().body(HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<HttpStatus> atualizar(@Valid @PathVariable int id,
			@RequestBody CartorioRequest cartorioRequest) throws Exception {
		cartorioService.atualizarCartorio(id, cartorioRequest);
		return ResponseEntity.ok().body(HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteById(@Valid @PathVariable int id) {
		cartorioService.deleteById(id);
		return ResponseEntity.ok().body(HttpStatus.OK);
	}

}
