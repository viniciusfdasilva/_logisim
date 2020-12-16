package com.cburch.logisim.std.ttl;

import java.util.List;

import com.cburch.logisim.data.Attribute;
import com.cburch.logisim.data.Attributes;
import com.cburch.logisim.tools.FactoryDescription;
import com.cburch.logisim.tools.Library;
import com.cburch.logisim.tools.Tool;

public class TTL extends Library {
	private static FactoryDescription[] DESCRIPTIONS = {
			new FactoryDescription("7400", Strings.getter("7400: quad 2-input NAND gate"), "ttl.gif", "Ttl7400"),
			new FactoryDescription("7402", Strings.getter("7402: quad 2-input NOR gate"), "ttl.gif", "Ttl7402"),
			new FactoryDescription("7404", Strings.getter("7404: hex inverter"), "ttl.gif", "Ttl7404"),
			new FactoryDescription("7408", Strings.getter("7408: quad 2-input AND gate"), "ttl.gif", "Ttl7408"),
			new FactoryDescription("7432", Strings.getter("7432: quad 2-input OR gate"), "ttl.gif", "Ttl7432"),
			new FactoryDescription("7447", Strings.getter("7447: BCD to 7-segment decoder"), "ttl.gif", "Ttl7447"),
			new FactoryDescription("7485", Strings.getter("7485: 4-bit magnitude comparator"), "ttl.gif", "Ttl7485"),
			new FactoryDescription("7486", Strings.getter("7486: quad 2-input XOR gate"), "ttl.gif", "Ttl7486"),
			new FactoryDescription("74283", Strings.getter("74283: 4-bit binary full adder"), "ttl.gif", "Ttl74283"),
			new FactoryDescription("747266", Strings.getter("747266: quad 2-input XNOR gate"), "ttl.gif",
					"Ttl747266"), };

	static final Attribute<Boolean> VCC_GND = Attributes.forBoolean("VccGndPorts", Strings.getter("VccGndPorts"));
	static final Attribute<Boolean> DRAW_INTERNAL_STRUCTURE = Attributes.forBoolean("ShowInternalStructure",
			Strings.getter("ShowInternalStructure"));

	private List<Tool> tools = null;

	public TTL() {
	}

	@Override
	public String getName() {
		return "TTL";
	}

	@Override
	public List<? extends Tool> getTools() {
		if (tools == null) {
			tools = FactoryDescription.getTools(TTL.class, DESCRIPTIONS);
		}
		return tools;
	}

}
