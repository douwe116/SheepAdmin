package nl.vissersuwald.sheepadmin.controllers;

import nl.vissersuwald.sheepadmin.models.SheepDetails;
import nl.vissersuwald.sheepadmin.repositories.SheepDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/sheepdetails")
public class SheepDetailsController {
    @Autowired
    private SheepDetailsRepository sheepDetailsRepository;

    @GetMapping
    public List<SheepDetails> list() {
        return sheepDetailsRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public SheepDetails get(@PathVariable Long id) {
        return sheepDetailsRepository.getReferenceById(id);
    }
}