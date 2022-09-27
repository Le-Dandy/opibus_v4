package ch.opibus.opibus.webAPI.model.template.fields;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WebTemplateInput {

    private String text;

    private String value;

    private String placeHolder;

    private String description;


}
