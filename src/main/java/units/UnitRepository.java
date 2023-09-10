package units;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface UnitRepository extends MongoRepository<Unit, String> {
    List<Unit> findByUnitName(String name);
}
