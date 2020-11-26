package ru.itmo.lab4.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Dot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double x;
    private Double r;
    private Double y;
    private String result;
    private String color;



    public Dot() {
    }

    public Dot(Double x, Double y, Double r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public Dot(Double x, Double y, Double r, String result, String color) {
        this(x, y, r);
        this.result = result;
        this.color = color;
    }


    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) return true;
        if (otherObject == null) return false;
        if (getClass() != otherObject.getClass()) return false;
        Dot other = (Dot) otherObject;
        return Objects.equals(x, other.getX())
                && Objects.equals(y, other.getY())
                && Objects.equals(r, other.getR())
                && Objects.equals(result, other.getResult())
                && Objects.equals(color, other.getColor());
    }


    @Override
    public int hashCode() {
        return Objects.hash(x, y, r, result);
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getR() {
        return r;
    }

    public void setR(Double r) {
        this.r = r;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setColor(String color){
        this.color = color;
    }

    public String getColor(){
        return color;
    }

    public void setXYR(Double x, Double y, Double r)
    {
        this.x=x;
        this.y=y;
        this.r=r;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
