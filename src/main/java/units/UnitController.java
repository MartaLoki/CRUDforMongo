package units;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@Slf4j
@RestController
public class UnitController {
    private UnitRepository unitRepository;

    @Autowired
    public UnitController(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }

    @Autowired
    private UnitService unitService;


    @PostMapping(value = "/createUnit")
    public void createUnit(@Valid @RequestBody Unit unit) {
        log.info("Creating new Unit with name: " + unit.getUnitName());
        if (!unitService.doesUnitNameAlreadyExists(unit.getUnitName())) {
            unitService.createNewUnit(unit);
            log.info("New Unit created successfully.");
        } else {
            throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, "New Unit not created, name already exists.");
        }

    }

    @GetMapping("/units")
    public List<Unit> getUnits() {
        log.info("Units received: " + unitService.getUnits());
        return unitService.getUnits();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUnit(@PathVariable String id ) {
        log.info("Deleting unit by ID: " + id);
        unitService.deleteUnit(id);
    }

    @PutMapping("/update/{id}")
    public void updateUnit(@PathVariable String id, @Valid @RequestBody Unit unit) {
        log.info("Updating data for Unit id:" + id);
        unitService.updateUnit(id, unit);
    }


}
