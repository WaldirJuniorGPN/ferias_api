package br.com.rh.ferias_api.dto.response;

import br.com.rh.ferias_api.model.Ferias;
import br.com.rh.ferias_api.model.Funcionario;

import java.time.LocalDate;

public record DadosListagemFerias(Long id, Integer duracaoFerias, LocalDate dataDasFerias, Funcionario funcionario) {
    public DadosListagemFerias(Ferias ferias) {
        this(ferias.getId(), ferias.getDuracaoFerias(), ferias.getDataDasFerias(), ferias.getFuncionario());
    }
}
