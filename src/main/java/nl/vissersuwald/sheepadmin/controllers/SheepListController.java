package nl.vissersuwald.sheepadmin.controllers;


import nl.vissersuwald.sheepadmin.models.SheepList;
import nl.vissersuwald.sheepadmin.repositories.SheepListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/sheeplist")
public class SheepListController {

    @Autowired
    private SheepListRepository sheepListRepository;

    @GetMapping
    public List<SheepList> list() {
        return sheepListRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public SheepList get(@PathVariable Long id){
        return sheepListRepository.getReferenceById(id);
    }
}
