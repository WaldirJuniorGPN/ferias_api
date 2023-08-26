package br.com.rh.ferias_api.dto.response;

import br.com.rh.ferias_api.model.Loja;

public record DadosDetalhamentoLoja(Long id, String nome) {
    public DadosDetalhamentoLoja(Loja loja){
        this(loja.getId(), loja.getNome());
    }
}
