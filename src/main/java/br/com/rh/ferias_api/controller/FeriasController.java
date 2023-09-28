package br.com.rh.ferias_api.controller;

import br.com.rh.ferias_api.dto.request.DadosAtualizacaoFerias;
import br.com.rh.ferias_api.dto.request.DadosCadastroFerias;
import br.com.rh.ferias_api.dto.response.DadosDetalhamentoFerias;
import br.com.rh.ferias_api.dto.response.DadosListagemFerias;
import br.com.rh.ferias_api.model.Ferias;
import br.com.rh.ferias_api.repository.FeriasRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/ferias")
public class FeriasController {
    @Autowired
    private FeriasRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroFerias dados, UriComponentsBuilder uriComponentsBuilder) {
        var ferias = new Ferias(dados);
        this.repository.save(ferias);
        var uri = uriComponentsBuilder.path("/ferias/{id}").buildAndExpand(ferias.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoFerias(ferias));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoFerias dados) {
        var ferias = this.repository.getReferenceById(dados.id());
        ferias.atualizarFerias(dados);
        return ResponseEntity.ok(new DadosDetalhamentoFerias(ferias));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemFerias>> listar(Pageable paginacao) {
        var page = this.repository.findAll(paginacao).map(DadosListagemFerias::new);
        return ResponseEntity.ok(page);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Long id) {
        this.repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
