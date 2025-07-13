package tn.esprit.Bloc_ms.Bloc.service;
import tn.esprit.Bloc_ms.Bloc.entity.Bloc;
import tn.esprit.Bloc_ms.Bloc.repository.BlocRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlocServiceImpl implements BlocService {

    private final BlocRepository blocRepository;

    public BlocServiceImpl(BlocRepository blocRepository) {
        this.blocRepository = blocRepository;
    }

    @Override
    public List<Bloc> getAll() {
        return blocRepository.findAll();
    }

    @Override
    public Optional<Bloc> getById(String id) {
        return blocRepository.findById(id);
    }

    @Override
    public Bloc save(Bloc bloc) {
        return blocRepository.save(bloc);
    }

    @Override
    public Bloc update(String id, Bloc bloc) {
        return blocRepository.findById(id).map(existingBloc -> {
            existingBloc.setNom(bloc.getNom());
            existingBloc.setNombreClasses(bloc.getNombreClasses());
            return blocRepository.save(existingBloc);
        }).orElseThrow(() -> new RuntimeException("Bloc not found with id " + id));
    }

    @Override
    public void delete(String id) {
        blocRepository.deleteById(id);
    }
}
