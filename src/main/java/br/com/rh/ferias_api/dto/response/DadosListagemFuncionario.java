package br.com.rh.ferias_api.dto.response;

import br.com.rh.ferias_api.model.Funcionario;
import br.com.rh.ferias_api.model.Loja;
import br.com.rh.ferias_api.model.Status;

import java.time.LocalDate;

public record DadosListagemFuncionario(Long id, String nome, LocalDate dataAdmissão, LocalDate ultimasFerias,
                                       Integer diasTranscorridos,
                                       Status status,
                                       Loja loja) {
    public DadosListagemFuncionario(Funcionario funcionario) {
        this(funcionario.getId(), funcionario.getNome(), funcionario.getDataAdmissao(), funcionario.getDataUltimasFerias(), funcionario.getDiasTranscorridos(), funcionario.getStatus(), funcionario.getLoja());
    }
}
