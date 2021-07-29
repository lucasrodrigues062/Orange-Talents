package br.com.zup.criacaodeproposta.cadastrobiometria;

import br.com.zup.criacaodeproposta.cadastracartao.Cartao;
import br.com.zup.criacaodeproposta.cadastracartao.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/biometria")
public class CadastroBiometriaController {

    @Autowired
    private CartaoRepository cartaoRepository;
    @Autowired
    private BiometriaRepository biometriaRepository;

    @PostMapping("/{idCartao}")
    public ResponseEntity<?> cadastraBiometria(@PathVariable(name = "idCartao") String idCartao, @RequestBody @Valid BiometriaDto request, UriComponentsBuilder builder){

        Optional<Cartao> optional = cartaoRepository.findById(idCartao);
        if(!optional.isPresent()){
            return ResponseEntity.notFound().build();
        }
        Biometria biometria = biometriaRepository.save(request.toModel(optional.get()));
        URI uri = builder.path("/api/biometria/" + idCartao + "/{idBiometria}").buildAndExpand(biometria.getId()).toUri();

        return ResponseEntity.created(uri).body(new BiometriaDto(biometria));
    }

}
