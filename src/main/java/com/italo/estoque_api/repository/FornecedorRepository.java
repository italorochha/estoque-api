package com.italo.estoque_api.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.italo.estoque_api.model.Fornecedor;
@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
}