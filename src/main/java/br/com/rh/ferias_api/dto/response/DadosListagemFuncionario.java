package br.com.rh.ferias_api.dto.response;

import br.com.rh.ferias_api.model.Funcionario;
import br.com.rh.ferias_api.model.Loja;

import java.time.LocalDate;

public record DadosListagemFuncionario(Long id, String nome, LocalDate dataAdmissao, Loja loja) {
    public DadosListagemFuncionario(Funcionario funcionario) {
        this(funcionario.getId(), funcionario.getNome(), funcionario.getDataAdimissao(), funcionario.getLoja());
    }
}
