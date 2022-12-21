package nl.vissersuwald.sheepadmin.repositories;

import nl.vissersuwald.sheepadmin.models.BirthDetails;
import nl.vissersuwald.sheepadmin.models.BirthDetailsKey;

import java.util.List;

public interface BirthDetailsRepository extends ReadOnlyRepository<BirthDetails, BirthDetailsKey> {

    List<BirthDetails> findByMotherId(Long motherId);

    List<BirthDetails> findByMotherIdOrderByYearOfBirthDesc(Long motherId);
}
