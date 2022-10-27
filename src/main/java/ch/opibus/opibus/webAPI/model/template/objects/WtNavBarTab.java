package ch.opibus.opibus.webAPI.model.template.objects;

import ch.opibus.opibus.webAPI.model.template.fields.WtDropDown;
import ch.opibus.opibus.webAPI.model.template.fields.WtLink;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WtNavBarTab {

    private boolean active;
    private WtLink title;
    private WtDropDown dropDown;

    public String getTitle(boolean translate) {

        return getMethodName(translate);

    }

    public String getDropDown(boolean translate) {

        return getMethodName(translate);

    }

    private String getMethodName(boolean get) {

        if(get = true){
            return Thread.currentThread().getStackTrace()[2].getMethodName().substring(3);
        } else {
            return null;}


    }
}
