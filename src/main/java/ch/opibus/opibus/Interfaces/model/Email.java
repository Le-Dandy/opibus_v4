package ch.opibus.opibus.Interfaces.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Email {

    private List<String> reciever;

    private List<String> recieverCopy;

    private List<String> blindCopy;

    private String header;

    private String body;


}
