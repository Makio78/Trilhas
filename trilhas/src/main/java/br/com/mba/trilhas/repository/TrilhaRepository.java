package br.com.mba.trilhas.repository;

import br.com.mba.trilhas.model.Trilha;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrilhaRepository extends JpaRepository<Trilha, Long> {

}
