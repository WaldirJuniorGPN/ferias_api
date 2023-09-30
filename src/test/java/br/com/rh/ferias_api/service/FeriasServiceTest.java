package br.com.rh.ferias_api.service;

import br.com.rh.ferias_api.dto.request.DadosAtualizacaoFuncionario;
import br.com.rh.ferias_api.dto.request.DadosCadastroFuncionario;
import br.com.rh.ferias_api.dto.request.DadosCadastroLoja;
import br.com.rh.ferias_api.model.Funcionario;
import br.com.rh.ferias_api.model.Loja;
import br.com.rh.ferias_api.model.StatusDeFerias;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class FeriasServiceTest {

    private Funcionario funcionario;
    private DadosCadastroFuncionario dtoFuncionario;
    private DadosCadastroLoja dtoLoja;
    private Loja loja;

    private FeriasService feriasService;

    @BeforeEach
    void setUp() {
        this.dtoLoja = new DadosCadastroLoja("NovoMundo");
        this.loja = new Loja(dtoLoja);
        this.dtoFuncionario = new DadosCadastroFuncionario("Fulano", LocalDate.now(), this.loja);
        this.funcionario = new Funcionario(dtoFuncionario);
        this.feriasService = new FeriasService();
    }

    @Test
    void verificarPrimeiraFeriasVencidas() {
        LocalDate dataAdmissao = LocalDate.of(2022, 2, 1);
        var dadosAtualizacaoFuncionario = new DadosAtualizacaoFuncionario(1L, null, dataAdmissao, null);
        this.funcionario.atualizarDadosFuncionario(dadosAtualizacaoFuncionario);
        this.feriasService.verificaFerias(this.funcionario);

        assertEquals(StatusDeFerias.PRIMEIRA_FERIAS_VENCIDA, this.funcionario.getStatus());
    }

    @Test
    void verificarSegundaFeriasProximoDeVencer() {
        var dataAdmissao = LocalDate.of(2021, 12, 1);
        var dadosAtualizacaoFuncionario = new DadosAtualizacaoFuncionario(1L, null, dataAdmissao, null);
        this.funcionario.atualizarDadosFuncionario(dadosAtualizacaoFuncionario);
        this.feriasService.verificaFerias(this.funcionario);

        assertEquals(StatusDeFerias.SEGUNDA_FERIAS_PROXIMO_DE_VENCER, this.funcionario.getStatus());
    }

    @Test
    void verificaSegundaFeriasVencidas() {
        var dataAdmissao = LocalDate.of(2021, 9, 1);
        var dadosAtualizacaoFuncionario = new DadosAtualizacaoFuncionario(1L, null, dataAdmissao, null);
        this.funcionario.atualizarDadosFuncionario(dadosAtualizacaoFuncionario);
        this.feriasService.verificaFerias(this.funcionario);

        assertEquals(StatusDeFerias.SEGUNDA_FERIAS_VENCIDA, this.funcionario.getStatus());
    }
}
