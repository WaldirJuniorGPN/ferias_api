package br.com.rh.ferias_api.model;

import br.com.rh.ferias_api.repository.request.DadosCadastroFuncionario;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity(name = "Funcionario")
@Table(name = "funcionarios")
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private LocalDate dataAdimissao;
    private LocalDate ultimaFerias;
    @ManyToOne
    @JoinColumn(name = "loja_id")
    private Loja loja;

    public Funcionario(DadosCadastroFuncionario dados) {
        this.nome = dados.nome();
        this.dataAdimissao = dados.dataAdmissao();
        this.loja = dados.loja();
        this.loja.adicionarFuncionario(this);
    }
}
