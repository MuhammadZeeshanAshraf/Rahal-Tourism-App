package com.octaitsolutions.rahal.Model;

import java.io.Serializable;

public class Country  implements Serializable {

    int image;
    int name;

    public Country(int image, int name) {
        this.image = image;
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }
}
