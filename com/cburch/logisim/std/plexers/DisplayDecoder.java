/* Copyright (c) 2010, Carl Burch. License information is located in the
 * com.cburch.logisim.Main source code and at www.cburch.com/logisim/. */

package com.cburch.logisim.std.plexers;

import java.awt.Color;
import java.awt.Graphics;

import com.cburch.logisim.data.Attribute;
import com.cburch.logisim.data.AttributeSet;
import com.cburch.logisim.data.Attributes;
import com.cburch.logisim.data.Bounds;
import com.cburch.logisim.data.Direction;
import com.cburch.logisim.data.Value;
import com.cburch.logisim.instance.Instance;
import com.cburch.logisim.instance.InstanceFactory;
import com.cburch.logisim.instance.InstancePainter;
import com.cburch.logisim.instance.InstanceState;
import com.cburch.logisim.instance.Port;
import com.cburch.logisim.instance.StdAttr;
import com.cburch.logisim.util.GraphicsUtil;

public class DisplayDecoder extends InstanceFactory {

	private static final Attribute<Boolean> MULTI_BIT = Attributes.forBoolean("multibit", Strings.getter("ioMultiBit"));

	public static void ComputeDisplayDecoderOutputs(InstanceState state, int inputvalue, int aPortIndex, int bPortIndex,
			int cPortIndex, int dPortIndex, int ePortIndex, int fPortIndex, int gPortIndex, int LTPortIndex,
			int BIPortIndex, int RBIPortIndex) {
		if (state.getPort(BIPortIndex) == Value.FALSE)
			inputvalue = 16;
		else if (state.getPort(LTPortIndex) == Value.FALSE)
			inputvalue = 8;
		else if (state.getPort(RBIPortIndex) == Value.FALSE && inputvalue == 0)
			inputvalue = 16;
		switch (inputvalue) {
		case 0:
			state.setPort(aPortIndex, Value.TRUE, Plexers.DELAY);
			state.setPort(bPortIndex, Value.TRUE, Plexers.DELAY);
			state.setPort(cPortIndex, Value.TRUE, Plexers.DELAY);
			state.setPort(dPortIndex, Value.TRUE, Plexers.DELAY);
			state.setPort(ePortIndex, Value.TRUE, Plexers.DELAY);
			state.setPort(fPortIndex, Value.TRUE, Plexers.DELAY);
			state.setPort(gPortIndex, Value.FALSE, Plexers.DELAY);
			break;
		case 1:
			state.setPort(aPortIndex, Value.FALSE, Plexers.DELAY);
			state.setPort(bPortIndex, Value.TRUE, Plexers.DELAY);
			state.setPort(cPortIndex, Value.TRUE, Plexers.DELAY);
			state.setPort(dPortIndex, Value.FALSE, Plexers.DELAY);
			state.setPort(ePortIndex, Value.FALSE, Plexers.DELAY);
			state.setPort(fPortIndex, Value.FALSE, Plexers.DELAY);
			state.setPort(gPortIndex, Value.FALSE, Plexers.DELAY);
			break;
		case 2:
			state.setPort(aPortIndex, Value.TRUE, Plexers.DELAY);
			state.setPort(bPortIndex, Value.TRUE, Plexers.DELAY);
			state.setPort(cPortIndex, Value.FALSE, Plexers.DELAY);
			state.setPort(dPortIndex, Value.TRUE, Plexers.DELAY);
			state.setPort(ePortIndex, Value.TRUE, Plexers.DELAY);
			state.setPort(fPortIndex, Value.FALSE, Plexers.DELAY);
			state.setPort(gPortIndex, Value.TRUE, Plexers.DELAY);
			break;
		case 3:
			state.setPort(aPortIndex, Value.TRUE, Plexers.DELAY);
			state.setPort(bPortIndex, Value.TRUE, Plexers.DELAY);
			state.setPort(cPortIndex, Value.TRUE, Plexers.DELAY);
			state.setPort(dPortIndex, Value.TRUE, Plexers.DELAY);
			state.setPort(ePortIndex, Value.FALSE, Plexers.DELAY);
			state.setPort(fPortIndex, Value.FALSE, Plexers.DELAY);
			state.setPort(gPortIndex, Value.TRUE, Plexers.DELAY);
			break;
		case 4:
			state.setPort(aPortIndex, Value.FALSE, Plexers.DELAY);
			state.setPort(bPortIndex, Value.TRUE, Plexers.DELAY);
			state.setPort(cPortIndex, Value.TRUE, Plexers.DELAY);
			state.setPort(dPortIndex, Value.FALSE, Plexers.DELAY);
			state.setPort(ePortIndex, Value.FALSE, Plexers.DELAY);
			state.setPort(fPortIndex, Value.TRUE, Plexers.DELAY);
			state.setPort(gPortIndex, Value.TRUE, Plexers.DELAY);
			break;
		case 5:
			state.setPort(aPortIndex, Value.TRUE, Plexers.DELAY);
			state.setPort(bPortIndex, Value.FALSE, Plexers.DELAY);
			state.setPort(cPortIndex, Value.TRUE, Plexers.DELAY);
			state.setPort(dPortIndex, Value.TRUE, Plexers.DELAY);
			state.setPort(ePortIndex, Value.FALSE, Plexers.DELAY);
			state.setPort(fPortIndex, Value.TRUE, Plexers.DELAY);
			state.setPort(gPortIndex, Value.TRUE, Plexers.DELAY);
			break;
		case 6:
			state.setPort(aPortIndex, Value.TRUE, Plexers.DELAY);
			state.setPort(bPortIndex, Value.FALSE, Plexers.DELAY);
			state.setPort(cPortIndex, Value.TRUE, Plexers.DELAY);
			state.setPort(dPortIndex, Value.TRUE, Plexers.DELAY);
			state.setPort(ePortIndex, Value.TRUE, Plexers.DELAY);
			state.setPort(fPortIndex, Value.TRUE, Plexers.DELAY);
			state.setPort(gPortIndex, Value.TRUE, Plexers.DELAY);
			break;
		case 7:
			state.setPort(aPortIndex, Value.TRUE, Plexers.DELAY);
			state.setPort(bPortIndex, Value.TRUE, Plexers.DELAY);
			state.setPort(cPortIndex, Value.TRUE, Plexers.DELAY);
			state.setPort(dPortIndex, Value.FALSE, Plexers.DELAY);
			state.setPort(ePortIndex, Value.FALSE, Plexers.DELAY);
			state.setPort(fPortIndex, Value.FALSE, Plexers.DELAY);
			state.setPort(gPortIndex, Value.FALSE, Plexers.DELAY);
			break;
		case 8:
			state.setPort(aPortIndex, Value.TRUE, Plexers.DELAY);
			state.setPort(bPortIndex, Value.TRUE, Plexers.DELAY);
			state.setPort(cPortIndex, Value.TRUE, Plexers.DELAY);
			state.setPort(dPortIndex, Value.TRUE, Plexers.DELAY);
			state.setPort(ePortIndex, Value.TRUE, Plexers.DELAY);
			state.setPort(fPortIndex, Value.TRUE, Plexers.DELAY);
			state.setPort(gPortIndex, Value.TRUE, Plexers.DELAY);
			break;
		case 9:
			state.setPort(aPortIndex, Value.TRUE, Plexers.DELAY);
			state.setPort(bPortIndex, Value.TRUE, Plexers.DELAY);
			state.setPort(cPortIndex, Value.TRUE, Plexers.DELAY);
			state.setPort(dPortIndex, Value.FALSE, Plexers.DELAY);
			state.setPort(ePortIndex, Value.FALSE, Plexers.DELAY);
			state.setPort(fPortIndex, Value.TRUE, Plexers.DELAY);
			state.setPort(gPortIndex, Value.TRUE, Plexers.DELAY);
			break;
		case 10:// a
			state.setPort(aPortIndex, Value.TRUE, Plexers.DELAY);
			state.setPort(bPortIndex, Value.TRUE, Plexers.DELAY);
			state.setPort(cPortIndex, Value.TRUE, Plexers.DELAY);
			state.setPort(dPortIndex, Value.FALSE, Plexers.DELAY);
			state.setPort(ePortIndex, Value.TRUE, Plexers.DELAY);
			state.setPort(fPortIndex, Value.TRUE, Plexers.DELAY);
			state.setPort(gPortIndex, Value.TRUE, Plexers.DELAY);
			break;
		case 11:// b
			state.setPort(aPortIndex, Value.FALSE, Plexers.DELAY);
			state.setPort(bPortIndex, Value.FALSE, Plexers.DELAY);
			state.setPort(cPortIndex, Value.TRUE, Plexers.DELAY);
			state.setPort(dPortIndex, Value.TRUE, Plexers.DELAY);
			state.setPort(ePortIndex, Value.TRUE, Plexers.DELAY);
			state.setPort(fPortIndex, Value.TRUE, Plexers.DELAY);
			state.setPort(gPortIndex, Value.TRUE, Plexers.DELAY);
			break;
		case 12:// c
			state.setPort(aPortIndex, Value.TRUE, Plexers.DELAY);
			state.setPort(bPortIndex, Value.FALSE, Plexers.DELAY);
			state.setPort(cPortIndex, Value.FALSE, Plexers.DELAY);
			state.setPort(dPortIndex, Value.TRUE, Plexers.DELAY);
			state.setPort(ePortIndex, Value.TRUE, Plexers.DELAY);
			state.setPort(fPortIndex, Value.TRUE, Plexers.DELAY);
			state.setPort(gPortIndex, Value.FALSE, Plexers.DELAY);
			break;
		case 13:// d
			state.setPort(aPortIndex, Value.FALSE, Plexers.DELAY);
			state.setPort(bPortIndex, Value.TRUE, Plexers.DELAY);
			state.setPort(cPortIndex, Value.TRUE, Plexers.DELAY);
			state.setPort(dPortIndex, Value.TRUE, Plexers.DELAY);
			state.setPort(ePortIndex, Value.TRUE, Plexers.DELAY);
			state.setPort(fPortIndex, Value.FALSE, Plexers.DELAY);
			state.setPort(gPortIndex, Value.TRUE, Plexers.DELAY);
			break;
		case 14:// e
			state.setPort(aPortIndex, Value.TRUE, Plexers.DELAY);
			state.setPort(bPortIndex, Value.FALSE, Plexers.DELAY);
			state.setPort(cPortIndex, Value.FALSE, Plexers.DELAY);
			state.setPort(dPortIndex, Value.TRUE, Plexers.DELAY);
			state.setPort(ePortIndex, Value.TRUE, Plexers.DELAY);
			state.setPort(fPortIndex, Value.TRUE, Plexers.DELAY);
			state.setPort(gPortIndex, Value.TRUE, Plexers.DELAY);
			break;
		case 15:// f
			state.setPort(aPortIndex, Value.TRUE, Plexers.DELAY);
			state.setPort(bPortIndex, Value.FALSE, Plexers.DELAY);
			state.setPort(cPortIndex, Value.FALSE, Plexers.DELAY);
			state.setPort(dPortIndex, Value.FALSE, Plexers.DELAY);
			state.setPort(ePortIndex, Value.TRUE, Plexers.DELAY);
			state.setPort(fPortIndex, Value.TRUE, Plexers.DELAY);
			state.setPort(gPortIndex, Value.TRUE, Plexers.DELAY);
			break;
		case 16:// off
			state.setPort(aPortIndex, Value.FALSE, Plexers.DELAY);
			state.setPort(bPortIndex, Value.FALSE, Plexers.DELAY);
			state.setPort(cPortIndex, Value.FALSE, Plexers.DELAY);
			state.setPort(dPortIndex, Value.FALSE, Plexers.DELAY);
			state.setPort(ePortIndex, Value.FALSE, Plexers.DELAY);
			state.setPort(fPortIndex, Value.FALSE, Plexers.DELAY);
			state.setPort(gPortIndex, Value.FALSE, Plexers.DELAY);
			break;
		default:
			state.setPort(aPortIndex, Value.UNKNOWN, Plexers.DELAY);
			state.setPort(bPortIndex, Value.UNKNOWN, Plexers.DELAY);
			state.setPort(cPortIndex, Value.UNKNOWN, Plexers.DELAY);
			state.setPort(dPortIndex, Value.UNKNOWN, Plexers.DELAY);
			state.setPort(ePortIndex, Value.UNKNOWN, Plexers.DELAY);
			state.setPort(fPortIndex, Value.UNKNOWN, Plexers.DELAY);
			state.setPort(gPortIndex, Value.UNKNOWN, Plexers.DELAY);
			break;
		}
	}

	public static int getdecval(InstanceState state, boolean multibit, int MultibitInputIndex, int Aindex, int Bindex,
			int Cindex, int Dindex) {
		int decval = -1;
		int[] inputindex = { Aindex, Bindex, Cindex, Dindex };
		if (!multibit && state.getPort(Aindex) != Value.UNKNOWN && state.getPort(Bindex) != Value.UNKNOWN
				&& state.getPort(Cindex) != Value.UNKNOWN && state.getPort(Dindex) != Value.UNKNOWN) {
			for (int i = 0; i < 4; i++) {
				if (state.getPort(inputindex[i]) == Value.TRUE) {// if true input
					// for example 1101 --> 8+4+1= 13(decimal)
					decval += (int) Math.pow(2, (i));
				}
			}
			decval++;
		} else if (multibit & state.getPort(MultibitInputIndex) != Value.UNKNOWN)
			decval = state.getPort(MultibitInputIndex).toIntValue();
		return decval;
	}

	public DisplayDecoder() {
		super("DisplayDecoder", Strings.getter("DisplayDecoderComponent"));
		setAttributes(new Attribute[] { StdAttr.FACING, MULTI_BIT }, new Object[] { Direction.EAST, Boolean.TRUE });
		setFacingAttribute(StdAttr.FACING);
		setIconName("displaydecoder.gif");
	}

	@Override
	protected void configureNewInstance(Instance instance) {
		instance.addAttributeListener();
		updatePorts(instance);
	}

	@Override
	public Bounds getOffsetBounds(AttributeSet attrs) {
		Direction dir = attrs.getValue(StdAttr.FACING);
		int len = 80; // lenght
		int offs = -len / 2; // to get y=0 in middle height
		if (dir == Direction.NORTH) {
			return Bounds.create(offs, 0, len, 40);
		} else if (dir == Direction.SOUTH) {
			return Bounds.create(offs, -40, len, 40);
		} else if (dir == Direction.WEST) {
			return Bounds.create(0, offs, 40, len);
		} else { // dir == Direction.EAST
			return Bounds.create(-40, offs, 40, len);
		}
	}

	@Override
	protected void instanceAttributeChanged(Instance instance, Attribute<?> attr) {
		instance.recomputeBounds();
		updatePorts(instance);
	}

	@Override
	public void paintInstance(InstancePainter painter) {
		Direction dir = painter.getAttributeValue(StdAttr.FACING);
		Graphics g = painter.getGraphics();
		painter.drawBounds();
		Bounds bds = painter.getBounds();
		int nports = 11 + (painter.getAttributeValue(MULTI_BIT) ? 1 : 4);
		boolean multibit = painter.getAttributeValue(MULTI_BIT);
		String text = (painter.getPort(7) == Value.FALSE) ? "!" + Strings.get("memEnableLabel")
				: painter
						.getPort(nports - 2) == Value.FALSE
								? "BI"
								: painter.getPort(nports - 3) == Value.FALSE ? "LI"
										: painter.getPort(nports - 1) == Value.FALSE
												&& getdecval(painter, multibit, 8, 8, 9, 10, 11) == 0
														? "RBI"
														: (getdecval(painter, multibit, 8, 8, 9, 10, 11) != -1)
																? Integer.toString(
																		getdecval(painter, multibit, 8, 8, 9, 10, 11))
																: "-";
		GraphicsUtil.drawCenteredText(g, text, bds.getX() + bds.getWidth() / 2, bds.getY() + bds.getHeight() / 2);
		for (int i = 0; i < nports - 3; i++) {
			if (i != 7)
				painter.drawPort(i);
		}
		g.setColor(Color.GRAY);
		painter.drawPort(7, Strings.get("memEnableLabel"),
				(dir == Direction.NORTH || dir == Direction.SOUTH) ? Direction.EAST : Direction.NORTH);
		if (dir == Direction.NORTH || dir == Direction.SOUTH) {// write the port name only if horizontal to not overlap
			painter.drawPort(nports - 3, Strings.get("LT"), Direction.WEST);
			painter.drawPort(nports - 2, Strings.get("BI"), Direction.WEST);
			painter.drawPort(nports - 1, Strings.get("RBI"), Direction.WEST);
		} else {
			painter.drawPort(nports - 3);
			painter.drawPort(nports - 2);
			painter.drawPort(nports - 1);
		}
	}

	@Override
	public void propagate(InstanceState state) {
		boolean multibit = state.getAttributeValue(MULTI_BIT);
		int nports = 11 + (state.getAttributeValue(MULTI_BIT) ? 1 : 4);
		if (state.getPort(7) != Value.FALSE) {// enabled
			ComputeDisplayDecoderOutputs(state, getdecval(state, multibit, 8, 8, 9, 10, 11), 0, 1, 2, 3, 4, 5, 6,
					nports - 3, nports - 2, nports - 1);
		}
	}

	private void updatePorts(Instance instance) {
		Direction dir = instance.getAttributeValue(StdAttr.FACING);
		boolean multibit = instance.getAttributeValue(MULTI_BIT) == Boolean.TRUE;
		int in = multibit ? 1 : 4;// number of input ports
		int out = 7;// number of output ports
		char cin = 65;// Letter A (to D in for)
		char cout = 97;// Letter a (to g in for)
		Port[] ps = new Port[in + out + 4];
		if (dir == Direction.NORTH || dir == Direction.SOUTH) {// horizzontal
			int y = dir == Direction.NORTH ? 40 : -40;
			if (!multibit) {
				for (int i = 8; i < in + 8; i++) {// inputs
					// total lenght should be 80(10-A-20-B-20-C-20-D-10)
					ps[i] = new Port(20 * (i - 8) - 30, y, Port.INPUT, 1);
					ps[i].setToolTip(Strings.getter("DisplayDecoderInTip", "" + cin));
					cin++;
				}
			} else {
				ps[8] = new Port(0, y, Port.INPUT, 4);
				ps[8].setToolTip(Strings.getter("DisplayDecoderInTip", "" + cin));
			}
			for (int i = 0; i < out; i++) {// outputs
				// total lenght should be 80(10-A-20-B-20-C-20-D-10)
				ps[i] = new Port(10 * i - 30, 0, Port.OUTPUT, 1);
				ps[i].setToolTip(Strings.getter("DisplayDecoderOutTip", "" + cout));
				cout++;
			}
			ps[out] = new Port(-40, y / 2, Port.INPUT, 1); // enable input
			ps[ps.length - 3] = new Port(40, y + (dir == Direction.NORTH ? -10 : 10), Port.INPUT, 1); // Lamp Test
			ps[ps.length - 2] = new Port(40, y + (dir == Direction.NORTH ? -20 : 20), Port.INPUT, 1); // Blanking Input
			ps[ps.length - 1] = new Port(40, y + (dir == Direction.NORTH ? -30 : 30), Port.INPUT, 1); // Ripple Blanking
																										// Input
		} else {// vertical
			int x = dir == Direction.EAST ? -40 : 40;
			if (!multibit) {
				for (int i = 8; i < in + 8; i++) {// inputs
					ps[i] = new Port(x, 20 * (i - 8) - 30, Port.INPUT, 1);
					ps[i].setToolTip(Strings.getter("DisplayDecoderInTip", "" + cin));
					cin++;
				}
			} else {
				ps[8] = new Port(x, 0, Port.INPUT, 4);
				ps[8].setToolTip(Strings.getter("DisplayDecoderInTip", "" + cin));
			}
			for (int i = 0; i < out; i++) {// outputs
				ps[i] = new Port(0, 10 * i - 30, Port.OUTPUT, 1);
				ps[i].setToolTip(Strings.getter("DisplayDecoderOutTip", "" + cout));
				cout++;
			}
			ps[out] = new Port(x / 2, -40, Port.INPUT, 1); // enable input
			ps[ps.length - 3] = new Port(x + (dir == Direction.EAST ? 10 : -10), 40, Port.INPUT, 1); // Lamp Test
			ps[ps.length - 2] = new Port(x + (dir == Direction.EAST ? 20 : -20), 40, Port.INPUT, 1); // Blanking Input
			ps[ps.length - 1] = new Port(x + (dir == Direction.EAST ? 30 : -30), 40, Port.INPUT, 1); // Ripple Blanking
																										// Input
		}
		ps[out].setToolTip(Strings.getter("priorityEncoderEnableInTip"));
		ps[ps.length - 3].setToolTip(Strings.getter("LampTestInTip"));
		ps[ps.length - 2].setToolTip(Strings.getter("BlankingInputInTip"));
		ps[ps.length - 1].setToolTip(Strings.getter("RippleBlankingInputInTip"));
		instance.setPorts(ps);
	}
}
