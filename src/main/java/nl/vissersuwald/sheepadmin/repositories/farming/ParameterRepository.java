package nl.vissersuwald.sheepadmin.repositories.farming;

import nl.vissersuwald.sheepadmin.models.farming.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParameterRepository extends JpaRepository<Parameter, Long> {

}
