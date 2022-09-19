package ch.opibus.opibus.partner.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ColorSettings {

    DARK("dark"),
    LIGHT("light");

    private final String color;
}
