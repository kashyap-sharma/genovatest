package kashyap.genovatest.model;

import kashyap.genovatest.cusfo.carous.CarouselModel;

public class SamModel extends CarouselModel {
    public String name;
    public String image;
    public String product_id;
    public String brand;
    public String price;
    public String off;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOff() {
        return off;
    }

    public void setOff(String off) {
        this.off = off;
    }

    public String getOld_price() {
        return old_price;
    }

    public void setOld_price(String old_price) {
        this.old_price = old_price;
    }

    public String old_price;



    public SamModel(String name, String image, String product_id, String brand, String price, String off, String old_price) {
        this.name = name;
        this.image = image;
        this.product_id = product_id;
        this.brand = brand;
        this.price = price;
        this.off = off;
        this.old_price = old_price;
    }
}
