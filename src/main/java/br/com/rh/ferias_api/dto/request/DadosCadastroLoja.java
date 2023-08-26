package br.com.rh.ferias_api.dto.request;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroLoja(
        @NotBlank
        String nome
) {
}
