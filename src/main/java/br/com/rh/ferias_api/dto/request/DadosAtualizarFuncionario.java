package br.com.rh.ferias_api.dto.request;

import br.com.rh.ferias_api.model.Loja;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DadosAtualizarFuncionario(
        @NotNull
        Long id,
        String nome,
        LocalDate dataAdmissao,
        LocalDate ultimasFerias,
        Loja loja
) {
}
