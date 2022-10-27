package ch.opibus.opibus.webAPI.model.template.fields;

import ch.opibus.opibus.security.model.SecurityRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WtDropDown {

    private List<WtLink> dropDown;


}
