package com.ui.pojos;

public class Environment {
    private String url;

    public int getMAX_NUMBER_OF_ATTEMPTS() {
        return MAX_NUMBER_OF_ATTEMPTS;
    }

    public void setMAX_NUMBER_OF_ATTEMPTS(int MAX_NUMBER_OF_ATTEMPTS) {
        this.MAX_NUMBER_OF_ATTEMPTS = MAX_NUMBER_OF_ATTEMPTS;
    }

    private int MAX_NUMBER_OF_ATTEMPTS;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
