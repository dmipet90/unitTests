/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany;

import java.util.HashMap;
import java.util.Map;
import static org.fest.assertions.api.Assertions.*;
import org.junit.Test;
/**
 *
 * @author user
 */
public class HashMapTest {
    @Test
    public void defaultConstructorShouldCreateEmptyMap() {
        Map<String, String> map = new HashMap<>();
        
        assertThat(map).isEmpty();
        assertThat(map).hasSize(0).doesNotContainKey("x");
        assertThat(map.entrySet().iterator().hasNext()).isFalse();
    }
    
    @Test
    public void collectionConstructorShouldCopyAllContent() {
        Map<String, String> originalMap = new HashMap<>();
        originalMap.put("a", "b");
        originalMap.put("c", "d");
        
        Map<String, String> copiedMap = new HashMap<>(originalMap);
        
        assertThat(copiedMap)
                .describedAs("copie map")
                .hasSize(2)
                .isEqualTo(originalMap)
                .isNotSameAs(originalMap);
        
        originalMap.put("e", "f");
        
        assertThat(copiedMap)
                .hasSize(2)
                .doesNotContain(entry("e", "f"))
                .isNotEqualTo(originalMap);
    }
}
