package ch.opibus.opibus.webAPI.model.template.window;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WwError {

    private String errorType;

    private String errorMsg;

}
