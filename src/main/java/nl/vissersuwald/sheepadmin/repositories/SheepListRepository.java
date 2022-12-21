package nl.vissersuwald.sheepadmin.repositories;

import nl.vissersuwald.sheepadmin.models.SheepList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SheepListRepository extends JpaRepository<SheepList, Long> {
}
