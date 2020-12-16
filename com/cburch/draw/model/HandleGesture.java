/* Copyright (c) 2010, Carl Burch. License information is located in the
 * com.cburch.logisim.Main source code and at www.cburch.com/logisim/. */

package com.cburch.draw.model;

import java.awt.event.InputEvent;

public class HandleGesture {
	private Handle handle;
	private int dx;
	private int dy;
	private int modifiersEx;
	private Handle resultingHandle;

	public HandleGesture(Handle handle, int dx, int dy, int modifiersEx) {
		this.handle = handle;
		this.dx = dx;
		this.dy = dy;
		this.modifiersEx = modifiersEx;
	}

	public int getDeltaX() {
		return dx;
	}

	public int getDeltaY() {
		return dy;
	}

	public Handle getHandle() {
		return handle;
	}

	public int getModifiersEx() {
		return modifiersEx;
	}

	public Handle getResultingHandle() {
		return resultingHandle;
	}

	public boolean isAltDown() {
		return (modifiersEx & InputEvent.ALT_DOWN_MASK) != 0;
	}

	public boolean isControlDown() {
		return (modifiersEx & InputEvent.CTRL_DOWN_MASK) != 0;
	}

	public boolean isShiftDown() {
		return (modifiersEx & InputEvent.SHIFT_DOWN_MASK) != 0;
	}

	public void setResultingHandle(Handle value) {
		resultingHandle = value;
	}
}
