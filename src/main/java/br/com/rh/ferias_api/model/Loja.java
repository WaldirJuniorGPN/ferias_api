package br.com.rh.ferias_api.model;

import br.com.rh.ferias_api.dto.request.DadosAtualizacaoLoja;
import br.com.rh.ferias_api.dto.request.DadosCadastroLoja;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "Loja")
@Table(name = "lojas")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Loja {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @OneToMany(mappedBy = "loja", cascade = CascadeType.ALL)
    private List<Funcionario> funcionarios;

    public Loja(DadosCadastroLoja dados) {
        this.nome = dados.nome();
    }

    public void adicionarFuncionario(Funcionario funcionario) {
        funcionarios.add(funcionario);
    }

    public void atualizarLoja(DadosAtualizacaoLoja dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
    }
}
