package br.com.rh.ferias_api.model;

import br.com.rh.ferias_api.dto.request.DadosAtualizacaoFerias;
import br.com.rh.ferias_api.dto.request.DadosCadastroFerias;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Table(name = "ferias")
@Entity(name = "Ferias")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Ferias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer duracaoFerias;
    private LocalDate dataDasFerias;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

    public Ferias(DadosCadastroFerias dados) {
        this.duracaoFerias = dados.duracaoFerias();
        this.dataDasFerias = dados.dataDasFerias();
        this.funcionario = dados.funcionario();
    }

    public void atualizarFerias(DadosAtualizacaoFerias dados) {
        if (dados.duracaoFerias() != null) {
            this.duracaoFerias = dados.duracaoFerias();
        }
        if (dados.dataDasFerias() != null) {
            this.dataDasFerias = dados.dataDasFerias();
        }
        if (dados.funcionario() != null) {
            this.funcionario = dados.funcionario();
        }
    }
}
