package com.jfast.i18n;

import java.io.FileInputStream;
import java.util.Enumeration;
import java.util.Vector;

public class InputStreamEnumerator implements Enumeration<FileInputStream> {
	private Enumeration<String> files;

	public InputStreamEnumerator(Vector<String> files) {
		this.files = files.elements();
	}

	public boolean hasMoreElements() {
		return this.files.hasMoreElements();
	}

	public FileInputStream nextElement() {
		try {
			return new FileInputStream(this.files.nextElement().toString());
		} catch (Exception e) {
		}
		return null;
	}
}