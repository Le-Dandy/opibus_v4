package ch.opibus.opibus.security.model;

import ch.opibus.opibus.security.dao.ValidationToken;
import ch.opibus.opibus.webAPI.model.template.fields.WtInput;
import ch.opibus.opibus.webAPI.model.template.fields.WtText;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ValidationTokenWebTemplateInput {

    private ValidationToken token;

    private WtText tokenTitle;

    private WtInput tokenInput;
}
