package nl.vissersuwald.sheepadmin.controllers;

import nl.vissersuwald.sheepadmin.models.Birth;
import nl.vissersuwald.sheepadmin.models.Sheep;
import nl.vissersuwald.sheepadmin.repositories.BirthRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/births")
public class BirthController {
    @Autowired
    private BirthRepository birthRepository;

    @GetMapping
    public List<Birth> list() {
        return birthRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Birth get(@PathVariable Long id) {
        return birthRepository.getReferenceById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Birth create(@RequestBody final Birth birth) {
        return birthRepository.saveAndFlush(birth);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Birth update(@PathVariable Long id, @RequestBody Birth birth) {
        Birth existingBirth = birthRepository.getReferenceById(id);
        BeanUtils.copyProperties(birth, existingBirth, "id");
        return birthRepository.saveAndFlush((existingBirth));
    }
}
