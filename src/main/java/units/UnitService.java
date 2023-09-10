package units;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UnitService {
    @Autowired
    private UnitRepository unitRepository;


    public void createNewUnit(Unit unit) {
        unitRepository.insert(unit);
    }

    public List<Unit> getUnits() {

        return unitRepository.findAll();
    }

    public boolean doesUnitNameAlreadyExists(String unitName) {
        return !findByName(unitName).isEmpty();
    }
    public List<Unit> findByName(String unitName) {
        return unitRepository.findByUnitName(unitName);
    }

    public void deleteUnit(String id) {
        unitRepository.deleteById(id);
    }

    public void updateUnit(String id, Unit unit) {
        Unit currentUnit = unitRepository.findById(id).orElseThrow(() -> new RuntimeException("Could not find Unit id: " + id));
        currentUnit.setUnitName(unit.getUnitName());
        currentUnit.setQuantity(unit.getQuantity());
        unitRepository.save(currentUnit);
    }


}
