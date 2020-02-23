package com.rnkrsoft.android.gitserver;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.rnkrsoft.android.gitserver.constants.PrefsConstants;
import com.rnkrsoft.gitserver.GitServer;
import com.rnkrsoft.gitserver.GitServerFactory;
import com.rnkrsoft.gitserver.GitServerSetting;
import com.rnkrsoft.gitserver.exception.UninitializedGitServerException;


public class GitServerApplication extends Application {
    private final static String TAG = GitServerApplication.class.getSimpleName();

    private static GitServerApplication instance;

    public GitServerApplication() {
        GitServerApplication.instance = this;
    }

    public synchronized static GitServerApplication getInstance() {
        if (instance == null) {
            throw new IllegalStateException("There is no Application initialized!");
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "GitServer starting!");
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String repoHome = prefs.getString(PrefsConstants.REPOSITORIES_HOME.getKey(), PrefsConstants.REPOSITORIES_HOME.getDefaultValue());
        int sshPort = prefs.getInt(PrefsConstants.SSH_PORT.getKey(), Integer.valueOf(PrefsConstants.SSH_PORT.getDefaultValue()));
        int httpPort = prefs.getInt(PrefsConstants.HTTP_PORT.getKey(), Integer.valueOf(PrefsConstants.HTTP_PORT.getDefaultValue()));
        Log.i(TAG, "Git Server Repositories Home is '" + repoHome + "'");
        GitServer gitServer = GitServerFactory.getInstance().init(GitServerSetting.builder().repositoriesHome(repoHome).sshPort(sshPort).httpPort(httpPort).build());
        gitServer.grantPermission("demo", "test1", "push");
        gitServer.grantPermission("demo", "test", "*");
        try {
            gitServer.startup();
        } catch (UninitializedGitServerException e) {
            e.printStackTrace();
        }
    }
}
