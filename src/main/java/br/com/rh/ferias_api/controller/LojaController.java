package br.com.rh.ferias_api.controller;

import br.com.rh.ferias_api.dto.request.DadosCadastroLoja;
import br.com.rh.ferias_api.dto.response.DadosDetalhamentoLoja;
import br.com.rh.ferias_api.dto.response.DadosListagemLoja;
import br.com.rh.ferias_api.model.Loja;
import br.com.rh.ferias_api.repository.LojaRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("lojas")
public class LojaController {

    @Autowired
    private LojaRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroLoja dados, UriComponentsBuilder uriBuilder){
        var loja = new Loja(dados);
        repository.save(loja);
        var uri = uriBuilder.path("/lojas/{id}").buildAndExpand(loja.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoLoja(loja));
    }

    @GetMapping
    public ResponseEntity listar(Pageable paginacao){
        var page = repository.findAll(paginacao).map(DadosListagemLoja::new);
        return ResponseEntity.ok(page);
    }
}
