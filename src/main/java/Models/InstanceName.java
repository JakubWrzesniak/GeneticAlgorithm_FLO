package Models;

import java.awt.*;

public enum InstanceName {
    easy(new Dimension(3,3), 9),
    flat(new Dimension(1,12), 12),
    hard(new Dimension(5,6), 24);

    final Dimension dimension;
    final int machines;

    InstanceName(Dimension dimension, int machines) {
        this.dimension = dimension;
        this.machines = machines;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public int getMachines() {
        return machines;
    }

    @Override
    public String toString() {
        return "InstanceName{" +
                "name=" + name() +
                ", dimension=" + dimension +
                ", machines=" + machines +
                '}';
    }
}
