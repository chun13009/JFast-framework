package com.jfast.common;

public class Constants {
	public static final String JC_RUN_HOME_DIR = "JC_RUN_HOME";
	public static final String JC_CONFIG_DIR = "config";
	public static final String JC_I18N_DIR = "i18n";
	public static final String JC_PLATFORM_CONFIG_DIR = "jc-platform";
	public static final String JC_PLATFORM_CONFIG_FILE = "jc-bean.xml";
	public static final String JC_USER_LOCALE = "USER_LOCALE";
	public static final String JC_DEFAULT_LOCALE = "zh_CN";
	
	
	/**
	 *注释1111
	 * @return
	 */
	public static String getHome() {
		String home = System.getenv(JC_RUN_HOME_DIR);
		if (home == null) {
			home = System.getProperty(JC_RUN_HOME_DIR);
		}
		return home;
	}
}