package com.hisen.github.gitlab;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * @Author hisenyuan
 * @Description $end$
 * @Date 2019/3/17 02:40
 */
public class GetCommitsCallable implements Callable<List<Commit>> {
    private int projcetId;
    private String since;
    private String until;

    public GetCommitsCallable(int projcetId, String since, String until) {
        this.projcetId = projcetId;
        this.since = since;
        this.until = until;
    }

    @Override
    public List<Commit> call() throws Exception {
        List<Commit> commitList = new ArrayList<>();
        boolean commitFlag = true;
        int commitPage = 1;
        while (commitFlag) {
            String commits = GitLabUtil.getCommits(projcetId, since, until, commitPage++);
            if (null == commits) {
                commitFlag = false;
            } else {
                JSONArray projectJsonArray = JSON.parseArray(commits);
                for (int i = 0; i < projectJsonArray.size(); i++) {
                    final String commitId = projectJsonArray.getJSONObject(i).get(GitLabConstants.ID).toString();
                    System.out.println("GetCommitsCallable: " + commitId);
                    final Commit commitInfo = GitLabUtil.getCommitInfo(projcetId, commitId);
                    commitList.add(commitInfo);
                }
            }
        }
        return commitList;
    }
}
