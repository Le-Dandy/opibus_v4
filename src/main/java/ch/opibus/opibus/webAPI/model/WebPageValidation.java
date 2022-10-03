package ch.opibus.opibus.webAPI.model;

import ch.opibus.opibus.security.dao.ValidationToken;
import ch.opibus.opibus.security.model.ValidationTokenWebTemplateInput;
import ch.opibus.opibus.webAPI.model.template.WebTemplateNavigationBar;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WebPageValidation {

    /* private WebTemplateNavigationBar navBar;

     */
    private ValidationTokenWebTemplateInput page;
}
