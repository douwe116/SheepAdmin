package nl.vissersuwald.sheepadmin.controllers;

import nl.vissersuwald.sheepadmin.models.farming.Parameter;
import nl.vissersuwald.sheepadmin.repositories.farming.ParameterRepository;
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
    private ParameterRepository parameterRepository;

    @GetMapping
    public List<Parameter> list() {
        return parameterRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Parameter get(@PathVariable Long id) {
        return  parameterRepository.getReferenceById(id);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Parameter update(@PathVariable Long id, @RequestBody Parameter parameters) {
        Parameter existingParameters = parameterRepository.getReferenceById(id);
        BeanUtils.copyProperties(parameters, existingParameters, "id", "paraName");;
        return parameterRepository.saveAndFlush(existingParameters);
    }
}
