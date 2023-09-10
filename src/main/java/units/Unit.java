package units;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.hibernate.validator.constraints.*;





    @Getter
    @Setter
    @Document(collection = "unit")
    public class Unit {
        @Id
        private String id;
        @Pattern(regexp = "[a-zA-Z]*", message = "Name can only have letters")
        private String unitName;
        private int quantity;
        private String comment;
    }

