package tacos.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tacos.entity.Taco;
import tacos.props.PaginationProps;
import tacos.repository.TacoRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TacoService {
    private final TacoRepository tacoRepository;
    private final PaginationProps paginationProps;

    public List<Taco> recentTacos(){
        return tacoRepository.findRecent(paginationProps.getTacoPageSize());
    }

    public Optional<Taco> findById(Long id){
        return tacoRepository.findById(id);
    }

    public Taco save(Taco taco){
        return tacoRepository.save(taco);
    }
}
