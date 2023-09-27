package br.com.rh.ferias_api.model;

import br.com.rh.ferias_api.dto.request.DadosAtualizacaoFuncionario;
import br.com.rh.ferias_api.dto.request.DadosCadastroFuncionario;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

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
    @ManyToOne
    @JoinColumn(name = "loja_id")
    private Loja loja;
    private boolean fericasVencidas = false;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "funcionario_id")
    private List<Ferias> ferias;

    public Funcionario(DadosCadastroFuncionario dados) {
        this.nome = dados.nome();
        this.dataAdimissao = dados.dataAdmissao();
        this.loja = dados.loja();
        this.loja.adicionarFuncionario(this);
    }

    public void atualizarDadosFuncionario(DadosAtualizacaoFuncionario dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.dataAdimissao() != null) {
            this.dataAdimissao = dados.dataAdimissao();
        }
        if (dados.loja() != null) {
            this.loja = dados.loja();
        }
    }

    public void mudarStatusParaFeriasVencidas() {
        this.fericasVencidas = true;
    }
}
