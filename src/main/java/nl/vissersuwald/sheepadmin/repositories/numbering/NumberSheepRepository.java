package nl.vissersuwald.sheepadmin.repositories.numbering;

import nl.vissersuwald.sheepadmin.models.farming.Parameter;
import nl.vissersuwald.sheepadmin.models.farming.Sheep;
import nl.vissersuwald.sheepadmin.models.numbering.NumberSheep;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NumberSheepRepository extends JpaRepository<Parameter, Long> {
    List<Sheep> addNumberingToBirth(NumberSheep birthId);
}
