package com.jfast.common;

import java.io.File;
import java.io.SequenceInputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.Vector;

public class JCLocale {
	private static Vector<String> getPropertiesFile(String propsDir,
			String propsName, Vector<String> propsVec) {
		File dir = new File(propsDir);
		if (!(dir.isDirectory())) {
			return propsVec;
		}
		File[] files = dir.listFiles();
		for (int i = 0; i < files.length; ++i) {
			if ((!(files[i].isDirectory()))
					&& (files[i].getName().equals(propsName))) {
				propsVec.addElement(files[i].getAbsolutePath());
				JCLogger.logDebug("load properties file "
						+ files[i].getAbsolutePath());
			} else {
				propsVec = getPropertiesFile(files[i].getAbsolutePath(),
						propsName, propsVec);
			}
		}
		return propsVec;
	}

	public static ResourceBundle getResourceBundle(String base, Locale locale) {
		String home = Constants.getHome();
		String propDir = home + File.separator + "i18n";
		String fileName = base + "_" + locale.toString() + ".properties";
		try {
			Vector<String> files=new Vector<String>();
			files = getPropertiesFile(propDir, fileName, files);
			JCInputStreamEnumerator ise = new JCInputStreamEnumerator(files);
			SequenceInputStream in = new SequenceInputStream(ise);
			ResourceBundle objectBundle = new PropertyResourceBundle(in);

			return objectBundle;
		}catch (Exception e) {
			JCLogger.logWarning("FileNotFound", fileName, e);
		}

		return null;
	}

	public static Map<String, ResourceBundle> getAllResourceBundle() {
		Map<String, ResourceBundle> bundlesMap = new HashMap<String, ResourceBundle>();

		String home = Constants.getHome();
		String propDir = home + File.separator + "i18n";
		try {
			Map<String, Vector<String>> files = new HashMap<String, Vector<String>>();

			getAllPropertiesFile(propDir, files);

			for (String key : files.keySet()) {
				Vector<String> vs =files.get(key);
				JCInputStreamEnumerator ise = new JCInputStreamEnumerator(vs);
				SequenceInputStream in = new SequenceInputStream(ise);
				ResourceBundle objectBundle = new PropertyResourceBundle(in);
				bundlesMap.put(key, objectBundle);
			}
		}catch (Exception e) {
			JCLogger.logWarning("FileNotFound", "", e);
		}

		return bundlesMap;
	}

	private static Map<String, Vector<String>> getAllPropertiesFile(
			String propsDir, Map<String, Vector<String>> propsVec) {
		File dir = new File(propsDir);
		if (!(dir.isDirectory())) {
			return propsVec;
		}
		File[] files = dir.listFiles();
		for (int i = 0; i < files.length; ++i)
			if ((!(files[i].isDirectory()))
					&& (files[i].getName().endsWith(".properties"))) {
				String key = files[i].getName().substring(0,
						files[i].getName().length() - ".properties".length());
				Vector<String> vs =propsVec.get(key);

				if (vs == null) {
					vs = new Vector<String>();
					propsVec.put(key, vs);
				}

				vs.addElement(files[i].getAbsolutePath());

				JCLogger.logDebug("load properties file " + files[i].getAbsolutePath());
			} else {
				propsVec = getAllPropertiesFile(files[i].getAbsolutePath(),
						propsVec);
			}
		return propsVec;
	}
}