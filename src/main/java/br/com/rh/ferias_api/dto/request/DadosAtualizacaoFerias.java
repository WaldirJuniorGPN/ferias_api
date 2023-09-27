package br.com.rh.ferias_api.dto.request;

import br.com.rh.ferias_api.model.Funcionario;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DadosAtualizacaoFerias(
        @NotNull
        Long id,
        Integer duracaoFerias,
        LocalDate dataDasFerias,
        Funcionario funcionario
) {
}
