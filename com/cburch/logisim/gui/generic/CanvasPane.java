/* Copyright (c) 2010, Carl Burch. License information is located in the
 * com.cburch.logisim.Main source code and at www.cburch.com/logisim/. */

package com.cburch.logisim.gui.generic;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import com.cburch.logisim.util.MacCompatibility;

public class CanvasPane extends JScrollPane {
	private class Listener implements ComponentListener, PropertyChangeListener {

		@Override
		public void componentHidden(ComponentEvent e) {
		}

		@Override
		public void componentMoved(ComponentEvent e) {
		}

		//
		// ComponentListener methods
		//
		@Override
		public void componentResized(ComponentEvent e) {
			contents.recomputeSize();
		}

		@Override
		public void componentShown(ComponentEvent e) {
		}

		@Override
		public void propertyChange(PropertyChangeEvent e) {
			String prop = e.getPropertyName();
			if (prop.equals(ZoomModel.ZOOM)) {
				// mouse point
				Point point = getMousePosition(true);
				double oldZoom = ((Double) e.getOldValue()).doubleValue();
				Rectangle r = getViewport().getViewRect();
				double cx = (r.getX() + r.getWidth() / 2) / oldZoom;
				double cy = (r.getY() + r.getHeight() / 2) / oldZoom;

				double newZoom = ((Double) e.getNewValue()).doubleValue();
				contents.recomputeSize();
				r = getViewport().getViewRect();
				if (point != null) {// mouse is pointing something
					int newX = (int) Math
							.round(r.getX() / oldZoom * newZoom + point.getX() / oldZoom * newZoom - point.getX());
					int newY = (int) Math
							.round(r.getY() / oldZoom * newZoom + point.getY() / oldZoom * newZoom - point.getY());
					getHorizontalScrollBar().setValue(newX);
					getVerticalScrollBar().setValue(newY);
				} else {// mouse is outside from canvas panel
					int hv = (int) (cx * newZoom - r.getWidth() / 2);
					int vv = (int) (cy * newZoom - r.getHeight() / 2);
					getHorizontalScrollBar().setValue(hv);
					getVerticalScrollBar().setValue(vv);
				}
			}
		}
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 404622249632842787L;

	private CanvasPaneContents contents;
	private Listener listener;
	private ZoomModel zoomModel;

	public CanvasPane(CanvasPaneContents contents) {
		super((Component) contents);
		this.contents = contents;
		this.listener = new Listener();
		this.zoomModel = null;
		if (MacCompatibility.mrjVersion >= 0.0) {
			setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		}

		addComponentListener(listener);
		contents.setCanvasPane(this);
	}

	public Dimension getViewportSize() {
		Dimension size = new Dimension();
		getViewport().getSize(size);
		return size;
	}

	public double getZoomFactor() {
		ZoomModel model = zoomModel;
		return model == null ? 1.0 : model.getZoomFactor();
	}

	public void setZoomModel(ZoomModel model) {
		ZoomModel oldModel = zoomModel;
		if (oldModel != null) {
			oldModel.removePropertyChangeListener(ZoomModel.ZOOM, listener);
		}
		zoomModel = model;
		if (model != null) {
			model.addPropertyChangeListener(ZoomModel.ZOOM, listener);
		}
	}

	public Dimension supportPreferredSize(int width, int height) {
		double zoom = getZoomFactor();
		if (zoom != 1.0) {
			width = (int) Math.ceil(width * zoom);
			height = (int) Math.ceil(height * zoom);
		}
		Dimension minSize = getViewportSize();
		if (minSize.width > width)
			width = minSize.width;
		if (minSize.height > height)
			height = minSize.height;
		return new Dimension(width, height);
	}

	public int supportScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
		int unit = supportScrollableUnitIncrement(visibleRect, orientation, direction);
		if (direction == SwingConstants.VERTICAL) {
			return visibleRect.height / unit * unit;
		} else {
			return visibleRect.width / unit * unit;
		}
	}

	public int supportScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
		double zoom = getZoomFactor();
		return (int) Math.round(10 * zoom);
	}
}
