package prodcat.miniprojet.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import prodcat.miniprojet.data.moduls.Category;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private String name;
    private  int qt;
    private  boolean dispo;

    private Category category;
}
