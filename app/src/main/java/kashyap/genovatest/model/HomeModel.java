package kashyap.genovatest.model;

public class HomeModel {
    public String name;
    public String sub_heading;
    public String image;
    public String product_id;
    public String category_name;
    public String category_id;
    public String button_text;
    public String brand;
    public String price;
    public String off;
    public String old_price;

    public HomeModel(String name, String sub_heading, String image, String product_id) {
        this.name = name;
        this.sub_heading = sub_heading;
        this.image = image;
        this.product_id = product_id;
    }

    public HomeModel(String image, String category_name, String category_id) {
        this.image = image;
        this.category_name = category_name;
        this.category_id = category_id;
    }

    public HomeModel(String name, String sub_heading, String image, String product_id, String button_text) {
        this.name = name;
        this.sub_heading = sub_heading;
        this.image = image;
        this.product_id = product_id;
        this.button_text = button_text;
    }

    public HomeModel(String name, String image, String product_id, String brand, String price, String off, String old_price) {
        this.name = name;
        this.image = image;
        this.product_id = product_id;
        this.brand = brand;
        this.price = price;
        this.off = off;
        this.old_price = old_price;
    }
}
