package spring.mqtt.api;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Micheal-Bigmac on 2017/7/13.
 */

/**
 * The context is a key-value store used to pass configuration information
 * throughout the system.
 */
public class Context {

    private Map<String, String> parameters;

    public Context() {
        parameters = Collections.synchronizedMap(new HashMap<String, String>());
    }

    public Context(Map<String, String> paramters) {
        this();
        this.putAll(paramters);
    }

    /**
     * Gets a copy of the backing map structure.
     * @return immutable copy of backing map structure
     */
    public Map<String, String> getParameters() {
        synchronized (parameters) {
            return Collections.unmodifiableMap(parameters);
//            return ImmutableMap.copyOf(parameters);
        }
    }
    /**
     * Removes all of the mappings from this map.
     */
    public void clear() {
        parameters.clear();
    }

    /**
     * Get properties which start with a prefix. When a property is returned,
     * the prefix is removed the from name. For example, if this method is
     * called with a parameter &quot;hdfs.&quot; and the context contains:
     * <code>
     * { hdfs.key = value, otherKey = otherValue }
     * </code>
     * this method will return a map containing:
     * <code>
     * { key = value}
     * </code>
     *
     * <b>Note:</b> The <tt>prefix</tt> must end with a period character. If not
     * this method will raise an IllegalArgumentException.
     *
     * @param prefix key prefix to find and remove from keys in resulting map
     * @return map with keys which matched prefix with prefix removed from
     *   keys in resulting map. If no keys are matched, the returned map is
     *   empty
     */
    public Map<String, String> getSubProperties(String prefix) {
        Map<String, String> result = new HashMap<String,String>();
        synchronized (parameters) {
            for (String key : parameters.keySet()) {
                if (key.startsWith(prefix)) {
                    String name = key.substring(prefix.length());
                    result.put(name, parameters.get(key));
                }
            }
        }
        return Collections.unmodifiableMap(result);
    }
    /**
     * Associates all of the given map's keys and values in the Context.
     */
    public void putAll(Map<String, String> map) {
        parameters.putAll(map);
    }
    /**
     * Associates the specified value with the specified key in this context.
     * If the context previously contained a mapping for the key, the old value
     * is replaced by the specified value.
     * @param key key with which the specified value is to be associated
     * @param value to be associated with the specified key
     */
    public void put(String key, String value) {
        parameters.put(key, value);
    }

    /**
     * Returns true if this Context contains a mapping for key.
     * Otherwise, returns false.
     */
    public boolean containsKey(String key) {
        return parameters.containsKey(key);
    }

    /**
     * Gets value mapped to key, returning defaultValue if unmapped.
     * @param key to be found
     * @param defaultValue returned if key is unmapped
     * @return value associated with key
     */
    public Boolean getBoolean(String key, Boolean defaultValue) {
        String value = get(key);
        if (value != null) {
            return Boolean.parseBoolean(value.trim());
        }
        return defaultValue;
    }
    /**
     * Gets value mapped to key, returning null if unmapped.
     * <p>
     * Note that this method returns an object as opposed to a
     * primitive. The configuration key requested may not be mapped
     * to a value and by returning the primitive object wrapper we can
     * return null. If the key does not exist the return value of
     * this method is assigned directly to a primitive, a
     * {@link NullPointerException} will be thrown.
     * </p>
     * @param key to be found
     * @return value associated with key or null if unmapped
     */
    public Boolean getBoolean(String key) {
        return getBoolean(key, null);
    }

    /**
     * Gets value mapped to key, returning defaultValue if unmapped.
     * @param key to be found
     * @param defaultValue returned if key is unmapped
     * @return value associated with key
     */

    public Integer getInteger(String key, Integer defaultValue) {
        String value = get(key);
        if (value != null) {
            return Integer.parseInt(value.trim());
        }
        return defaultValue;
    }

    public int getInt(String key, int defaultValue) {
        String value = get(key);
        if (value != null) {
            return Integer.parseInt(value.trim());
        }
        return defaultValue;
    }

    public int getInt(String key) {
        String value = get(key);
        if (value != null) {
            return Integer.parseInt(value.trim());
        }
        return -1 ;
    }


    /**
     * Gets value mapped to key, returning null if unmapped.
     * <p>
     * Note that this method returns an object as opposed to a
     * primitive. The configuration key requested may not be mapped
     * to a value and by returning the primitive object wrapper we can
     * return null. If the key does not exist the return value of
     * this method is assigned directly to a primitive, a
     * {@link NullPointerException} will be thrown.
     * </p>
     * @param key to be found
     * @return value associated with key or null if unmapped
     */
    public Integer getInteger(String key) {
        return getInteger(key, null);
    }
    /**
     * Gets value mapped to key, returning defaultValue if unmapped.
     * @param key to be found
     * @param defaultValue returned if key is unmapped
     * @return value associated with key
     */
    public Long getLong(String key, Long defaultValue) {
        String value = get(key);
        if (value != null) {
            return Long.parseLong(value.trim());
        }
        return defaultValue;
    }
    /**
     * Gets value mapped to key, returning null if unmapped.
     * <p>
     * Note that this method returns an object as opposed to a
     * primitive. The configuration key requested may not be mapped
     * to a value and by returning the primitive object wrapper we can
     * return null. If the key does not exist the return value of
     * this method is assigned directly to a primitive, a
     * {@link NullPointerException} will be thrown.
     * </p>
     * @param key to be found
     * @return value associated with key or null if unmapped
     */
    public Long getLong(String key) {
        return getLong(key, null);
    }
    /**
     * Gets value mapped to key, returning defaultValue if unmapped.
     * @param key to be found
     * @param defaultValue returned if key is unmapped
     * @return value associated with key
     */
    public String getString(String key, String defaultValue) {
        return get(key, defaultValue);
    }
    /**
     * Gets value mapped to key, returning null if unmapped.
     * @param key to be found
     * @return value associated with key or null if unmapped
     */
    public String getString(String key) {
        return get(key);
    }
    private String get(String key, String defaultValue) {
        String result = parameters.get(key);
        if (result != null) {
            return result;
        }
        return defaultValue;
    }
    private String get(String key) {
        return get(key, null);
    }
    @Override
    public String toString() {
        return "{ parameters:" + parameters + " }";
    }
}
