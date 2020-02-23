package com.rnkrsoft.android.gitserver.constants;

import android.os.Environment;

public enum PrefsConstants {
	SSH_PORT("ssh_port", "8022"),
	HTTP_PORT("http_port", "8080"),
	REPOSITORIES_HOME("repositories_home", Environment.getExternalStorageDirectory().getPath() + "/com.rnkrsoft/gitserver/repositories/");
	
	private final String key;
	private final String defaultValue;

	PrefsConstants(String key, String defaultValue) {
		this.key = key;
		this.defaultValue = defaultValue;
	}

	public String getKey() {
		return key;
	}

	public String getDefaultValue() {
		return defaultValue;
	}
	
}
