package com.TextUML.SyntaxRecognition;

import java.util.List;

public class UMLPattern {

    Class<?>[] main_pattern;
    List<Class<?>[]> variation_patterns;

    public UMLPattern(Class<?>[] main_pattern){
        this.main_pattern = main_pattern;
    }

    public UMLPattern(Class<?>[] main_pattern, List<Class<?>[]> variation_patterns){
        this.main_pattern = main_pattern;
        this.variation_patterns = variation_patterns;
    }

    public void AddVariationPattern(Class<?>[] pattern){
        variation_patterns.add(pattern);
    }

}
