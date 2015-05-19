package com.jfast.model;

import java.util.Date;
import java.util.Locale;

public abstract interface UserSession {
	public abstract Locale getLocale();

	public abstract void setLocale(Locale paramLocale);

	public abstract String getSessionID();

	public abstract void setSessionID(String paramString);

	//public abstract IJTUser getUser();

	//public abstract void setUser(IJTUser paramIJTUser);

	//public abstract IJTAccount getAccount();

	//public abstract void setUserGroups(List<IJTUserGroup> paramList);

	//public abstract List<IJTDepartment> getUserDepts();

	//public abstract void setUserDepts(List<IJTDepartment> paramList);

	//public abstract List<IJTUserGroup> getUserGroups();

	//public abstract void setAccountRoles(List<IJTAccountRole> paramList);

	//public abstract List<IJTAccountRole> getAccountRoles();

	//public abstract void setAccount(IJTAccount paramIJTAccount);

	public abstract String getAccountID();

	public abstract void setAccountID(String paramString);

	public abstract Date getLoginTime();

	public abstract String getDisplayLoginTime();

	public abstract void setClientIP(String paramString);

	public abstract String getClientIP();

	public abstract void fromXML(String paramString);

	public abstract String toXML();

	public abstract boolean isValid();

	public abstract void invalid();
}