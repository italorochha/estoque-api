package com.italo.estoque_api;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PecaRepository extends JpaRepository<Peca, Long> {
    List<Peca> findByFornecedorId(Long fornecedorId);
    List<Peca> findByQuantidadeLessThanEqual(int quantidadeCritica);
}