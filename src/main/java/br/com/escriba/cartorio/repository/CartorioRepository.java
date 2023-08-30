package br.com.escriba.cartorio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.escriba.cartorio.domain.Cartorio;

@Repository
public interface CartorioRepository extends JpaRepository<Cartorio, Integer> {
	@Query("from Cartorio where nome = :nome")
	List<Cartorio> findByName(@Param("nome") String nome_cartorio);
}
