package edu.hitsz.bullet;

import edu.hitsz.aircraft.AbstractAircraft;

import java.util.LinkedList;
import java.util.List;

public interface ShootStrategy {
    public List<BaseBullet> executeShooting(AbstractAircraft aircraft);
}
