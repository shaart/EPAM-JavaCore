package HexMap.dump;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Collection;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.List;
import HexMap.Hex;

public class HexMap<T> {
    protected Map<Hex, T> map = new HashMap<>();

    public HexMap() {
    }

    public static <T> HexMap<T> initializeBlank(int width, int height) {
        HexMap<T> resultMap = new HexMap<T>();
        for (int column = 0; column < width; column++) {
            for (int row = 0; row < height; row++) {
                resultMap.createHex(new Hex(column, row));
            }
        }

        return resultMap;
    }

    public static <T> HexMap<T> initializeWith(int width, int height, T initObject) {
        HexMap<T> resultMap = new HexMap<T>();
        Hex current;
        for (int column = 0; column < width; column++) {
            for (int row = 0; row < height; row++) {
                current = new Hex(column, row);
                resultMap.createHex(current);
                resultMap.setObject(current, initObject);
            }
        }

        return resultMap;
    }

    public boolean setObject(int column, int row, T object) {
        return setObject(new Hex(column, row), object, false);
    }

    public boolean setObject(Hex coordsHex, T object) {
        return setObject(coordsHex, object, false);
    }

    public boolean setObject(Hex coordsHex, T object, boolean canOverride) {
        if (map.containsKey(coordsHex) && (canOverride || map.get(coordsHex) == null)) {
            map.put(coordsHex, object);
            return true;
        }
        return false;
    }

    public void removeObjectAt(Hex coordsHex) {
        if (map.containsKey(coordsHex)) {
            map.put(coordsHex, null);
        }
    }

    public void removeObject(T object) {
        if (object == null) return;

        if (map.containsValue(object)) {
            map.values().remove(object);
        }
    }

    public void createHex(int column, int row) {
        createHex(new Hex(column, row));
    }

    public void createHex(Hex hex) {
        map.put(hex, null);
    }

    public void createHexWith(int column, int row, T object) {
        createHexWith(new Hex(column, row), object);
    }
    public void createHexWith(Hex hex, T object) {
        map.put(hex, object);
    }

    public void removeHex(Hex coords) {
        map.remove(coords);
    }

    /**
     * Get object from HexMap by Hex
     *
     * @param coordsHex Hex
     * @return Object located at column <b>q</b> a row <b>r</b>
     */
    public T getObject(Hex coordsHex) {
        return map.get(coordsHex);
    }

    /**
     * Get object from HexMap by Hex's coordinates
     *
     * @param column Column
     * @param row    Row
     */
    public T getObject(int column, int row) {
        return getObject(new Hex(column, row));
    }

    public Collection<T> getObjects() {
        return map.values();
    }

    public Set<Hex> getHexGrid() {
        return map.keySet();
    }

    public boolean moveObject(T o, Hex from, Hex to) {
        return moveObject(o, from, to, false);
    }

    public boolean moveObject(T object, Hex from, Hex to, boolean canOverride) {
        if (object == null || from == null || to == null) return false;
        if (map.get(to) != null && !canOverride) return false; // these coords already contains object

        if (object.equals(map.get(from))) {
            map.put(from, null);
            map.put(to, object);
            return true;
        }
        return false;
    }

    public int distance(Hex start, Hex destination) {
        throw new UnsupportedOperationException();
    }

    public List<Hex> path(Hex start, Hex destination) {
        throw new UnsupportedOperationException();
    }

    public boolean save(FileOutputStream savingFile) {
        throw new UnsupportedOperationException();
    }

    /**
     * Loads HexMap from file
     *
     * @return operation result
     */
    public boolean load(FileInputStream loadingFile) {
        throw new UnsupportedOperationException();
    }

}
