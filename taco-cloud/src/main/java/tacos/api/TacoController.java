package tacos.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tacos.entity.Taco;
import tacos.service.TacoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/api/tacos", produces = "application/json")
@CrossOrigin(origins = {"http://localhost:8442/api/tacos", "http://tacocloud.com"})
@RequiredArgsConstructor
public class TacoController {
    private final TacoService tacoService;

    @GetMapping(params = "recent")
    public List<Taco> recentTacos(){
        return tacoService.recentTacos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Taco> tacoById(@PathVariable("id") Long id){
        Optional<Taco> taco = tacoService.findById(id);

        if(taco.isPresent()){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(taco.get());
        }

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(null);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Taco postTaco(@RequestBody Taco taco){
        return tacoService.save(taco);
    }


}
