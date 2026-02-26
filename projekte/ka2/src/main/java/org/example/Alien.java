package org.example;


import java.util.List;

public record Alien(V2 pos) {
    public boolean collidesWith(List<Rocket> rockets){
        for (var rocket : rockets) {
            if (this.pos.equals(rocket.pos())) return true;
        }
        return false;
    }



}
