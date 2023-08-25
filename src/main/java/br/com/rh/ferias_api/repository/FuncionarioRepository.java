package br.com.rh.ferias_api.repository;

import br.com.rh.ferias_api.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
}
