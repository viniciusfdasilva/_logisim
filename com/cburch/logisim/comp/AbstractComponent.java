/* Copyright (c) 2010, Carl Burch. License information is located in the
 * com.cburch.logisim.Main source code and at www.cburch.com/logisim/. */

package com.cburch.logisim.comp;

import java.awt.Graphics;
import java.util.List;

import com.cburch.logisim.circuit.CircuitState;
import com.cburch.logisim.data.Bounds;
import com.cburch.logisim.data.Location;

public abstract class AbstractComponent implements Component {
	protected AbstractComponent() {
	}

	@Override
	public boolean contains(Location pt) {
		Bounds bds = getBounds();
		if (bds == null)
			return false;
		return bds.contains(pt, 1);
	}

	@Override
	public boolean contains(Location pt, Graphics g) {
		Bounds bds = getBounds(g);
		if (bds == null)
			return false;
		return bds.contains(pt, 1);
	}

	@Override
	public boolean endsAt(Location pt) {
		for (EndData data : getEnds()) {
			if (data.getLocation().equals(pt))
				return true;
		}
		return false;
	}

	@Override
	public abstract Bounds getBounds();

	@Override
	public Bounds getBounds(Graphics g) {
		return getBounds();
	}

	@Override
	public EndData getEnd(int index) {
		return getEnds().get(index);
	}

	//
	// propagation methods
	//
	@Override
	public abstract List<EndData> getEnds();

	//
	// basic information methods
	//
	@Override
	public abstract ComponentFactory getFactory();

	//
	// location/extent methods
	//
	@Override
	public abstract Location getLocation();

	@Override
	public abstract void propagate(CircuitState state);
}
