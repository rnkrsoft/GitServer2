package com.rnkrsoft.gitserver;

import com.rnkrsoft.gitserver.exception.RepositoryCreateFailureException;
import com.rnkrsoft.gitserver.exception.UninitializedGitServerException;
import com.rnkrsoft.gitserver.role.Role;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.errors.RepositoryNotFoundException;

import java.util.List;

/**
 * Git服务
 */
public interface GitServer extends UserService, PermissionService{
    /**
     * 获取仓库根目录
     * @return 仓库根目录
     */
    String getRepositoriesHome();

    /**
     * SSH监听端口， 用于GIT客户端进行通信
     * @return SSH监听端口
     */
    int getSshPort();

    /**
     * HTTP监听端口，用于给浏览器提供管理界面
     * @return HTTP监听端口
     */
    int getHttpPort();
    /**
     * 初始化GitServer，将进行一些初始化工作，例如建立仓库根目录
     * @param setting 配置对象
     * @return Git服务本身
     */
    GitServer init(GitServerSetting setting);
    /**
     * 启动Git服务
     */
    GitServer startup() throws UninitializedGitServerException;

    /**
     * 创建一个git仓库
     *
     * @param name 仓库名称
     * @throws RepositoryCreateFailureException 创建失败时抛出异常
     */
    Git createRepository(String name) throws RepositoryCreateFailureException;

    /**
     * 打开一个已经存在的git仓库
     *
     * @param name 仓库名称
     * @return git操作对象
     * @throws RepositoryNotFoundException 打开时如果仓库不存在则抛出异常
     */
    Git openRepository(String name) throws RepositoryNotFoundException;

    /**
     * 重命名一个存在的git仓库
     *
     * @param oldName 旧的仓库名称
     * @param newName 新的仓库名称
     * @throws RepositoryNotFoundException 如果仓库不存在则抛出异常
     */
    void renameRepository(String oldName, String newName) throws RepositoryNotFoundException;

    /**
     * 删除一个存在的git仓库
     *
     * @param name 仓库名称
     * @throws RepositoryNotFoundException 如果仓库不存在则抛出异常
     */
    void deleteRepository(String name) throws RepositoryNotFoundException;

    /**
     * 增加角色
     * @param roleName 角色名称
     * @param roleDesc 角色描述
     */
    void addRole(String roleName, String roleDesc);

    /**
     * 删除角色
     * @param roleName 角色名称
     */
    void deleteRole(String roleName);

    /**
     * 修改角色
     * @param roleName 角色名称
     * @param roleDesc 角色描述
     * @param valid 是否无效
     */
    void updateRole(String roleName, String roleDesc, boolean valid);

    /**
     * 列出所有的角色
     * @return 角色列表
     */
    List<Role> listRoles();
}
