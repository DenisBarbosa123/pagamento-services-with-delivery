package br.inatel.dm112.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MailRequestData {
    private String from;
    private String password;
    private String to;
    private byte[] content;
}
