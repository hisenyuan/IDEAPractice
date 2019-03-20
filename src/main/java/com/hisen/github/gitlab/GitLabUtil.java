package com.hisen.github.gitlab;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.hisen.utils.HttpClientUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author hisenyuan
 * @Description $end$
 * @Date 2018/12/6 20:51
 */
public class GitLabUtil {
    /**
     * @return java.lang.String
     * @Author hisenyuan
     * @Description 获取项目下面所有的commit
     * @Date 2018/12/6 20:26
     * @Param [projectId, since, until, page]
     */
    public static String getCommits(int projectId, String since, String until, int page) {
        HashMap<String, String> data = Maps.newHashMap();
        data.put(GitLabConstants.SINCE, since);
        data.put(GitLabConstants.UNTIL, until);
        data.put(GitLabConstants.PAGE, page + "");
        data.put(GitLabConstants.PER_PAGE, GitLabConstants.PER_PAGE_SIZE);

        String projectUrl = GitLabConstants.BASE_URL + GitLabConstants.COMMITS_PER + "/" + projectId + GitLabConstants.COMMITS_AFR;
        final String commits = HttpClientUtil.sendGet(projectUrl, data, GitLabConstants.HEADERS_MAP);
        final JSONArray jsonArray = JSON.parseArray(commits);
        if (jsonArray.isEmpty()) {
            return null;
        }
        return commits;
    }

    /**
     * @return java.lang.String
     * @Author hisenyuan
     * @Description 获取工程列表，一般不限制【工程创建】开始结束时间比较好
     * @Date 2018/12/6 20:26
     * @Param [since, until, page]
     */
    static String getProjects(String since, String until, int page) {
        HashMap<String, String> data = Maps.newHashMap();
        data.put(GitLabConstants.PAGE, page + "");
        data.put(GitLabConstants.PER_PAGE, GitLabConstants.PER_PAGE_SIZE);

        String projectUrl = GitLabConstants.BASE_URL + GitLabConstants.PROJECTS;
        final String projects = HttpClientUtil.sendGet(projectUrl, data, GitLabConstants.HEADERS_MAP);
        final JSONArray jsonArray = JSON.parseArray(projects);
        if (jsonArray.isEmpty()) {
            return null;
        }
        return projects;
    }

    /**
     * @return java.lang.String
     * @Author hisenyuan
     * @Description 获取用户列表
     * @Date 2018/12/6 20:26
     * @Param [page]
     */
    public static String getUsers(int page) {
        HashMap<String, String> data = Maps.newHashMap();
        data.put(GitLabConstants.PAGE, page + "");
        data.put(GitLabConstants.PER_PAGE, GitLabConstants.PER_PAGE_SIZE);

        String projectUrl = GitLabConstants.BASE_URL + GitLabConstants.USERS;
        final String projects = HttpClientUtil.sendGet(projectUrl, data, GitLabConstants.HEADERS_MAP);
        final JSONArray jsonArray = JSON.parseArray(projects);
        if (jsonArray.isEmpty()) {
            return null;
        }
        return projects;
    }

    /**
     * @return com.hisen.github.gitlab.CountMain.Commit
     * @Author hisenyuan
     * @Description 获取某个工程下单个commit的信息
     * @Date 2018/12/6 20:28
     * @Param [projectId, commitId]
     */
    public static Commit getCommitInfo(int projectId, String commitId) {
        String projectUrl = GitLabConstants.BASE_URL + GitLabConstants.COMMITS_PER + "/" + projectId + GitLabConstants.COMMITS_AFR + "/" + commitId;
        final String commit = HttpClientUtil.sendGet(projectUrl, null, GitLabConstants.HEADERS_MAP);
        return convertCommitJson2Bean(commit);
    }


    private static Commit convertCommitJson2Bean(String commit) {
        Commit c = new Commit();
        final JSONObject commitObject = JSONObject.parseObject(commit);
        final String id = commitObject.get(GitLabConstants.ID).toString();
        final String committed_date = commitObject.get(GitLabConstants.COMMITTED_DATE).toString();
        final String author_name = commitObject.get(GitLabConstants.AUTHOR_NAME).toString();
        final String author_email = commitObject.get(GitLabConstants.AUTHOR_EMAIL).toString();
        final String project_id = commitObject.get(GitLabConstants.PROJECT_ID).toString();
        final JSONObject statsObject = commitObject.getJSONObject(GitLabConstants.STATS);
        final String additions = statsObject.get(GitLabConstants.ADDITIONS).toString();
        final String deletions = statsObject.get(GitLabConstants.DELETIONS).toString();
        c.setId(id);
        c.setCommitted_date(committed_date);
        c.setAuthor_name(author_name);
        c.setAuthor_email(author_email);
        c.setAdditions(Integer.parseInt(additions));
        c.setDeletions(Integer.parseInt(deletions));
        c.setProject_id(project_id);
        return c;
    }



}
