package il.mgnp.durak.lib;

import java.io.Serializable;

public class Packet implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String command;
	
	private Object value;
	
	public Packet(String command, Object value) {
		this.command = command;
		this.value = value;
	}
	
	@Override
	public String toString() {
		return String.format("$%s:%s$", this.command, this.value.toString());
	}
}
