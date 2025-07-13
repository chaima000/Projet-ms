package tn.esprit.Bloc_ms.Bloc.service;
import tn.esprit.Bloc_ms.Bloc.entity.Bloc ;

import java.util.List;
import java.util.Optional;

public interface BlocService {

    List<Bloc> getAll();

    Optional<Bloc> getById(String id);

    Bloc save(Bloc bloc);

    Bloc update(String id, Bloc bloc);

    void delete(String id);
}

