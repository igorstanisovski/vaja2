package si.um.feri.models;

import si.um.feri.Util.Util;

import java.math.BigDecimal;
import java.util.UUID;

public class InternalArticle {
    private static UUID uuid;
    private String ID;
    private String name;
    private BigDecimal price;
    private BigDecimal vat;
    private int stock;

    public InternalArticle(UUID uuid,String ID, String name, BigDecimal price, BigDecimal vat, int stock) {
        this.ID = ID;
        this.name = name;
        this.price = price;
        this.vat = vat;
        this.stock = stock;
        this.uuid = uuid;
    }

    public UUID getUuid() {
        if(uuid == null){
            uuid = Util.binaryToUuid(Util.generateBinrayUuid());
        }
        return uuid;
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getVat() {
        return vat;
    }

    public int getStock() {
        return stock;
    }

    public static void setUuid(UUID uuid) {
        InternalArticle.uuid = uuid;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setVat(BigDecimal vat) {
        this.vat = vat;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
