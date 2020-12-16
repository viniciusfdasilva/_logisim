/* Copyright (c) 2010, Carl Burch. License information is located in the
 * com.cburch.logisim.Main source code and at www.cburch.com/logisim/. */

package com.cburch.logisim.util;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.WindowConstants;

public abstract class WindowMenuItemManager {
	private class MyListener implements WindowListener {
		@Override
		public void windowActivated(WindowEvent event) {
			addToManager();
			WindowMenuManager.setCurrentManager(WindowMenuItemManager.this);
		}

		@Override
		public void windowClosed(WindowEvent event) {
			removeFromManager();
		}

		@Override
		public void windowClosing(WindowEvent event) {
			JFrame frame = getJFrame(false);
			if (frame.getDefaultCloseOperation() == WindowConstants.HIDE_ON_CLOSE) {
				removeFromManager();
			}
		}

		@Override
		public void windowDeactivated(WindowEvent event) {
			WindowMenuManager.unsetCurrentManager(WindowMenuItemManager.this);
		}

		@Override
		public void windowDeiconified(WindowEvent event) {
		}

		@Override
		public void windowIconified(WindowEvent event) {
			addToManager();
			WindowMenuManager.setCurrentManager(WindowMenuItemManager.this);
		}

		@Override
		public void windowOpened(WindowEvent event) {
		}
	}

	private MyListener myListener = new MyListener();
	private String text;
	private boolean persistent;
	private boolean listenerAdded = false;
	private boolean inManager = false;
	private HashMap<WindowMenu, JRadioButtonMenuItem> menuItems = new HashMap<WindowMenu, JRadioButtonMenuItem>();

	public WindowMenuItemManager(String text, boolean persistent) {
		this.text = text;
		this.persistent = persistent;
		if (persistent) {
			WindowMenuManager.addManager(this);
		}
	}

	private void addToManager() {
		if (!persistent && !inManager) {
			WindowMenuManager.addManager(this);
			inManager = true;
		}
	}

	void createMenuItem(WindowMenu menu) {
		WindowMenuItem ret = new WindowMenuItem(this);
		menuItems.put(menu, ret);
		menu.addMenuItem(this, ret, persistent);
	}

	public void frameClosed(JFrame frame) {
		if (!persistent) {
			if (listenerAdded) {
				frame.removeWindowListener(myListener);
				listenerAdded = false;
			}
			removeFromManager();
		}
	}

	public void frameOpened(JFrame frame) {
		if (!listenerAdded) {
			frame.addWindowListener(myListener);
			listenerAdded = true;
		}
		addToManager();
		WindowMenuManager.setCurrentManager(this);
	}

	public abstract JFrame getJFrame(boolean create);

	JRadioButtonMenuItem getMenuItem(WindowMenu key) {
		return menuItems.get(key);
	}

	public String getText() {
		return text;
	}

	private void removeFromManager() {
		if (!persistent && inManager) {
			inManager = false;
			for (WindowMenu menu : WindowMenuManager.getMenus()) {
				JRadioButtonMenuItem menuItem = menuItems.get(menu);
				menu.removeMenuItem(this, menuItem);
			}
			WindowMenuManager.removeManager(this);
		}
	}

	void removeMenuItem(WindowMenu menu) {
		JRadioButtonMenuItem item = menuItems.remove(menu);
		if (item != null)
			menu.removeMenuItem(this, item);
	}

	void setSelected(boolean selected) {
		for (JRadioButtonMenuItem item : menuItems.values()) {
			item.setSelected(selected);
		}
	}

	public void setText(String value) {
		text = value;
		for (JRadioButtonMenuItem menuItem : menuItems.values()) {
			menuItem.setText(text);
		}
	}
}
