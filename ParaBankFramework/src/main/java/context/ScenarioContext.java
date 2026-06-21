package context;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {
    private final Map<ScenarioContextKey, Object> scenarioData;

    public ScenarioContext() {
        this.scenarioData = new HashMap<>();
    }

    public void set(ScenarioContextKey key, Object value) {
        scenarioData.put(key, value);
    }

    public Object get(ScenarioContextKey key) {
        return scenarioData.get(key);
    }

    public <T> T get(ScenarioContextKey key, Class<T> type) {
        return type.cast(scenarioData.get(key));
    }

    public boolean contains(ScenarioContextKey key) {
        return scenarioData.containsKey(key);
    }

    public void clear() {
        scenarioData.clear();
    }
}
