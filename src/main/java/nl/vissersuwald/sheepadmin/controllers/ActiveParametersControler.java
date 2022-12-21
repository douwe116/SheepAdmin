package nl.vissersuwald.sheepadmin.controllers;

import nl.vissersuwald.sheepadmin.models.ActiveParameters;
import nl.vissersuwald.sheepadmin.repositories.ActiveParametersRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/parameter")
public class ActiveParametersControler {
    @Autowired
    private ActiveParametersRepository activeParametersRepository;

    @GetMapping
    public List<ActiveParameters> list() {
        return activeParametersRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public ActiveParameters get(@PathVariable Long id) {
        return  activeParametersRepository.getReferenceById(id);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ActiveParameters update(@PathVariable Long id, @RequestBody ActiveParameters activeParameters) {
        ActiveParameters existingActiveParameters = activeParametersRepository.getReferenceById(id);
        BeanUtils.copyProperties(activeParameters, existingActiveParameters, "id", "paraName");;
        return activeParametersRepository.saveAndFlush(existingActiveParameters);
    }
}
