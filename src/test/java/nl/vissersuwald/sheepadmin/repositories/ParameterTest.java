package nl.vissersuwald.sheepadmin.repositories;

import nl.vissersuwald.sheepadmin.models.farming.Parameter;
import nl.vissersuwald.sheepadmin.repositories.farming.ParameterRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class ParameterTest {

    @Autowired
    private ParameterRepository parameterRepository;

    @Test
    void  testSaveAndFindParameter() {
        String parameterName = "String parameter";
        Parameter stringParameter = new Parameter(parameterName, "test value");

        Parameter savedStringParameter = parameterRepository.save(stringParameter);
        Optional<Parameter> foundStringParameter = parameterRepository.findById(stringParameter.getId());

        assertTrue(foundStringParameter.isPresent());
        assertEquals(parameterName, foundStringParameter.get().getParameterName());
    }
}
