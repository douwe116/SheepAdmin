package nl.vissersuwald.sheepadmin.repositories;

import nl.vissersuwald.sheepadmin.models.farming.FosterReference;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FosterReferenceRepository extends JpaRepository<FosterReference, Long> {
}
