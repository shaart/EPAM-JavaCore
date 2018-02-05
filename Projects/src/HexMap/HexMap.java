package HexMap;

import java.util.Collection;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.List;

public class HexMap<T> {
    protected Map<Hex, T> map = new HashMap<>();

    public HexMap() {
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


}
