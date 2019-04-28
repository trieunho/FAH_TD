package com.example.fah.FAHModel.Models;

public class Image {
    private String source;

    public Image() {
    }

    public String getSource() {
        return source;
    }

    @Override
    public String toString() {
        return "Image{" +
                "source='" + source + '\'' +
                '}';
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Image(String source) {
        this.source = source;
    }
}
