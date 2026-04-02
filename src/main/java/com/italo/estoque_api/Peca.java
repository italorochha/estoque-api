package com.italo.estoque_api;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Peca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "O nome da peça não pode ser vazio!")
    private String nome;
    @NotBlank(message = "O código SKU é obrigatório!")
    private String codigoSku;
    @Min(value = 0, message = "A quantidade inicial não pode ser menor que zero!")
    private int quantidade;

    @ManyToOne
    @JoinColumn(name = "fornecedor_id")
    private Fornecedor fornecedor;

    public Peca() {}
    public Peca(String nome, String codigoSku, int quantidade, Fornecedor fornecedor) {
        this.nome = nome;
        this.codigoSku = codigoSku;
        this.quantidade = quantidade;
        this.fornecedor = fornecedor;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCodigoSku() { return codigoSku; }
    public void setCodigoSku(String codigoSku) { this.codigoSku = codigoSku; }
    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
    public Fornecedor getFornecedor() { return fornecedor; }
    public void setFornecedor(Fornecedor fornecedor) { this.fornecedor = fornecedor; }
}