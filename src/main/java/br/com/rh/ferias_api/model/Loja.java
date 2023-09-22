package br.com.rh.ferias_api.model;

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

    public void adicionarFuncionario(Funcionario funcionario) {
        funcionarios.add(funcionario);
    }
}
