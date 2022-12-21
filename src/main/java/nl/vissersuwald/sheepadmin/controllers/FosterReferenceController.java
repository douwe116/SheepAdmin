package nl.vissersuwald.sheepadmin.controllers;

import nl.vissersuwald.sheepadmin.models.FosterReference;
import nl.vissersuwald.sheepadmin.repositories.FosterReferenceRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/foster")
public class FosterReferenceController {
    @Autowired
    private FosterReferenceRepository fosterReferenceRepository;

    @GetMapping
    public List<FosterReference> list() {
        return fosterReferenceRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public FosterReference get(@PathVariable Long id) {
        return fosterReferenceRepository.getReferenceById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FosterReference create(@RequestBody final FosterReference fosterReference) {
        return fosterReferenceRepository.saveAndFlush(fosterReference);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable Long id) {
        fosterReferenceRepository.deleteById(id);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public FosterReference update(@PathVariable Long id, @RequestBody FosterReference fosterReference) {
        FosterReference existingFosterReference = fosterReferenceRepository.getReferenceById(id);
        BeanUtils.copyProperties(fosterReference, existingFosterReference, "id");
        return fosterReferenceRepository.saveAndFlush(existingFosterReference);
    }
}
