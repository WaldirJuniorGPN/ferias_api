package br.com.rh.ferias_api.dto.response;

import br.com.rh.ferias_api.model.Ferias;
import br.com.rh.ferias_api.model.Funcionario;

import java.time.LocalDate;

public record DadosDetalhamentoFerias(Long id, Integer duracaoFerias, LocalDate dataDasFerias, Funcionario funcionario) {
    public DadosDetalhamentoFerias(Ferias ferias) {
        this(ferias.getId(), ferias.getDuracaoFerias(), ferias.getDataDasFerias(), ferias.getFuncionario());
    }
}
