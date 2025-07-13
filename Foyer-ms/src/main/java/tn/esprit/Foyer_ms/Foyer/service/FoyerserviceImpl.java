package tn.esprit.Foyer_ms.Foyer.service;

import tn.esprit.Foyer_ms.Foyer.entity.Foyer;
import tn.esprit.Foyer_ms.Foyer.repository.FoyerRepository;
import tn.esprit.Foyer_ms.Foyer.dto.Bloc;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class FoyerserviceImpl implements FoyerService {

    private final RestTemplate restTemplate;
    private final FoyerRepository repository;
    private static final String BLOC_SERVICE = "blocService";

    public FoyerserviceImpl(RestTemplate restTemplate, FoyerRepository repository) {
        this.restTemplate = restTemplate;
        this.repository = repository;
    }

    // Circuit Breaker fallback si erreur ou service bloc indisponible
    @Override
    @CircuitBreaker(name = BLOC_SERVICE, fallbackMethod = "fallbackGetBlocsFromBlocService")
    public List<Bloc> getBlocsFromBlocService() {
        String url = "http://localhost:8200/blocs";
        ResponseEntity<List<Bloc>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Bloc>>() {}
        );
        return response.getBody();
    }

    // Méthode fallback renvoyant liste vide et log d’erreur
    public List<Bloc> fallbackGetBlocsFromBlocService(Throwable t) {
        System.out.println("Bloc service unreachable, fallback triggered: " + t.getMessage());
        return Collections.emptyList();
    }

    @Override
    public Foyer saveFoyer(Foyer foyer) {
        return repository.save(foyer);
    }

    @Override
    public List<Foyer> getAllFoyers() {
        return repository.findAll();
    }

    @Override
    public Foyer getFoyerById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Foyer updateFoyer(Long id, Foyer foyer) {
        Foyer existing = repository.findById(id).orElse(null);
        if (existing != null) {
            existing.setNom(foyer.getNom());
            existing.setBlocIds(foyer.getBlocIds());
            return repository.save(existing);
        }
        return null;
    }

    @Override
    public void deleteFoyer(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Foyer addFoyerWithBlocs(String nomFoyer, List<String> blocIds) {
        List<String> validatedBlocs = new ArrayList<>();

        for (String blocId : blocIds) {
            try {
                //Vérifie que le bloc existe dans Bloc-MS
                Bloc bloc = restTemplate.getForObject("http://localhost:8200/blocs/" + blocId, Bloc.class);

                // Vérifie que ce bloc n'est pas déjà ajouté au même foyer
                if (validatedBlocs.contains(blocId)) {
                    System.out.println("Bloc déjà affecté à ce foyer : " + blocId);
                } else {
                    validatedBlocs.add(blocId); // Ajoute s’il est valide et unique
                }

            } catch (Exception e) {
                System.out.println("Bloc introuvable ou erreur d'appel : " + blocId);
            }
        }

        //  Création du foyer avec les blocs valides
        Foyer foyer = new Foyer();
        foyer.setNom(nomFoyer);
        foyer.setBlocIds(validatedBlocs);
        return repository.save(foyer);
    }
}
