/**
 * этот класс представляет координаты конкретной ячейки на карте
 * This class represents a specific location in a 2D map.  Coordinates are
 * integer values.
 **/
public class Location {
    /**
     * X coordinate of this location.
     **/
    public int xCoord;

    /**
     * Y coordinate of this location.
     **/
    public int yCoord;


    /**
     * Creates a new location with the specified integer coordinates.
     **/
    public Location(int x, int y) {
        xCoord = x;
        yCoord = y;
    }

    /**
     * Creates a new location with coordinates (0, 0).
     **/
    public Location() {
        this(0, 0);
    }

    @Override
    public boolean equals(Object ob) {
        if (this == ob) return true;  // если ссылки совпадают, то сравнивать не нужно
        if (ob == null || getClass() != ob.getClass()) return false; // если равен null или классы НЕ совпадают, априори не равны
        Location loc = (Location) ob; // ссылка на объект
        return (loc.xCoord == xCoord && loc.yCoord == yCoord);
    }

    @Override
    public int hashCode() { //  возвращает числовое значение фиксированной длины для любого объекта
        int hash = 7; // простое нечетное число
        hash = 31 * hash + (int) xCoord;
        hash = 31 * hash + (int) yCoord;
        return hash;
    }
}


