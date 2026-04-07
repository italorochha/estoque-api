package com.italo.estoque_api;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; // 🌟 Necessário para capturar a hora atual

@Service
public class PecaService {

    @Autowired
    private PecaRepository pecaRepo;
    @Autowired
    private MovimentacaoRepository movimentacaoRepo;

    public Peca darEntradaEstoque(Long id, int quantidadeChegou) {
        Peca pecaNoBanco = pecaRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Peça não encontrada no sistema!"));

        int novoTotal = pecaNoBanco.getQuantidade() + quantidadeChegou;
        pecaNoBanco.setQuantidade(novoTotal);
        Peca pecaAtualizada = pecaRepo.save(pecaNoBanco);

        Movimentacao mov = new Movimentacao();
        mov.setDataHora(LocalDateTime.now());
        mov.setTipo("ENTRADA");
        mov.setQuantidade(quantidadeChegou);
        mov.setPeca(pecaAtualizada);
        movimentacaoRepo.save(mov);

        return pecaAtualizada;
    }

    public Peca registrarSaida(Long id, int quantidadeSaida) {
        Peca pecaNoBanco = pecaRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Peça não encontrada no sistema!"));

        if (quantidadeSaida > pecaNoBanco.getQuantidade()) {
            throw new RuntimeException("Estoque insuficiente! Temos apenas " + pecaNoBanco.getQuantidade() + " unidades da peça.");
        }

        int novoTotal = pecaNoBanco.getQuantidade() - quantidadeSaida;
        pecaNoBanco.setQuantidade(novoTotal);
        Peca pecaAtualizada = pecaRepo.save(pecaNoBanco);
        Movimentacao mov = new Movimentacao();
        mov.setDataHora(LocalDateTime.now());
        mov.setTipo("SAIDA");
        mov.setQuantidade(quantidadeSaida);
        mov.setPeca(pecaAtualizada);
        movimentacaoRepo.save(mov);

        return pecaAtualizada;
    }
}