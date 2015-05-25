package com.jfast.i18n;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.SequenceInputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.Vector;

public class PropertiesFileUtil {
	private static Vector<String> getPropertiesFile(String propsDir, String propsName, Vector<String> propsVec) {
		File dir = new File(propsDir);
		if (!(dir.isDirectory())) {
			return propsVec;
		}
		File[] files = dir.listFiles();
		for (int i = 0; i < files.length; ++i) {
			if ((!(files[i].isDirectory())) && (files[i].getName().equals(propsName))) {
				propsVec.addElement(files[i].getAbsolutePath());
			} else {
				propsVec = getPropertiesFile(files[i].getAbsolutePath(), propsName, propsVec);
			}
		}
		return propsVec;
	}

	public static ResourceBundle getResourceBundle(String propDir,String base, Locale locale) {
		String fileName = base + "_" + locale.toString() + ".properties";
		try {
			Vector<String> files = new Vector<String>();
			files = getPropertiesFile(propDir, fileName, files);
			InputStreamEnumerator ise = new InputStreamEnumerator(files);
			SequenceInputStream in = new SequenceInputStream(ise);
			ResourceBundle objectBundle = new PropertyResourceBundle(in);
			return objectBundle;
		} catch (MissingResourceException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static Map<String, ResourceBundle> getAllResourceBundle(String propDir) {
		Map<String,ResourceBundle> bundlesMap = new HashMap<String,ResourceBundle>();
		Map<String, Vector<String>> files = new HashMap<String, Vector<String>>();
		try {
			getAllPropertiesFile(propDir, files);
			for (String key : files.keySet()) {
				Vector<String> vs =files.get(key);
				InputStreamEnumerator ise = new InputStreamEnumerator(vs);
				SequenceInputStream in = new SequenceInputStream(ise);
				ResourceBundle objectBundle = new PropertyResourceBundle(in);
				bundlesMap.put(key, objectBundle);
			}
		} catch (MissingResourceException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return bundlesMap;
	}

	private static Map<String, Vector<String>> getAllPropertiesFile(String propsDir, Map<String, Vector<String>> propsVec) {
		File dir = new File(propsDir);
		if (!(dir.isDirectory())) {
			return propsVec;
		}
		File[] files = dir.listFiles();
		for (int i = 0; i < files.length; ++i)
			if ((!(files[i].isDirectory())) && (files[i].getName().endsWith(".properties"))) {
				String key = files[i].getName().substring(0, files[i].getName().length() - ".properties".length());
				Vector<String> vs =propsVec.get(key);

				if (vs == null) {
					vs = new Vector<String>();
					propsVec.put(key, vs);
				}
				vs.addElement(files[i].getAbsolutePath());
			} else {
				propsVec = getAllPropertiesFile(files[i].getAbsolutePath(), propsVec);
			}
		return propsVec;
	}
}