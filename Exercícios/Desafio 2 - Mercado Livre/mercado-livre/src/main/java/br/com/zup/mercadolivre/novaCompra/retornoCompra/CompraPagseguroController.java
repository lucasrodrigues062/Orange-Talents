package br.com.zup.mercadolivre.novaCompra.retornoCompra;

import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.mercadolivre.novaCompra.Compra;
import br.com.zup.mercadolivre.novaCompra.CompraRepository;
import br.com.zup.mercadolivre.novaCompra.StatusCompra;
import br.com.zup.mercadolivre.pergunta.Emails;

@RestController
@RequestMapping("/api/compra/retorno/pagseguro")
public class CompraPagseguroController {

    @Autowired
    private CompraRepository compraRepository;
    @Autowired
    private PagamentoRepository pagamentoRepository;
    @Autowired
    private Emails emails;
    @Autowired
    private NotaFiscal notaFiscal;
    @Autowired
    private Ranking ranking;


    @PostMapping("/{idCompra}")
    @Transactional
    public String confirmaPagamento(@PathVariable(name = "idCompra") Long idCompra, @RequestBody @Valid PagamentoPagSeguroDto pagamentoDto) throws BindException{
        Optional<Compra> optional = compraRepository.findById(idCompra);
        if(optional.isPresent()){
            Compra compra = optional.get();
            if(compra.getStatusCompra().equals(StatusCompra.FINALIZADA)){
                BindException compraFinalizada = new BindException(pagamentoDto, "PagamentoPagSeguroDto");
                compraFinalizada.reject("2", "Compra ja se encontra FINALIZADA");
                throw compraFinalizada;
            }
            Pagamento pagamento = pagamentoRepository.save(pagamentoDto.toPagamento(compra));
            boolean statusCompra = compra.atualizaStatus(pagamento);
            if(statusCompra){
                notaFiscal.gerar(compra);
                ranking.atualizar(compra);
                emails.pagamentoRealizado(compra);
            } else {
                emails.pagamentoNaoRealizado(compra);
            }
            
            return pagamento.toString();
        } else{
            return pagamentoDto.toString();
        }
        
        
    }
    
}
