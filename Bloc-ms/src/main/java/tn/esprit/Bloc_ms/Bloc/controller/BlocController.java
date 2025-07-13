package tn.esprit.Bloc_ms.Bloc.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import  tn.esprit.Bloc_ms.Bloc.repository.BlocRepository;
import tn.esprit.Bloc_ms.Bloc.entity.Bloc;
import  tn.esprit.Bloc_ms.Bloc.service.BlocService;
import java.util.List;

@RestController
@RequestMapping("/blocs")
public class BlocController {

    private final BlocService blocService;

    public BlocController(BlocService blocService) {
        this.blocService = blocService;
    }

    @GetMapping
    public List<Bloc> getAll() {

        return blocService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bloc> getById(@PathVariable String id) {
        return blocService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Bloc create(@RequestBody Bloc bloc) {
        return blocService.save(bloc);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bloc> update(@PathVariable String id, @RequestBody Bloc bloc) {
        try {
            Bloc updatedBloc = blocService.update(id, bloc);
            return ResponseEntity.ok(updatedBloc);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        blocService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
