package tacos.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import tacos.model.Ingredient;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcIngredientRepository implements IngredientRepository{

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Ingredient> findAll() {
        return jdbcTemplate.query(
                "select id, name, type from Ingredient",
                (row, rowNum) -> new Ingredient(
                        row.getString("id"),
                        row.getString("name"),
                        Ingredient.Type.valueOf(row.getString("type"))
                )
        );
    }

    @Override
    public Optional<Ingredient> findById(String id) {
        return jdbcTemplate.query(
                "select id, name, type from Ingredient where id=?",
                (row, rowNum) -> new Ingredient(
                        row.getString("id"),
                        row.getString("name"),
                        Ingredient.Type.valueOf(row.getString("type"))
                ),
                id
        ).stream().findFirst();
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        jdbcTemplate.update(
                "insert into Ingredient (id, name, type) values (?, ?, ?)",
                ingredient.getId(),
                ingredient.getName(),
                ingredient.getType().toString()
        );

        return ingredient;
    }
}
