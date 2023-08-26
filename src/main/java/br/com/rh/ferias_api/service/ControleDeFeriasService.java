package br.com.rh.ferias_api.service;

import br.com.rh.ferias_api.model.Funcionario;
import br.com.rh.ferias_api.model.Status;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class ControleDeFeriasService {

    public void carregarDados(List<Funcionario> lista) {
        lista.forEach(this::calcularDiasTranscorridos);
        lista.forEach(this::atribuirStatus);
    }

    private void calcularDiasTranscorridos(Funcionario funcionario) {
        var dataUltimasFerias = funcionario.getDataUltimasFerias();

        if (dataUltimasFerias != null) {
            var diasTranscorridos = ChronoUnit.DAYS.between(dataUltimasFerias, LocalDate.now());
            funcionario.setDiasTranscorridos((int) diasTranscorridos);
        } else {
            var dataAdmissao = funcionario.getDataAdmissao();
            var diasTranscorridos = ChronoUnit.DAYS.between(dataAdmissao, LocalDate.now());
            funcionario.setDiasTranscorridos((int) diasTranscorridos);
        }
    }

    private void atribuirStatus(Funcionario funcionario) {

        var diasTranscorridos = funcionario.getDiasTranscorridos();

        if (diasTranscorridos < 360) {
            funcionario.setStatus(Status.REGULAR);
        } else if (diasTranscorridos > 359 || diasTranscorridos < 690) {
            funcionario.setStatus(Status.ATENÇÃO);
        } else if (diasTranscorridos >= 720) {
            funcionario.setStatus(Status.DUAS_FERIAS_VENCIDAS);
        } else {
            funcionario.setStatus(Status.ALERTA_VERMELHO);
        }
    }
}
