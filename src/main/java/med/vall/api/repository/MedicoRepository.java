package med.vall.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import med.vall.api.model.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

}
