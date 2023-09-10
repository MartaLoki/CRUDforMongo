package units;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@Validated
public class UnitController {
    private UnitRepository unitRepository;

    @Autowired
    public UnitController(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }

    @Autowired
    private UnitService unitService;

    @PostMapping(value = "/createUnit")
    public ResponseEntity<String>createUnit(@Valid @RequestBody Unit unit) {
        log.info("Creating new Unit with name: " + unit.getUnitName());
        if (!unitService.doesUnitNameAlreadyExists(unit.getUnitName())) {
            unitService.createNewUnit(unit);
        } else {
            throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, "New Unit not created, name already exists.");
        }
        return ResponseEntity.ok("New unit created.");
    }

    @GetMapping("/units")
    public List<Unit> getUnits() {
        log.info("Units received: " + unitService.getUnits());
        return unitService.getUnits();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUnit(@PathVariable @Pattern(regexp = "[a-zA-Z0-9]*", message = "ID must be alphanumeric") String id ) {
        log.info("Deleting unit by ID: " + id);
        unitService.deleteUnit(id);
        return ResponseEntity.ok("Unit deleted");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUnit(@PathVariable @Pattern(regexp = "[a-zA-Z0-9]*", message = "ID must be alphanumeric") String id , @Valid @RequestBody Unit unit) {
        log.info("Updating data for Unit id:" + id);
        unitService.updateUnit(id, unit);
        return ResponseEntity.ok("Unit updated");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class, HttpMessageNotReadableException.class})
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
