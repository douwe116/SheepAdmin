package nl.vissersuwald.sheepadmin.controllers;

import nl.vissersuwald.sheepadmin.dto.SheepListDto;
import nl.vissersuwald.sheepadmin.repositories.farming.SheepRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/sheeplist")
public class SheepListController {
    private final SheepRepository sheepRepository;

    public SheepListController(SheepRepository sheepRepository) {
        this.sheepRepository = sheepRepository;
    }

    @GetMapping
    public Page<SheepListDto> getSheepList(
            @RequestParam(required = true) Long currentYear,
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        Pageable pageable = (Pageable) PageRequest.of(page, size, Sort.by("name").ascending());
        return sheepRepository.findSheepList(currentYear, currentYear - 1L, name, pageable);
    }
}
