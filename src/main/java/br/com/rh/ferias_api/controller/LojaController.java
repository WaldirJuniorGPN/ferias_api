package br.com.rh.ferias_api.controller;

import br.com.rh.ferias_api.dto.request.DadosAtualizacaoLoja;
import br.com.rh.ferias_api.dto.request.DadosCadastroLoja;
import br.com.rh.ferias_api.dto.response.DadosDetalhamentoLoja;
import br.com.rh.ferias_api.dto.response.DadosListagemLoja;
import br.com.rh.ferias_api.model.Loja;
import br.com.rh.ferias_api.repository.LojaRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
@RequestMapping("lojas")
public class LojaController {

    @Autowired
    private LojaRepository repository;

    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroLoja dados, UriComponentsBuilder uriBuilder) {
        var loja = new Loja(dados);
        repository.save(loja);
        var uri = uriBuilder.path("/lojas/{id}").buildAndExpand(loja.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoLoja(loja));
    }

    @GetMapping
    public String fomularioCadastro(){
        return "lojas/cadastro";
    }

    @GetMapping("/listar")
    public ResponseEntity<Page<DadosListagemLoja>> listar(Pageable paginacao) {
        var page = repository.findAll(paginacao).map(DadosListagemLoja::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping("/atualizar")
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoLoja dados) {
        var loja = repository.getReferenceById(dados.id());
        loja.atualizar(dados);
        return ResponseEntity.ok(new DadosDetalhamentoLoja(loja));
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity deletar(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
