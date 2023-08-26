package br.com.rh.ferias_api.model;

import br.com.rh.ferias_api.dto.request.DadosCadastroLoja;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "Loja")
@Table(name = "lojas")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Loja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @OneToMany(mappedBy = "loja")
    private List<Funcionario> funcionario;

    public Loja(DadosCadastroLoja dados) {
        this.nome = dados.nome();
    }
}
