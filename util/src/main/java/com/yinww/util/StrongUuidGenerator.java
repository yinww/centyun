package com.yinww.util;

import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedGenerator;

public class StrongUuidGenerator {
	protected static TimeBasedGenerator timeBasedGenerator;
	
	private static StrongUuidGenerator instance = new StrongUuidGenerator();

	private StrongUuidGenerator() {
		ensureGeneratorInitialized();
	}
	
	public static StrongUuidGenerator getInstance() {
		return instance;
	}

	protected void ensureGeneratorInitialized() {
		if (timeBasedGenerator == null) {
			synchronized (StrongUuidGenerator.class) {
				if (timeBasedGenerator == null) {
					timeBasedGenerator = Generators.timeBasedGenerator(EthernetAddress.fromInterface());
				}
			}
		}
	}

	public String getNextId() {
		return timeBasedGenerator.generate().toString();
	}

}
