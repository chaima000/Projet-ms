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

    // Nom utilisé dans la configuration Resilience4j
    private static final String BLOC_SERVICE = "blocService";

    public FoyerserviceImpl(RestTemplate restTemplate, FoyerRepository repository) {
        this.restTemplate = restTemplate;
        this.repository = repository;
    }

    @Override
    @CircuitBreaker(name = BLOC_SERVICE, fallbackMethod = "fallbackGetBlocsFromBlocService")
    public List<Bloc> getBlocsFromBlocService() {
        // Note : PAS de port dans l'URL, on utilise juste le service name Eureka
        String url = "http://bloc-ms/blocs";
        ResponseEntity<List<Bloc>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Bloc>>() {}
        );
        return response.getBody();
    }

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
                // Pas de port non plus ici
                Bloc bloc = restTemplate.getForObject("http://bloc-ms/blocs/" + blocId, Bloc.class);

                if (!validatedBlocs.contains(blocId)) {
                    validatedBlocs.add(blocId);
                } else {
                    System.out.println("Bloc déjà affecté à ce foyer : " + blocId);
                }
            } catch (Exception e) {
                System.out.println("Bloc introuvable ou erreur d'appel : " + blocId);
            }
        }

        Foyer foyer = new Foyer();
        foyer.setNom(nomFoyer);
        foyer.setBlocIds(validatedBlocs);
        return repository.save(foyer);
    }
}
