package br.com.rh.ferias_api.service;

import br.com.rh.ferias_api.dto.request.DadosAtualizacaoFuncionario;
import br.com.rh.ferias_api.dto.request.DadosCadastroFuncionario;
import br.com.rh.ferias_api.dto.request.DadosCadastroLoja;
import br.com.rh.ferias_api.model.Funcionario;
import br.com.rh.ferias_api.model.Loja;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

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
    void verificaSeAsFeriasEstaoVencidas() {
        LocalDate dataAdmissao = LocalDate.of(2022, 2, 1);
        var DadosAtualizacaoFuncionario = new DadosAtualizacaoFuncionario(1L, null, dataAdmissao,null);
        this.feriasService.verificaFerias(this.funcionario);

    }
}
