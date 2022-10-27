package ch.opibus.opibus.webAPI.model.template.objects;

import ch.opibus.opibus.webAPI.model.template.fields.WtLink;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WtNavigationBar {

    private WtLink navbarBrand;

    private WtNavBarTab tab1;
    private WtNavBarTab tab2;
    private WtNavBarTab tab3;
    private WtNavBarTab tab4;
    private WtNavBarTab tab5;
    private WtNavBarTab tab6;
    private WtNavBarTab tab7;
    private WtNavBarTab tab8;
    private WtNavBarTab tab9;
    private WtNavBarTab tab10;

    private WtLink logoff;
}
