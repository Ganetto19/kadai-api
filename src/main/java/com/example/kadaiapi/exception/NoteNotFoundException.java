package com.example.kadaiapi.exception;

public class NoteNotFoundException extends RuntimeException {
    public NoteNotFoundException(long id) {
        super("指定されたIDのノートが見つかりません: " +id);
    }
}
