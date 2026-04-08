package com.italo.estoque_api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class EstoqueController {

    @Autowired
    private FornecedorRepository fornecedorRepo;
    @Autowired
    private PecaRepository pecaRepo;
    @Autowired
    private PecaService pecaService;
    @Autowired
    private MovimentacaoRepository movimentacaoRepo;
    @PostMapping("/fornecedores")
    public Fornecedor cadastrarFornecedor(@Valid @RequestBody Fornecedor novoFornecedor) {
        return fornecedorRepo.save(novoFornecedor);
    }

    @GetMapping("/fornecedores")
    public List<Fornecedor> listarFornecedores() {
        return fornecedorRepo.findAll();
    }
    @PostMapping("/pecas")
    public Peca cadastrarPeca(@Valid @RequestBody Peca novaPeca) {
        return pecaRepo.save(novaPeca);
    }

    @GetMapping("/pecas")
    public List<Peca> listarPecas() {
        return pecaRepo.findAll();
    }
    @PutMapping("/pecas/{id}/entrada")
    public Peca registrarEntrada(@PathVariable("id") Long id, @RequestParam("quantidade") int quantidade) {
        return pecaService.darEntradaEstoque(id, quantidade);
    }
    @PutMapping("/pecas/{id}/saida")
    public Peca registrarSaida(@PathVariable("id") Long id, @RequestParam("quantidade") int quantidade) {
        return pecaService.registrarSaida(id, quantidade);
    }
    @GetMapping("/fornecedores/{fornecedorId}/pecas")
    public List<Peca> listarPecasDoFornecedor(@PathVariable("fornecedorId") Long fornecedorId) {
        return pecaRepo.findByFornecedorId(fornecedorId);
    }
    @GetMapping("/pecas/{id}/movimentacoes")
    public List<Movimentacao> emitirExtratoDaPeca(@PathVariable("id") Long id) {
        return movimentacaoRepo.findByPecaIdOrderByDataHoraDesc(id);
    }
    @GetMapping("/pecas/alerta-estoque")
    public List<PecaResponseDTO> emitirAlertaEstoque(
            @RequestParam(value = "limite", defaultValue = "5") int limite) {

        List<Peca> pecasEmAlerta = pecaService.buscarPecasEmAlerta(limite);
        return pecasEmAlerta.stream()
                .map(PecaResponseDTO::new)
                .toList();
    }
    @GetMapping("/pecas/relatorio")
    public ResponseEntity<byte[]> baixarRelatorioExcel() {
        String conteudoCsv = pecaService.gerarRelatorioCsv();
        byte[] arquivoFisico = conteudoCsv.getBytes();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=relatorio-inventario.csv")
                // Define o tipo do arquivo
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(arquivoFisico);
    }
}
