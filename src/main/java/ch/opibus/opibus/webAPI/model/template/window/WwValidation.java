package ch.opibus.opibus.webAPI.model.template.window;

import ch.opibus.opibus.security.model.ValidationTokenWebTemplateInput;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WwValidation {

    /* private WtNavigationBar navBar;

     */
    private ValidationTokenWebTemplateInput page;
}
