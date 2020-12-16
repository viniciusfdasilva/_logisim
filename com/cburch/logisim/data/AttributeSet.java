/* Copyright (c) 2010, Carl Burch. License information is located in the
 * com.cburch.logisim.Main source code and at www.cburch.com/logisim/. */

package com.cburch.logisim.data;

import java.util.List;

public interface AttributeSet {
	public void addAttributeListener(AttributeListener l);

	public Object clone();

	public boolean containsAttribute(Attribute<?> attr);

	public Attribute<?> getAttribute(String name);

	public List<Attribute<?>> getAttributes();

	public <V> V getValue(Attribute<V> attr);

	public boolean isReadOnly(Attribute<?> attr);

	public boolean isToSave(Attribute<?> attr);

	public void removeAttributeListener(AttributeListener l);

	public void setReadOnly(Attribute<?> attr, boolean value); // optional

	public <V> void setValue(Attribute<V> attr, V value);
}
