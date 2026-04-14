package com.italo.estoque_api.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.italo.estoque_api.model.Movimentacao;
import com.italo.estoque_api.model.Peca;
import com.italo.estoque_api.repository.MovimentacaoRepository;
import com.italo.estoque_api.repository.PecaRepository;

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
    public List<Peca> buscarPecasEmAlerta(int limite) {
        return pecaRepo.findByQuantidadeLessThanEqual(limite);
    }

    public String gerarRelatorioCsv() {
        List<Peca> todasPecas = pecaRepo.findAll();
        StringBuilder csv = new StringBuilder();

        csv.append("ID;Nome;Código SKU;Quantidade;Fornecedor\n");

        for (Peca peca : todasPecas) {
            String nomeFornecedor = peca.getFornecedor() != null ? peca.getFornecedor().getRazaoSocial() : "Sem Fornecedor";
            csv.append(peca.getId()).append(";")
        .append(peca.getNome()).append(";")
        .append(peca.getCodigoSku()).append(";")
        .append(peca.getQuantidade()).append(";")
        .append(nomeFornecedor).append("\n");
        }
        return csv.toString();
    }
}
