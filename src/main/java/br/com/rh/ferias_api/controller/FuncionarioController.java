package br.com.rh.ferias_api.controller;

import br.com.rh.ferias_api.controller.dto.request.DadosCadastroFuncionario;
import br.com.rh.ferias_api.dto.response.DadosDetalhamentoFuncionario;
import br.com.rh.ferias_api.model.Funcionario;
import br.com.rh.ferias_api.repository.FuncionarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
