package br.com.rh.ferias_api.controller;

import br.com.rh.ferias_api.dto.request.DadosAtualizarFuncionario;
import br.com.rh.ferias_api.dto.request.DadosCadastroFuncionario;
import br.com.rh.ferias_api.dto.response.DadosDetalhamentoFuncionario;
import br.com.rh.ferias_api.dto.response.DadosListagemFuncionario;
import br.com.rh.ferias_api.model.Funcionario;
import br.com.rh.ferias_api.repository.FuncionarioRepository;
import br.com.rh.ferias_api.service.ControleDeFeriasService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("funcionarios")
public class FuncionariosController {

    @Autowired
    private FuncionarioRepository repository;
    @Autowired
    ControleDeFeriasService controle;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroFuncionario dados, UriComponentsBuilder uriBuilder) {
        var funcionario = new Funcionario(dados);
        repository.save(funcionario);
        var uri = uriBuilder.path("/funcionarios/{id}").buildAndExpand(funcionario.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoFuncionario(funcionario));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemFuncionario>> listar(Pageable paginacao) {
        var page = repository.findAll(paginacao).map((DadosListagemFuncionario::new));
        return ResponseEntity.ok(page);
    }

    @GetMapping("/lancar-dados")
    @Transactional
    public ResponseEntity lancarDados() {
        var lista = repository.findAll();
        controle.carregarDados(lista);

        // Redirecionamento para o método listar
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .replacePath("/funcionarios")
                .build()
                .toUri();

        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY)
                .location(location)
                .build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizarFuncionario dados) {
        var funcionario = repository.getReferenceById(dados.id());
        funcionario.atualizar(dados);
        return ResponseEntity.ok(new DadosDetalhamentoFuncionario(funcionario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
