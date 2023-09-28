package br.com.rh.ferias_api.service;

import br.com.rh.ferias_api.model.Funcionario;
import br.com.rh.ferias_api.model.StatusDeFerias;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class FeriasService {


    public void verificaFerias(Funcionario funcionario) {
        var dataAdmissao = funcionario.getDataAdimissao();
        var dataAtual = LocalDate.now();
        Long diferencaDemeses = ChronoUnit.MONTHS.between(dataAdmissao, dataAtual);

        if (diferencaDemeses >= 12 && diferencaDemeses < 21) {
            funcionario.setStatus(StatusDeFerias.PRIMEIRA_FERIAS_VENCIDA);
        } else if (diferencaDemeses >= 21 && diferencaDemeses < 24) {
            funcionario.setStatus(StatusDeFerias.SEGUNDA_FERIAS_PROXIMO_DE_VENCER);
        } else if (diferencaDemeses >= 24) {
            funcionario.setStatus(StatusDeFerias.SEGUNDA_FERIAS_VENCIDA);
        }
    }
}
