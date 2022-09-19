package ch.opibus.opibus.error.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DBError extends Throwable {

    private Object object;
    private String message;


    public DBError(String name) {

        this.message = "Error trying to " + Thread.currentThread().getStackTrace()[4].getMethodName() + " " + name;
        this.object = object;

    }
}
