package com.example.kadaiapi.exception.handler;
import com.example.kadaiapi.exception.NoteArgumentNotValidException;
import com.example.kadaiapi.exception.NoteNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoteNotFoundException.class)
    public ResponseEntity<String> handleNoteNotFoundException(NoteNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
      //  return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(NoteArgumentNotValidException.class)
    public ResponseEntity<String> handleNoteArgumentNotValid(NoteArgumentNotValidException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
