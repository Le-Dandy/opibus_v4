package ch.opibus.opibus.error.model;

import ch.opibus.opibus.partner.dao.AppUser;
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
    private int type;


    public DBError(Object object) {

        String methodName = Thread.currentThread().getStackTrace()[4].getMethodName();

        this.message = "Error trying to " + methodName + " " + object.getClass().getSimpleName();
        this.object = object;
        this.type = getType(methodName);

    }

    public DBError(Object object, long id) {

        String methodName = Thread.currentThread().getStackTrace()[4].getMethodName();

        this.message = getMessage(methodName, object, id);
        this.object = object;
        this.type = getType(methodName);
    }

    private String getMessage(String methodName, Object object, long id) {

        if(id < 0) {

            return "Error trying to " + methodName
                    + " " + object.getClass().getSimpleName()
                    + " id: " + id;

        } else {

            return "Error trying to " + methodName
                    + " " + object.getClass().getSimpleName();

        }
    }

    private int getType(String methodName) {

        switch (methodName) {

            case "get": return 1;
            case "save": return 2;
            case "create": return 2;
            case "delete": return 3;
            default: return 0;
        }
    }
}
