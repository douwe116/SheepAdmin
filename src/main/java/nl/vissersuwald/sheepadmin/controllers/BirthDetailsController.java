package nl.vissersuwald.sheepadmin.controllers;


import nl.vissersuwald.sheepadmin.models.BirthDetails;
import nl.vissersuwald.sheepadmin.repositories.BirthDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/birthdetails")
public class BirthDetailsController {
    @Autowired
    private BirthDetailsRepository birthDetailsRepository;

    @GetMapping
    public List<BirthDetails> list() {
        return birthDetailsRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{motherid}")
    public List<BirthDetails> get(@PathVariable Long motherid) {
        return birthDetailsRepository.findByMotherIdOrderByYearOfBirthDesc(motherid);
    }
}
