package nl.vissersuwald.sheepadmin.repositories.numbering;

import nl.vissersuwald.sheepadmin.models.ActiveParameters;
import nl.vissersuwald.sheepadmin.models.Sheep;
import nl.vissersuwald.sheepadmin.models.numbering.NumberSheep;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NumberSheepRepository extends JpaRepository<ActiveParameters, Long> {
    List<Sheep> addNumberingToBirth(NumberSheep birthId);
}
