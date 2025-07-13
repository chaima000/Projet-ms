package tn.esprit.Foyer_ms.Foyer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.Foyer_ms.Foyer.dto.FoyerWithBlocsRequest;
import tn.esprit.Foyer_ms.Foyer.entity.Foyer;
import tn.esprit.Foyer_ms.Foyer.service.FoyerService;
import tn.esprit.Foyer_ms.Foyer.dto.Bloc;
import java.util.List;

@RestController
@RequestMapping("/foyers")
public class FoyerController {

    @Autowired
    private FoyerService foyerService;

    @PostMapping
    public Foyer createFoyer(@RequestBody Foyer foyer) {
        return foyerService.saveFoyer(foyer);
    }

    @GetMapping
    public List<Foyer> getAllFoyers() {
        return foyerService.getAllFoyers();
    }

    @GetMapping("/{id}")
    public Foyer getFoyerById(@PathVariable Long id) {
        return foyerService.getFoyerById(id);
    }

    @PutMapping("/{id}")
    public Foyer updateFoyer(@PathVariable Long id, @RequestBody Foyer foyer) {
        return foyerService.updateFoyer(id, foyer);
    }

    @DeleteMapping("/{id}")
    public void deleteFoyer(@PathVariable Long id) {
        foyerService.deleteFoyer(id);
    }

    @GetMapping("/external-blocs")
    public List<Bloc> getBlocsFromBlocService() {
        return foyerService.getBlocsFromBlocService();
    }
    @PostMapping("/with-blocs")
    public ResponseEntity<Foyer> createFoyerWithBlocs(@RequestBody FoyerWithBlocsRequest req) {
        Foyer created = foyerService.addFoyerWithBlocs(req.getNom(), req.getBlocIds());
        return ResponseEntity.ok(created);
    }
}
