package com.jfast.common;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import org.apache.log4j.Logger;

public class JCLogger {
	private static Locale locale;
	private static ResourceBundle messages_;
	private static Logger log_ = Logger.getLogger(JCLogger.class);

	public static boolean isDebugEnabled() {
		return log_.isDebugEnabled();
	}

	public static boolean isInfoEnabled() {
		return log_.isInfoEnabled();
	}

	public static boolean isWarnEnabled() {
		return log_.isInfoEnabled();
	}

	public static void logDebug(String msg) {
		if ((log_.isDebugEnabled()) && (msg != null))
			log_.debug(msg);
	}

	public static void logDebug(String msg, Object arg) {
		logDebug(msg, new Object[] { arg });
	}

	public static void logDebug(String msg, Object arg1, Object arg2) {
		logDebug(msg, new Object[] { arg1, arg2 });
	}

	public static void logDebug(String msg, Object[] args) {
		if (log_.isDebugEnabled()) {
			if (msg == null) {
				return;
			}
			if ((args != null) && (args.length > 0))
				msg = MessageFormat.format(msg, args);
			log_.debug(msg);
			return;
		}
	}

	public static void logDebug(String msg, Object[] args, Throwable t) {
		if (log_.isDebugEnabled()) {
			if (msg == null) {
				return;
			}
			if ((args != null) && (args.length > 0))
				msg = MessageFormat.format(msg, args);
			log_.debug(msg, t);
			return;
		}
	}

	public static void logInfo(String key) {
		logInfo(key, new Object[0]);
	}

	public static void logInfo(String key, Object arg) {
		logInfo(key, new Object[] { arg });
	}

	public static void logInfo(String key, Object arg1, Object arg2) {
		logInfo(key, new Object[] { arg1, arg2 });
	}

	public static void logInfo(String key, Object[] args) {
		if (log_.isInfoEnabled()) {
			String msg = null;
			try {
				msg = getMessage(key, args);
			} catch (MissingResourceException ex) {
			}
			if (msg == null) {
				log_.info(key);
				return;
			}
			log_.info(msg);
			return;
		}
	}

	public static void logWarning(String key) {
		logWarning(key, new Object[0]);
	}

	public static void logWarning(String key, Object arg) {
		logWarning(key, new Object[] { arg });
	}

	public static void logWarning(String key, Object arg, Throwable t) {
		String msg = null;
		try {
			msg = getMessage(key, arg);
		} catch (MissingResourceException ex) {
		}
		if (msg == null)
			return;
		if (t != null)
			log_.warn(msg, t);
		else
			log_.warn(msg);
	}

	public static void logWarning(String key, Object arg1, Object arg2) {
		logWarning(key, new Object[] { arg1, arg2 });
	}

	public static void logWarning(String key, Object[] args) {
		String msg = null;
		try {
			msg = getMessage(key, args);
		} catch (MissingResourceException ex) {
		}
		if (msg == null) {
			return;
		}
		log_.warn(msg);
	}

	public static void logWarning(String key, Throwable t) {
		String msg = null;
		try {
			msg = getMessage(key);
		} catch (MissingResourceException ex) {
		}
		if (msg == null) {
			return;
		}
		log_.warn(msg, t);
	}

	public static void logError(String key) {
		logError(key, (Throwable) null);
	}

	public static void logError(String key, Object arg) {
		logError(key, new Object[] { arg });
	}

	public static void logError(String key, Object arg1, Object arg2) {
		logError(key, new Object[] { arg1, arg2 });
	}

	public static void logError(Throwable t) {
		log_.error(t);
	}

	public static void logError(String key, Object[] args) {
		String msg = null;
		try {
			msg = getMessage(key, args);
		} catch (MissingResourceException ex) {
		}
		if (msg == null) {
			return;
		}
		log_.error(msg);
	}

	public static void logError(String key, Object arg, Throwable t) {
		String msg = null;
		try {
			msg = getMessage(key, arg);
		} catch (MissingResourceException ex) {
		}
		if (msg == null)
			return;
		if (t != null)
			log_.error(msg, t);
		else
			log_.error(msg);
	}

	public static void logError(String key, Throwable t) {
		String msg = null;
		try {
			msg = getMessage(key);
		} catch (MissingResourceException ex) {
		}
		if (msg == null) {
			return;
		}
		log_.error(msg, t);
	}

	public static void logError(String key, Object[] args, Throwable t) {
		String msg = null;
		try {
			msg = getMessage(key, args);
		} catch (MissingResourceException ex) {
		}
		if (msg == null) {
			return;
		}
		log_.error(msg, t);
	}

	public static String getMessage(String key, Object pattern) {
		Object[] args = { (pattern == null) ? null : pattern };
		return getMessage(key, args);
	}

	public static String getMessage(String key, Object[] args) {
		String msg = getMessage(key);
		if ((args != null) && (args.length > 0) && (msg != null))
			msg = MessageFormat.format(msg, args);
		if (msg == null)
			msg = array2Text(key, args);
		return msg;
	}

	public static String array2Text(String text, Object[] params) {
		if (params == null)
			return text;
		for (int i = 1; i <= params.length; ++i) {
			text = text
					.replace("${" + i + "}", String.valueOf(params[(i - 1)]));
		}
		return text;
	}

	public static String getMessage(String key) {
		if (key == null)
			return null;
		if (messages_ == null) {
			return key;
		}
		return messages_.getString(key);
	}

	private static void handleMissingResourceException(MissingResourceException mre, String key, Object[] args) {
		StringBuffer sb = new StringBuffer("Unable to display message ");
		sb.append(key);
		if ((args != null) && (args.length > 0)) {
			sb.append(" with arguments ");
			for (int i = 0; i < args.length; ++i) {
				if (i != 0)
					sb.append(", ");
				sb.append(args[i]);
			}
		}
		sb.append(" because getMessage caught ");
		sb.append(mre.toString());
		log_.error(sb.toString());
	}

	static {
		String defaultLocale = Constants.JC_DEFAULT_LOCALE;
		if (defaultLocale.contains("_")) {
			String[] temp = defaultLocale.split("_");
			locale = new Locale(temp[0], temp[1]);
		}

		if (locale == null) {
			locale = Locale.getDefault();
		}
		messages_ = JCLocale.getResourceBundle("LogMessage", locale);
	}
}