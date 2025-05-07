package edu.utep.group9.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.utep.group9.models.Menu; // make sure this matches your package

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

/** This class loads the menu options as Menu objects from a json file.
 * It returns the objects in a Hashmap.
 */
public class MenuOptionsLoader {

    public HashMap<String, List<Menu>> load() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream input = getClass().getClassLoader().getResourceAsStream("menu_data.json");

            if (input == null) {
                throw new IllegalArgumentException("menu_data.json not found in resources.");
            }

            return mapper.readValue(input, new TypeReference<>() {});
        } catch (Exception e) {
            throw new RuntimeException("Failed to load menu options", e);
        }
    }
}