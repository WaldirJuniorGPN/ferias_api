package br.com.rh.ferias_api.model;

import br.com.rh.ferias_api.dto.request.DadosAtualizarFuncionario;
import br.com.rh.ferias_api.dto.request.DadosCadastroFuncionario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity(name = "Funcionario")
@Table(name = "funcionarios")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private LocalDate dataAdmissao;
    private LocalDate dataUltimasFerias;
    private int diasTranscorridos;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "loja_id")
    private Loja loja;

    public Funcionario(DadosCadastroFuncionario dados) {
        this.nome = dados.nome();
        this.dataAdmissao = dados.dataAdimissao();
        this.dataUltimasFerias = dados.dataUltimasFerias();
        this.loja = dados.loja();
    }

    public void atualizar(DadosAtualizarFuncionario dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.dataAdmissao() != null) {
            this.dataAdmissao = dados.dataAdmissao();
        }
        if (dados.ultimasFerias() != null) {
            this.dataUltimasFerias = dados.ultimasFerias();
        }
        if (dados.loja() != null) {
            this.loja = dados.loja();
        }
    }

    public void setDiasTranscorridos(int diasTranscorridos) {
        this.diasTranscorridos = diasTranscorridos;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
