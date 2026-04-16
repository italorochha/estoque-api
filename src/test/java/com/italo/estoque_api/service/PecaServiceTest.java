package com.italo.estoque_api.service;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.italo.estoque_api.model.Peca;
import com.italo.estoque_api.repository.MovimentacaoRepository;
import com.italo.estoque_api.repository.PecaRepository;

@ExtendWith(MockitoExtension.class)
public class PecaServiceTest {
    @InjectMocks
    private PecaService pecaService;

    @Mock
    private PecaRepository pecaRepo;

    @Mock
    private MovimentacaoRepository movimentacaoRepo;

    @Test
    public void deveLancarErroQuandoEstoqueForInsuficiente() {

        Peca pecaFalsa = new Peca();
        pecaFalsa.setId(1L);
        pecaFalsa.setNome("Pastilha de Freio");
        pecaFalsa.setQuantidade(10);

        Mockito.when(pecaRepo.findById(1L)).thenReturn(Optional.of(pecaFalsa));

        RuntimeException erroCapturado = assertThrows(RuntimeException.class, () -> {
            pecaService.registrarSaida(1L, 50);
        });
        assertEquals("Estoque insuficiente! Temos apenas 10 unidades da peça.", erroCapturado.getMessage());
    }
}