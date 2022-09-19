package ch.opibus.opibus.error.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TranslationError extends Throwable{

    private String message;
    private String object;
    private String type;
    private String language;

    public TranslationError(String object, String type, String language) {

        this.message = "Not able to " + Thread.currentThread().getStackTrace()[4].getMethodName() + " " + object + " type " + type + " in language " + language;
        this.object = object;
        this.type = type;
        this.language = language;

    }
}
