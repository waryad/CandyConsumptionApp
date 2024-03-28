package ca.sheridancollege.waryad.beans;

import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HalloweenCandyConsumption {
    private Long id;
    private String quantity;
    private String candyBrandname;
    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate beforeDate;
    private final String[] HALLOWEENCATEGORIES = {"crunchy", "chewy", "sweet", "sour"};
    private String category;
}
