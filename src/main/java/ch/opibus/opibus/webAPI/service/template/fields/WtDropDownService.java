package ch.opibus.opibus.webAPI.service.template.fields;

import ch.opibus.opibus.error.model.TranslationError;
import ch.opibus.opibus.security.model.SecurityRole;
import ch.opibus.opibus.translation.dao.Translation;
import ch.opibus.opibus.translation.service.TranslationService;
import ch.opibus.opibus.webAPI.model.template.fields.WtDropDown;
import ch.opibus.opibus.webAPI.model.template.fields.WtLink;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class WtDropDownService {

    private final WtLinkService link;

    public WtDropDown get(List<WtLink> dDList, Object object, SecurityRole role, int tabNo, String language) throws TranslationError {

        try{

            List<WtLink> thisList = new ArrayList<>();

            int i;

            for(i =0; i <= dDList.size(); i++) {

                WtLink link = getLine(object, role, tabNo, i +1, language);
                link.setHref(dDList.get(i).getHref());

                thisList.add(link);
            }

            return new WtDropDown(thisList);

        } catch (TranslationError e){

            throw e;

        }

    }

    private WtLink getLine(Object object, SecurityRole role, int tabNo, int dropDownNo, String language) throws TranslationError{

        try {

            return link.get(
                    object.getClass().getSimpleName(),
                    getField(tabNo, dropDownNo),
                    getType(role),
                    language
            );


        } catch (TranslationError error) {

            throw error;

        }

    }

    private String getType(SecurityRole role){

        return WtDropDown.class.getSimpleName() + "_" + role.getUrlPrefix();
    }

    private String getField(int tabNo, int dropDownNo) {

        return "Link_" + tabNo + dropDownNo ;
    }

}
