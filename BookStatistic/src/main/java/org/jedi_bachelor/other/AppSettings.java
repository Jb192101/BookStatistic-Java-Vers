package org.jedi_bachelor.other;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
public class AppSettings {
    private String language;

    public void setLanguage(String _lan) {
        if(Objects.equals(_lan, "ru") || Objects.equals(_lan, "en")) {
            this.language = _lan;
        }
    }
}