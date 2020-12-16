package com.cburch.logisim.std.ttl;

import java.awt.Graphics;

import com.cburch.logisim.instance.InstancePainter;
import com.cburch.logisim.instance.InstanceState;

public class Ttl7486 extends AbstractTtlGate {

	public Ttl7486() {
		super("7486", 14);
	}

	@Override
	public void paintInternal(InstancePainter painter, int x, int y, int height, boolean up) {
		Graphics g = painter.getGraphics();
		int portwidth = 18, portheight = 15;
		int youtput = y + (up ? 20 : 40);
		Drawgates.paintXor(g, x + 44, youtput, false);
		// output line
		Drawgates.paintOutputgate(g, x + 50, y, x + 44, youtput, up);
		// input lines
		Drawgates.paintDoubleInputgate(g, x + 30, y, x + 44 - portwidth, youtput, portheight, up);
	}

	@Override
	public void ttlpropagate(InstanceState state) {
		for (int i = 2; i < 12; i += 3) {
			state.setPort(i, state.getPort(i - 1).xor(state.getPort(i - 2)), 1);
		}
	}

}