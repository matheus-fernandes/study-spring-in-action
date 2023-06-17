package tacos.email;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class Taco {
    private final String tacoName;
    private List<String> ingredients;
}
