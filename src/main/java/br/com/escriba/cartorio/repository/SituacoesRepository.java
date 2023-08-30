package br.com.escriba.cartorio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.escriba.cartorio.domain.SituacoesCartorio;

public interface SituacoesRepository extends JpaRepository<SituacoesCartorio, Integer>{

}
