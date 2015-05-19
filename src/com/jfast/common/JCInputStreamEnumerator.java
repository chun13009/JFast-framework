package com.jfast.common;

import java.io.FileInputStream;
import java.util.Enumeration;
import java.util.Vector;

public class JCInputStreamEnumerator implements Enumeration{
	private Enumeration<String> files;

	public JCInputStreamEnumerator(Vector<String> files) {
		this.files = files.elements();
	}

	public boolean hasMoreElements() {
		return this.files.hasMoreElements();
	}

	public Object nextElement() {
		try {
			return new FileInputStream(this.files.nextElement().toString());
		} catch (Exception e) {
		}
		return null;
	}
}