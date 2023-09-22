package br.com.rh.ferias_api.controller.dto.request;

import br.com.rh.ferias_api.model.Loja;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DadosCadastroFuncionario(
        @NotBlank
        String nome,
        @NotNull
        LocalDate dataAdmissao,
        @NotNull
        Loja loja
) {
}
