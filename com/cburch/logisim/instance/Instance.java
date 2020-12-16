/* Copyright (c) 2010, Carl Burch. License information is located in the
 * com.cburch.logisim.Main source code and at www.cburch.com/logisim/. */

package com.cburch.logisim.instance;

import java.awt.Font;
import java.util.List;

import com.cburch.logisim.circuit.CircuitState;
import com.cburch.logisim.comp.Component;
import com.cburch.logisim.data.Attribute;
import com.cburch.logisim.data.AttributeSet;
import com.cburch.logisim.data.Bounds;
import com.cburch.logisim.data.Location;

public class Instance {
	public static Component getComponentFor(Instance instance) {
		return instance.comp;
	}

	public static Instance getInstanceFor(Component comp) {
		if (comp instanceof InstanceComponent) {
			return ((InstanceComponent) comp).getInstance();
		} else {
			return null;
		}
	}

	private InstanceComponent comp;

	Instance(InstanceComponent comp) {
		this.comp = comp;
	}

	public void addAttributeListener() {
		comp.addAttributeListener(this);
	}

	public void fireInvalidated() {
		comp.fireInvalidated();
	}

	public AttributeSet getAttributeSet() {
		return comp.getAttributeSet();
	}

	public <E> E getAttributeValue(Attribute<E> attr) {
		return comp.getAttributeSet().getValue(attr);
	}

	public Bounds getBounds() {
		return comp.getBounds();
	}

	InstanceComponent getComponent() {
		return comp;
	}

	public InstanceData getData(CircuitState state) {
		return (InstanceData) state.getData(comp);
	}

	public InstanceFactory getFactory() {
		return (InstanceFactory) comp.getFactory();
	}

	public Location getLocation() {
		return comp.getLocation();
	}

	public Location getPortLocation(int index) {
		return comp.getEnd(index).getLocation();
	}

	public List<Port> getPorts() {
		return comp.getPorts();
	}

	public void recomputeBounds() {
		comp.recomputeBounds();
	}

	public void setAttributeReadOnly(Attribute<?> attr, boolean value) {
		comp.getAttributeSet().setReadOnly(attr, value);
	}

	public void setData(CircuitState state, InstanceData data) {
		state.setData(comp, data);
	}

	public void setPorts(Port[] ports) {
		comp.setPorts(ports);
	}

	public void setTextField(Attribute<String> labelAttr, Attribute<Font> fontAttr, int x, int y, int halign,
			int valign) {
		comp.setTextField(labelAttr, fontAttr, x, y, halign, valign);
	}
}
