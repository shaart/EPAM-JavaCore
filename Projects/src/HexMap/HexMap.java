package HexMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HexMap {
	protected final static String BLANK = "0";
	protected final static String NO_HEX = "_";

	protected Map<Hex, Object> map = new HashMap<Hex, Object>();

	public HexMap() {
	}

	public void setObject(Hex h, Object o) {
		if (!map.containsKey(h) || BLANK.equals(map.get(h))) {
			map.put(h, o);
		}
	}

	public void removeObject(Hex h) {
		if (map.containsKey(h)) {
			map.put(h, BLANK);
		}
	}

	public void removeHex(Hex h) {
		map.put(h, NO_HEX);
	}

	public void setObject(int q, int r, Object o) {
		setObject(new Hex(q, r), o);
	}

	/**
	 * Get object from HexMap by Hex
	 * 
	 * @param h
	 *            Hex
	 * @return Object located at column <b>q</b> a row <b>r</b>
	 */
	public Object getObject(Hex h) {
		return map.get(h);
	}

	public Set<Hex> getGrid() {
		return map.keySet();
	}

	/**
	 * Get object from HexMap by Hex's coordinates
	 * 
	 * @param q
	 *            Column
	 * @param r
	 *            Row
	 */
	public Object getObject(int q, int r) {
		return getObject(new Hex(q, r));
	}

}
