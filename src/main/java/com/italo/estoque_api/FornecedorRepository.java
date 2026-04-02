package com.italo.estoque_api;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
}