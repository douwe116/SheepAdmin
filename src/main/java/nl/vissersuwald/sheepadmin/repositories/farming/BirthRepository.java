package nl.vissersuwald.sheepadmin.repositories.farming;

import nl.vissersuwald.sheepadmin.models.farming.Birth;
import nl.vissersuwald.sheepadmin.models.farming.BirthId;
import nl.vissersuwald.sheepadmin.models.farming.Sheep;
import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.persistence.*;
import java.util.List;
import java.util.Optional;

public interface BirthRepository extends JpaRepository<Birth, BirthId> {

    List<Birth> findByMotherId(Long motherId);

    List<Birth> findByMother(Sheep mother);

    List<Birth> findById_YearOfBirth(Integer yearOfBirth);

    Optional<Birth> findById_MotherIdAndId_YearOfBirth(Long motherId, Integer year);
}
