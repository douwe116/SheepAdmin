package nl.vissersuwald.sheepadmin.repositories;

import nl.vissersuwald.sheepadmin.models.farming.ActiveParameters;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActiveParametersRepository extends JpaRepository<ActiveParameters, Long> {

}
