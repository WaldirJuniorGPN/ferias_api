package br.com.rh.ferias_api.dto.request;

import br.com.rh.ferias_api.model.Loja;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DadosAtualizacaoFuncionario(
        @NotNull
        Long id,
        @NotBlank
        String nome,
        LocalDate dataAdimissao,
        LocalDate ultimaFerias,
        Loja loja
) {
}
