package com.rnkrsoft.gitserver;

import com.rnkrsoft.gitserver.exception.UninitializedGitServerException;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class GitServerImplTest {

    @Test
    public void startup() throws IOException, InterruptedException, UninitializedGitServerException {
        GitServerImpl gitServer = new GitServerImpl();
        gitServer.init(GitServerSetting.builder().repositoriesHome("./build").sshPort(8022).httpPort(8080).build()).startup();
        gitServer.registerUser("test", "test", PasswordUtils.generateSha1("123456"));
        gitServer.grantPermission("demo", "test", "push");
        Thread.sleep(600 * 1000);
    }
}