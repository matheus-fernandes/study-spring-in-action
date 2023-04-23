package tacos.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("ingredients")
public class Ingredient{
    @PrimaryKey
    private String id;
    @Column
    private String name;
    @Column
    private Type type;

    public enum Type {
        WRAP, PROTEIN, VEGGIE, CHEESE, SAUCE
    }
}
