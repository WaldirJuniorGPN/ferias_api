package br.com.rh.ferias_api.dto.request;

import br.com.rh.ferias_api.model.Loja;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DadosCadastroFuncionario(
        @NotBlank
        String nome,
        @NotNull
        LocalDate dataAdimissao,
        LocalDate dataUltimasFerias,
        @NotNull
        Loja loja

) {
}
