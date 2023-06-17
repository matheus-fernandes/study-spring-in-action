package tacos.email;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Ingredient {
    private final String code;
    private final String name;
}
