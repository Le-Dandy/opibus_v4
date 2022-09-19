package ch.opibus.opibus.error.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Error extends Throwable {

    private String errorClass;
    private String errorMethod;
    private long objectId;
    private String objectName;

    public Error(Object object, long objectId) {

        this.errorClass = Thread.currentThread().getStackTrace()[4].getClassName();
        this.errorMethod = Thread.currentThread().getStackTrace()[4].getMethodName();
        this.objectId = objectId;
        this.objectName = object.getClass().descriptorString();
    }

}
