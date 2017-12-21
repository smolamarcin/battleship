package com.epam.solid.nie.config;

public enum ConfigurationE {

    CONFIGURATION {
        @Override
        public String getName(){
            return "configuration";
        }
    };

    public abstract String getName();
}
