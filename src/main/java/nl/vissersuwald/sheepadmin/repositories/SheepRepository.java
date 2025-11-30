package nl.vissersuwald.sheepadmin.repositories;

import nl.vissersuwald.sheepadmin.models.farming.Sheep;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SheepRepository extends JpaRepository<Sheep, Long> {

}
