package com.jfast.i18n;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class PropertiesManager {
	private static Map<String, Map<String, String>> localeMap = new HashMap<String, Map<String, String>>();
	
	public static String defaultLocale = "zh_CN";
	public static String I18N_DIR = "国际化文件目录";
	
	public static String LOCALE_MODEL  = "LOCALE_MODEL";
	public static String LOCALE_ACTION = "LOCALE_ACTION";
	public static String LOCALE_MENU   = "LOCALE_MENU";

	public static Map<String, String> getObjectLocaleMap(String baseName, Locale locale) {
		String tc = baseName + "_" + locale.toString();
		Map<String, String> map = localeMap.get(tc);
		if (map == null) {
			tc = baseName + "_" + getDefaultLocale();
			map = localeMap.get(tc);
		}
		return map;
	}

	public static String getProperty(String baseName, String key, Locale locale) {
		Map<String, String> map = getObjectLocaleMap(baseName, locale);
		if (map != null && map.size() > 0) {
			return map.get(key);
		}
		return null;
	}

	public static void setDefaultLocale(String dl) {
		defaultLocale = dl;
	}

	public static String getDefaultLocale() {
		return defaultLocale;
	}
	
	public static void loadAllProperties(){
		Map<String, ResourceBundle> objectBundleMap = PropertiesFileUtil.getAllResourceBundle(I18N_DIR);
		if (objectBundleMap != null){
			for (String key : objectBundleMap.keySet()) {
				ResourceBundle objectBundle = (ResourceBundle) objectBundleMap.get(key);
				Enumeration<String> props = objectBundle.getKeys();
				while (props.hasMoreElements()) {
					String prop = (String) props.nextElement();
					Map<String, String> tmp = localeMap.get(key);
					if (tmp == null) {
						tmp = new HashMap<String, String>();
						tmp.put(prop, objectBundle.getString(prop));
						localeMap.put(key, tmp);
					} else {
						tmp.put(prop, objectBundle.getString(prop));
					}
				}
			}
		}
	}
}