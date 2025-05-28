package org.jedi_bachelor.view;

import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

abstract public class View extends Stage {
    // Поле для ограничения кол-ва окон (Singleton)
    protected static Map<Class<?>, Integer> windowCountMap = new HashMap<>();

    public View() {
        Class<?> currentClass = this.getClass();
        int currentCount = windowCountMap.getOrDefault(currentClass, 0);

        if (currentCount >= 1) {
            throw new IllegalStateException(
                    "Превышен лимит окон для " + currentClass.getSimpleName() +
                            " (максимум " + 1 + ")"
            );
        }

        windowCountMap.put(currentClass, currentCount + 1);
    }
}
