package com.italo.estoque_api.dto;

import com.italo.estoque_api.model.Peca;

public record PecaResponseDTO(
        Long id,
        String nome,
        String codigoSku,
        int quantidade,
        String nomeFornecedor
) {
    public PecaResponseDTO(Peca peca) {
        this(
                peca.getId(),
                peca.getNome(),
                peca.getCodigoSku(),
                peca.getQuantidade(),
                peca.getFornecedor() != null ? peca.getFornecedor().getRazaoSocial() : "Fornecedor não informado"
        );
    }
}