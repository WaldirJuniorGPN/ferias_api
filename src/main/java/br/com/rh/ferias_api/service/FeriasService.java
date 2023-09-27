package br.com.rh.ferias_api.service;

import br.com.rh.ferias_api.model.Funcionario;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class FeriasService {


    public void verificaFerias(Funcionario funcionario) {
        var data = funcionario.getDataAdimissao();
        var dataAtual = LocalDate.now();
        Long diferencaDemeses = ChronoUnit.MONTHS.between(data, dataAtual);

        if (diferencaDemeses >= 12) {
            funcionario.mudarStatusParaFeriasVencidas();
        }
    }
}
