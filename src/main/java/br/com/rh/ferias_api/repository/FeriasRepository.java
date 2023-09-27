package br.com.rh.ferias_api.repository;

import br.com.rh.ferias_api.model.Ferias;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeriasRepository extends JpaRepository<Ferias, Long> {
}
