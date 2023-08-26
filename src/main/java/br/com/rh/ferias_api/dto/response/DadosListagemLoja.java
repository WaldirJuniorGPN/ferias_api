package br.com.rh.ferias_api.dto.response;

import br.com.rh.ferias_api.model.Loja;

public record DadosListagemLoja(Long id, String nome) {

    public DadosListagemLoja(Loja loja) {
        this(loja.getId(), loja.getNome());
    }
}
