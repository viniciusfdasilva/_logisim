/* Copyright (c) 2010, Carl Burch. License information is located in the
 * com.cburch.logisim.Main source code and at www.cburch.com/logisim/. */

package com.cburch.logisim.gui.log;

import java.awt.BorderLayout;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

class ScrollPanel extends LogPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5413692326374455650L;
	private TablePanel table;

	public ScrollPanel(LogFrame frame) {
		super(frame);
		this.table = new TablePanel(frame);
		JScrollPane pane = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pane.setVerticalScrollBar(table.getVerticalScrollBar());
		setLayout(new BorderLayout());
		add(pane);
	}

	@Override
	public String getHelpText() {
		return table.getHelpText();
	}

	@Override
	public String getTitle() {
		return table.getTitle();
	}

	@Override
	public void localeChanged() {
		table.localeChanged();
	}

	@Override
	public void modelChanged(Model oldModel, Model newModel) {
		table.modelChanged(oldModel, newModel);
	}
}
