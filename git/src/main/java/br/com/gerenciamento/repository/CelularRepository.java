package br.com.gerenciamento.repository;

import br.com.gerenciamento.model.Celular;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CelularRepository extends JpaRepository<Celular, Long> {

    @Query("SELECT c FROM Celular c WHERE c.status = 'ATIVO' ")
    public List<Celular> findByStatusAtivo();

    @Query("SELECT c FROM Celular c WHERE c.status = 'INATIVO' ")
    public List<Celular> findByStatusInativo();

    public List<Celular> findByModeloContainingIgnoreCase(String modelo);

}
