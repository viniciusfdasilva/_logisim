/* Copyright (c) 2011, Carl Burch. License information is located in the
 * com.cburch.logisim.Main source code and at www.cburch.com/logisim/. */

package com.cburch.logisim.gui.main;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.TreePath;

import com.cburch.draw.toolbar.Toolbar;
import com.cburch.logisim.circuit.CircuitState;
import com.cburch.logisim.circuit.Simulator;
import com.cburch.logisim.proj.Project;
import com.cburch.logisim.proj.ProjectEvent;
import com.cburch.logisim.proj.ProjectListener;

class SimulationExplorer extends JPanel implements ProjectListener, MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8414281898752383249L;
	private Project project;
	private SimulationTreeModel model;
	private JTree tree;

	SimulationExplorer(Project proj, MenuListener menu) {
		super(new BorderLayout());
		this.project = proj;

		SimulationToolbarModel toolbarModel = new SimulationToolbarModel(proj, menu);
		Toolbar toolbar = new Toolbar(toolbarModel);
		add(toolbar, BorderLayout.NORTH);

		model = new SimulationTreeModel(proj.getSimulator().getCircuitState());
		model.setCurrentView(project.getCircuitState());
		tree = new JTree(model);
		tree.setCellRenderer(new SimulationTreeRenderer());
		tree.addMouseListener(this);
		tree.setToggleClickCount(3);
		add(new JScrollPane(tree), BorderLayout.CENTER);
		proj.addProjectListener(this);
	}

	private void checkForPopup(MouseEvent e) {
		if (e.isPopupTrigger()) {
			; // do nothing
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			TreePath path = tree.getPathForLocation(e.getX(), e.getY());
			if (path != null) {
				Object last = path.getLastPathComponent();
				if (last instanceof SimulationTreeCircuitNode) {
					SimulationTreeCircuitNode node;
					node = (SimulationTreeCircuitNode) last;
					project.setCircuitState(node.getCircuitState());
				}
			}
		}
	}

	//
	// MouseListener methods
	//
	//
	// MouseListener methods
	//
	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		requestFocus();
		checkForPopup(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		checkForPopup(e);
	}

	//
	// ProjectListener methods
	//
	@Override
	public void projectChanged(ProjectEvent event) {
		int action = event.getAction();
		if (action == ProjectEvent.ACTION_SET_STATE) {
			Simulator sim = project.getSimulator();
			CircuitState root = sim.getCircuitState();
			if (model.getRootState() != root) {
				model = new SimulationTreeModel(root);
				tree.setModel(model);
			}
			model.setCurrentView(project.getCircuitState());
			TreePath path = model.mapToPath(project.getCircuitState());
			if (path != null) {
				tree.scrollPathToVisible(path);
			}
		}
	}
}
