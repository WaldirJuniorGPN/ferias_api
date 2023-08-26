package br.com.rh.ferias_api.dto.request;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoLoja(
        @NotNull
        Long id,
        String nome
) {
}
