package com.italo.estoque_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PecaService {
    @Autowired
    private PecaRepository pecaRepo;

    public Peca darEntradaEstoque(Long id, int quantidadeChegou) {
        Peca pecaNoBanco = pecaRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Peça não encontrada no sistema!"));

        int novoTotal = pecaNoBanco.getQuantidade() + quantidadeChegou;
        pecaNoBanco.setQuantidade(novoTotal);

        return pecaRepo.save(pecaNoBanco);
    }

    public Peca registrarSaida(Long id, int quantidadeSaida) {
        Peca pecaNoBanco = pecaRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Peça não encontrada no sistema!"));

        if (quantidadeSaida > pecaNoBanco.getQuantidade()) {
            throw new RuntimeException("Estoque insuficiente! Temos apenas " + pecaNoBanco.getQuantidade() + " unidades da peça.");
        }

        int novoTotal = pecaNoBanco.getQuantidade() - quantidadeSaida;
        pecaNoBanco.setQuantidade(novoTotal);

        return pecaRepo.save(pecaNoBanco);
    }
}