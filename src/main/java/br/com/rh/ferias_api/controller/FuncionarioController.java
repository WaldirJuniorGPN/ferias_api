package br.com.rh.ferias_api.controller;

import br.com.rh.ferias_api.dto.request.DadosAtualizacaoFuncionario;
import br.com.rh.ferias_api.dto.request.DadosCadastroFuncionario;
import br.com.rh.ferias_api.dto.response.DadosDetalhamentoFuncionario;
import br.com.rh.ferias_api.dto.response.DadosListagemFuncionario;
import br.com.rh.ferias_api.model.Funcionario;
import br.com.rh.ferias_api.repository.FuncionarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RequestMapping("funcionario")
@RestController
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroFuncionario dados, UriComponentsBuilder uriComponentsBuilder) {
        var funcionario = new Funcionario(dados);
        this.repository.save(funcionario);
        var uri = uriComponentsBuilder.path("funcionario/{id}").buildAndExpand(funcionario.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoFuncionario(funcionario));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemFuncionario>> listar(Pageable paginacao) {
        var page = this.repository.findAll(paginacao).map(DadosListagemFuncionario::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoFuncionario dados) {
        var funcionario = this.repository.getReferenceById(dados.id());
        funcionario.atualizarDadosFuncionario(dados);
        return ResponseEntity.ok(new DadosDetalhamentoFuncionario(funcionario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Long id) {
        this.repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
