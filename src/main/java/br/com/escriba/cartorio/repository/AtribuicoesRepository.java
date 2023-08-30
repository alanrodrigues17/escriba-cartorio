package br.com.escriba.cartorio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.escriba.cartorio.domain.AtribuicoesCartorio;

@Repository
public interface AtribuicoesRepository extends JpaRepository<AtribuicoesCartorio, Integer> {

}
