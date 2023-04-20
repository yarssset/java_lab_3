import java.util.HashMap;

/**
 * этот класс хранит набор открытых и закрытых вершин, и предоставляет основные операции,
 * необходимые для функционирования алгоритма поиска А*.
 * Этот класс хранит базовое состояние, необходимое алгоритму A* для вычисления
 * пути по карте. Это состояние включает набор «открытых путевых точек» и другой
 * набор «закрытых путевых точек». Кроме того, этот класс предоставляет основные операции,
 * необходимые алгоритму поиска пути A* для выполнения своей обработки.
 **/
public class AStarState
{
    /** Это ссылка на карту, по которой перемещается алгоритм A*. **/
    private Map2D map;
    // очевидный вывод из всего этого заключается в том, что с каждым местоположением
    // на карте может быть связана только одна вершина
    private HashMap<Location, Waypoint> openWaypoint = new HashMap<Location, Waypoint>(); // открытая вершина
    private HashMap<Location, Waypoint> closeWaypoint = new HashMap<Location, Waypoint>(); // закрытая вершина
    // хэш-картa, где местоположение вершин является ключом, а сами вершины являются значениями
    /**
     * Инициализировать новый объект состояния для использования алгоритмом поиска пути A*.
     **/
    public AStarState(Map2D map)
    {
        if (map == null)
            throw new NullPointerException("map cannot be null");

        this.map = map;
    }

    /** Возвращает карту, по которой перемещается навигатор A*. **/
    public Map2D getMap()
    {
        return map;
    }

    /**
     * Этот метод сканирует все открытые путевые точки и возвращает путевую точку
     * с минимальной общей стоимостью. Если открытых путевых точек нет, этот
     * метод возвращает <code>null</code>.
     **/
    public Waypoint getMinOpenWaypoint()
    {
        Waypoint minWaypoint = null; // подходящая точка
        Waypoint temp = null; // вершина
        float minCost = Float.MAX_VALUE; // минимальная стоимость пути
        // находим минимальную цену пути, проходим по открытым точкам
        for (int i = 0; i < openWaypoint.size(); i++) {
            // работаем с values открытой точки
            temp = (Waypoint) openWaypoint.values().toArray()[i]; // присваиваем вершине пути значение открытой вершины на пути
            if (temp.getTotalCost() < minCost) {
                minCost = temp.getTotalCost();
                minWaypoint = temp; // если стоимость минимальна, то вершина - подходящая (мин)
            }
        }
        return minWaypoint;
    }

    /**
     * Этот метод добавляет путевую точку (или потенциально обновляет путевую точку,
     * уже находящуюся в ней) в коллекции «открытых путевых точек».
     *
     * Если в местоположении новой путевой точки еще нет открытой путевой точки,
     * новая путевая точка просто добавляется в коллекцию.
     * Однако, если в местоположении
     * новой путевой точки уже есть путевая точка, новая путевая точка заменяет старую,
     * <em>только если</em> значение «предыдущая стоимость» новой путевой точки меньше,
     * чем значение «предыдущая стоимость» текущей путевой точки.
     **/
    public boolean addOpenWaypoint(Waypoint newWP)
    {
        Waypoint newPoint = openWaypoint.get(newWP.getLocation()); // текущая точка
        // добавляем, если не найдена или заменяем, если её предыдущее значение меньше
        if (newPoint == null || newPoint.getPreviousCost() > newWP.getPreviousCost()) {
            openWaypoint.put(newWP.getLocation(),newWP); // вставляем указанное значение с ключом в карту
            return true;
        }
        return false;
    }


    /** Возвращает текущее количество открытых путевых точек. **/
    public int numOpenWaypoints()
    {
        return openWaypoint.size();
    }


    /**
     * Этот метод перемещает путевую точку в указанном месте
     * из открытого списка в закрытый список.
     **/
    public void closeWaypoint(Location loc)
    {
        Waypoint Point = openWaypoint.get(loc); // точка со значением по ключу loc
        // если точки нет в открытых точках, то ничего не делаем
        if (Point == null) return;
        openWaypoint.remove(loc); // удаление значения по ключу loc
        closeWaypoint.put(loc,Point); // положить в закрытые точки значение point с ключом loc
    }

    /**
     * Возвращает true, если коллекция закрытых путевых точек содержит
     * путевую точку для указанного местоположения.
     **/
    public boolean isLocationClosed(Location loc)
    {
        return closeWaypoint.get(loc) != null; // возвращает значение, которому сопоставлен ключ или null
    }
}
