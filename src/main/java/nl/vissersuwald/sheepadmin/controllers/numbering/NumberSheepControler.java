package nl.vissersuwald.sheepadmin.controllers.numbering;

import nl.vissersuwald.sheepadmin.models.Sheep;
import nl.vissersuwald.sheepadmin.models.numbering.NumberSheep;
import nl.vissersuwald.sheepadmin.repositories.numbering.NumberSheepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/numbersheep")
public class NumberSheepControler {
    @Autowired
    private NumberSheepRepository numberSheepRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<Sheep> create(@RequestBody final NumberSheep numberSheep) {

        return numberSheepRepository.addNumberingToBirth(numberSheep);
    }
}
