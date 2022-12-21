package nl.vissersuwald.sheepadmin.controllers;

import nl.vissersuwald.sheepadmin.models.Sheep;
import nl.vissersuwald.sheepadmin.repositories.SheepRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/sheep")
public class SheepController {
    @Autowired
    private SheepRepository sheepRepository;

    @GetMapping
    public List<Sheep> list() {
        return sheepRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Sheep get(@PathVariable Long id) {
        return  sheepRepository.getReferenceById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Sheep create(@RequestBody final Sheep sheep) {
        return sheepRepository.saveAndFlush(sheep);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable Long id) {
        sheepRepository.deleteById(id);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Sheep update(@PathVariable Long id, @RequestBody Sheep sheep) {
        Sheep existingSheep = sheepRepository.getReferenceById(id);
        BeanUtils.copyProperties(sheep, existingSheep, "id");
        return sheepRepository.saveAndFlush(existingSheep);
    }
}
