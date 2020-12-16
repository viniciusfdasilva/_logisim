/* Copyright (c) 2010, Carl Burch. License information is located in the
 * com.cburch.logisim.Main source code and at www.cburch.com/logisim/. */

package com.cburch.logisim.analyze.gui;

import java.awt.event.ItemListener;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.cburch.logisim.analyze.model.AnalyzerModel;
import com.cburch.logisim.analyze.model.VariableList;
import com.cburch.logisim.analyze.model.VariableListEvent;
import com.cburch.logisim.analyze.model.VariableListListener;

class OutputSelector {
	private class Model extends AbstractListModel<Object> implements ComboBoxModel<Object>, VariableListListener {
		/**
		 * 
		 */
		private static final long serialVersionUID = -2649106261241267059L;
		private Object selected;

		@Override
		public Object getElementAt(int index) {
			return source.get(index);
		}

		@Override
		public Object getSelectedItem() {
			return selected;
		}

		@Override
		public int getSize() {
			return source.size();
		}

		@Override
		public void listChanged(VariableListEvent event) {
			int index;
			String variable;
			Object selection;
			switch (event.getType()) {
			case VariableListEvent.ALL_REPLACED:
				computePrototypeValue();
				fireContentsChanged(this, 0, getSize());
				if (source.isEmpty()) {
					select.setSelectedItem(null);
				} else {
					select.setSelectedItem(source.get(0));
				}
				break;
			case VariableListEvent.ADD:
				variable = event.getVariable();
				if (prototypeValue == null || variable.length() > prototypeValue.length()) {
					computePrototypeValue();
				}

				index = source.indexOf(variable);
				fireIntervalAdded(this, index, index);
				if (select.getSelectedItem() == null) {
					select.setSelectedItem(variable);
				}
				break;
			case VariableListEvent.REMOVE:
				variable = event.getVariable();
				if (variable.equals(prototypeValue))
					computePrototypeValue();
				index = ((Integer) event.getData()).intValue();
				fireIntervalRemoved(this, index, index);
				selection = select.getSelectedItem();
				if (selection != null && selection.equals(variable)) {
					selection = source.isEmpty() ? null : source.get(0);
					select.setSelectedItem(selection);
				}
				break;
			case VariableListEvent.MOVE:
				fireContentsChanged(this, 0, getSize());
				break;
			case VariableListEvent.REPLACE:
				variable = event.getVariable();
				if (variable.equals(prototypeValue))
					computePrototypeValue();
				index = ((Integer) event.getData()).intValue();
				fireContentsChanged(this, index, index);
				selection = select.getSelectedItem();
				if (selection != null && selection.equals(variable)) {
					select.setSelectedItem(event.getSource().get(index));
				}
				break;
			}
		}

		@Override
		public void setSelectedItem(Object value) {
			selected = value;
		}
	}

	private VariableList source;
	private JLabel label = new JLabel();
	private JComboBox<Object> select = new JComboBox<Object>();
	private String prototypeValue = null;

	public OutputSelector(AnalyzerModel model) {
		this.source = model.getOutputs();

		Model listModel = new Model();
		select.setModel(listModel);
		source.addVariableListListener(listModel);
	}

	public void addItemListener(ItemListener l) {
		select.addItemListener(l);
	}

	private void computePrototypeValue() {
		String newValue;
		if (source.isEmpty()) {
			newValue = "xx";
		} else {
			newValue = "xx";
			for (int i = 0, n = source.size(); i < n; i++) {
				String candidate = source.get(i);
				if (candidate.length() > newValue.length())
					newValue = candidate;
			}
		}
		if (prototypeValue == null || newValue.length() != prototypeValue.length()) {
			prototypeValue = newValue;
			select.setPrototypeDisplayValue(prototypeValue + "xx");
			select.revalidate();
		}
	}

	public JPanel createPanel() {
		JPanel ret = new JPanel();
		ret.add(label);
		ret.add(select);
		return ret;
	}

	public JComboBox<Object> getComboBox() {
		return select;
	}

	public JLabel getLabel() {
		return label;
	}

	public String getSelectedOutput() {
		String value = (String) select.getSelectedItem();
		if (value != null && !source.contains(value)) {
			if (source.isEmpty()) {
				value = null;
			} else {
				value = source.get(0);
			}
			select.setSelectedItem(value);
		}
		return value;
	}

	void localeChanged() {
		label.setText(Strings.get("outputSelectLabel"));
	}

	public void removeItemListener(ItemListener l) {
		select.removeItemListener(l);
	}
}
