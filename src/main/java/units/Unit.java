package units;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



    @Getter
    @Setter
    @Document(collection = "unit")
    public class Unit {

        @Id
        private String id;

        @Pattern(regexp = "[a-zA-Z0-9]*", message = "Name can only be alphanumeric.")
        @NotBlank(message = "Name cannot be blank")
        @NotNull(message = "Name is required")
        private String unitName;

        private int quantity;
    }

