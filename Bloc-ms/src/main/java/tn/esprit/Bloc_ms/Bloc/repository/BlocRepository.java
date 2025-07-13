package tn.esprit.Bloc_ms.Bloc.repository;



import org.springframework.data.mongodb.repository.MongoRepository;
import tn.esprit.Bloc_ms.Bloc.entity.Bloc;

public interface BlocRepository extends MongoRepository<Bloc, String> {
    // CRUD de base inclus
}

