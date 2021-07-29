package br.com.zup.criacaodeproposta.tratamentodeerros;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErroHandler {

    private MessageSource messageSource;
    public ErroHandler(MessageSource messageSource){
        this.messageSource = messageSource;
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<Erro> handler(MethodArgumentNotValidException exception){
        List<Erro> erros = new ArrayList<>();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        for (FieldError e : fieldErrors) {
            String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            Erro erroNovo = new Erro(e.getField(), mensagem);

            erros.add(erroNovo);
        }

        return erros;
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public List<Erro> handlerBinder(BindException exception){
        List<Erro> erros = new ArrayList<>();
       erros.add(new Erro("Erro", exception.getMessage()));

        return erros;
    }
}
