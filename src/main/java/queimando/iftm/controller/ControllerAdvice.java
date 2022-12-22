package queimando.iftm.controller;

import java.nio.file.FileAlreadyExistsException;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

  @ExceptionHandler(MaxUploadSizeExceededException.class)
  public String trataTamanhoMaximoExcedido(final Model model, MaxUploadSizeExceededException e) {
    model.addAttribute("message", "Tamanho do arquivo excede o permitido (128KB). Tente novamente.");
    return "error";
  }

  @ExceptionHandler(FileAlreadyExistsException.class)
  public String trataTamanhoMaximoExcedido(final Model model, FileAlreadyExistsException e) {
    model.addAttribute("message", "Arquivo já existe com mesmo nome. Tente novamente.");
    return "error";
  }
}
