package com.epam.solid.nie.config;

public enum Config {

    FILE {
        @Override
        public String getName(){
            return CONFIGURATION_NAME;
        }
    };

    public static final String CONFIGURATION_NAME = "configuration";

    public abstract String getName();
}
