package ch.opibus.opibus.webAPI.model.template.fields;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WtLink {

    private String href;

    private String text;

    private String description;

    public WtLink(String text, String description) {
        this.text = text;
        this.description = description;
    }
}
