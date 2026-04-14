package com.italo.estoque_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.italo.estoque_api.model.Peca;

@Repository
public interface PecaRepository extends JpaRepository<Peca, Long> {
    List<Peca> findByFornecedorId(Long fornecedorId);
    List<Peca> findByQuantidadeLessThanEqual(int quantidadeCritica);
}