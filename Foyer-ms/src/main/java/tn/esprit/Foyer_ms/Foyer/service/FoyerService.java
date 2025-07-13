package tn.esprit.Foyer_ms.Foyer.service;
import tn.esprit.Foyer_ms.Foyer.entity.Foyer;
import tn.esprit.Foyer_ms.Foyer.dto.Bloc;
import java.util.List;

public interface FoyerService {
    Foyer saveFoyer(Foyer foyer);
    List<Foyer> getAllFoyers();
    Foyer getFoyerById(Long id);
    Foyer updateFoyer(Long id, Foyer foyer);
    void deleteFoyer(Long id);
    List<Bloc> getBlocsFromBlocService();
    Foyer addFoyerWithBlocs(String nomFoyer, List<String> blocIds);
}
