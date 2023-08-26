package br.com.rh.ferias_api.repository;

import br.com.rh.ferias_api.model.Loja;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LojaRepository extends JpaRepository<Loja, Long> {
}
