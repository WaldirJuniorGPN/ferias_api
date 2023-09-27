package br.com.rh.ferias_api.dto.request;

import br.com.rh.ferias_api.model.Funcionario;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DadosCadastroFerias(
        @NotNull
        Integer duracaoFerias,
        @NotNull
        LocalDate dataDasFerias,
        @NotNull
        Funcionario funcionario
) {
}
