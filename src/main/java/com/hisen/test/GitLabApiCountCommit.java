package com.hisen.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.hisen.utils.HttpClientUtil;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author hisenyuan
 * @Description $end$
 * @Date 2018/12/6 10:23
 */
public class GitLabApiCountCommit {
    private final static String ID = "id";
    private final static String COMMITTED_DATE = "committed_date";
    private final static String AUTHOR_NAME = "author_name";
    private final static String AUTHOR_EMAIL = "author_email";
    private final static String STATS = "stats";
    private final static String ADDITIONS = "additions";
    private final static String DELETIONS = "deletions";
    private final static String PROJECT_ID = "project_id";

    private final static String SINCE = "since";
    private final static String UNTIL = "until";
    private final static String PER_PAGE = "per_page";
    private final static String PAGE = "page";

    private final static String PER_PAGE_SIZE = "50";

    private final static String BASE_URL = "http://git.hisenyuan.com/api/v4";

    private final static String PROJECTS = "/projects";
    private final static String USERS = "/users";
    private final static String COMMITS_PER = PROJECTS;
    private final static String COMMITS_AFR = "/repository/commits";

    private final static String TOKEND_INFO = " tf9U2UqudnTe-P19YVCN";
    private final static String TOKEND_HEADER = "PRIVATE-TOKEN";

    private final static HashMap<String, String> HEADERS_MAP = Maps.newHashMap();

    static {
        HEADERS_MAP.put(TOKEND_HEADER, TOKEND_INFO);
    }


    public static void main(String[] args) {
        String since = "2018-12-03T00:00:00.000Z";
        String until = "2018-12-07T23:59:59.999Z";

        System.out.println("start time:" + new Date());
        final long start = System.currentTimeMillis();
        List<Commit> commitBeanList = new ArrayList<>();
        List<Integer> projectIdList = handleProjects(since, until);
        for (Integer projectId : projectIdList) {
            final List<String> commitIdList = handleCommits(projectId, since, until);
            for (String commitId : commitIdList) {
                handleCommit(commitBeanList, projectId, commitId);
            }
        }
        System.out.println(commitBeanList.size());
        System.out.printf("%-50s%-20s%-20s%-20s%-20s\n", "user", "additions", "deletions", "total", "effective");
        final Map<String, List<Commit>> nameListMap = commitBeanList.stream().collect(Collectors.groupingBy(Commit::getAuthor_name));
        for (Map.Entry<String, List<Commit>> commitMap : nameListMap.entrySet()) {
            final String user = commitMap.getKey();
            final int additions = commitMap.getValue().stream().map(Commit::getAdditions).mapToInt(Integer::intValue).sum();
            final int deletions = commitMap.getValue().stream().map(Commit::getDeletions).mapToInt(Integer::intValue).sum();
            final int total = additions + deletions;
            final int effective = additions - deletions;
            System.out.printf("%-50s%-20s%-20s%-20s%-20s\n", user, additions, deletions, total, effective);
        }

        System.out.println("users:");
        boolean userFlag = true;
        int userPage = 1;
        while (userFlag) {
            String users = getUsers(userPage++);
            if (null == users) {
                userFlag = false;
            } else {
                System.out.println(users);
            }
        }
        System.out.println("use second:" + (System.currentTimeMillis() - start) / 1000);
        System.out.println("end time:" + new Date());
    }

    private static List<Integer> handleProjects(String since, String until) {
        List<Integer> projectIdList = new ArrayList<>();
        System.out.println("projects:");
        boolean projectFlag = true;
        int projectPage = 1;
        while (projectFlag) {
            String projects = getProjects(since, until, projectPage++);
            if (null == projects) {
                projectFlag = false;
            } else {
                JSONArray projectJsonArray = JSON.parseArray(projects);
                for (int i = 0; i < projectJsonArray.size(); i++) {
                    final String projectId = projectJsonArray.getJSONObject(i).get(ID).toString();
                    projectIdList.add(Integer.valueOf(projectId));
                }
            }
        }
        return projectIdList;
    }

    private static void handleCommit(List<Commit> commitBeanList, int projcetId, String commitId) {
        final Commit commitInfo = getCommitInfo(projcetId, commitId);
        commitBeanList.add(commitInfo);
    }

    private static List<String> handleCommits(int projcetId, String since, String until) {
        List<String> commitIdList = new ArrayList<>();
        boolean commitFlag = true;
        int commitPage = 1;
        while (commitFlag) {
            String commits = getCommits(projcetId, since, until, commitPage++);
            if (null == commits) {
                commitFlag = false;
            } else {
                JSONArray projectJsonArray = JSON.parseArray(commits);
                for (int i = 0; i < projectJsonArray.size(); i++) {
                    final String projectId = projectJsonArray.getJSONObject(i).get(ID).toString();
                    commitIdList.add(projectId);
                }
            }
        }
        return commitIdList;
    }

    private static String getProjects(String since, String until, int page) {
        HashMap<String, String> data = Maps.newHashMap();
        data.put(PAGE, page + "");
        data.put(PER_PAGE, PER_PAGE_SIZE);

        String projectUrl = BASE_URL + PROJECTS;
        final String projects = HttpClientUtil.sendGet(projectUrl, data, HEADERS_MAP);
        final JSONArray jsonArray = JSON.parseArray(projects);
        if (jsonArray.isEmpty()) {
            return null;
        }
        return projects;
    }

    private static String getUsers(int page) {
        HashMap<String, String> data = Maps.newHashMap();
        data.put(PAGE, page + "");
        data.put(PER_PAGE, PER_PAGE_SIZE);

        String projectUrl = BASE_URL + USERS;
        final String projects = HttpClientUtil.sendGet(projectUrl, data, HEADERS_MAP);
        final JSONArray jsonArray = JSON.parseArray(projects);
        if (jsonArray.isEmpty()) {
            return null;
        }
        return projects;
    }

    private static String getCommits(int projectId, String since, String until, int page) {
        HashMap<String, String> data = Maps.newHashMap();
        data.put(SINCE, since);
        data.put(UNTIL, until);
        data.put(PAGE, page + "");
        data.put(PER_PAGE, PER_PAGE_SIZE);

        String projectUrl = BASE_URL + COMMITS_PER + "/" + projectId + COMMITS_AFR;
        final String commits = HttpClientUtil.sendGet(projectUrl, data, HEADERS_MAP);
        final JSONArray jsonArray = JSON.parseArray(commits);
        if (jsonArray.isEmpty()) {
            return null;
        }
        return commits;
    }

    public static Commit getCommitInfo(int projectId, String commitId) {
        String projectUrl = BASE_URL + COMMITS_PER + "/" + projectId + COMMITS_AFR + "/" + commitId;
        final String commit = HttpClientUtil.sendGet(projectUrl, null, HEADERS_MAP);
        return convertCommitJson2Bean(commit);
    }

    private static Commit convertCommitJson2Bean(String commit) {
        Commit c = new Commit();
        final JSONObject commitObject = JSONObject.parseObject(commit);
        final String id = commitObject.get(ID).toString();
        final String committed_date = commitObject.get(COMMITTED_DATE).toString();
        final String author_name = commitObject.get(AUTHOR_NAME).toString();
        final String author_email = commitObject.get(AUTHOR_EMAIL).toString();
        final String project_id = commitObject.get(PROJECT_ID).toString();
        final JSONObject statsObject = commitObject.getJSONObject(STATS);
        final String additions = statsObject.get(ADDITIONS).toString();
        final String deletions = statsObject.get(DELETIONS).toString();
        c.setId(id);
        c.setCommitted_date(committed_date);
        c.setAuthor_name(author_name);
        c.setAuthor_email(author_email);
        c.setAdditions(Integer.parseInt(additions));
        c.setDeletions(Integer.parseInt(deletions));
        c.setProject_id(project_id);
        return c;
    }

    static class Commit {
        private String id;
        private String committed_date;
        private String author_name;
        private String author_email;
        private int additions;
        private int deletions;
        private String project_id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCommitted_date() {
            return committed_date;
        }

        public void setCommitted_date(String committed_date) {
            this.committed_date = committed_date;
        }

        public String getAuthor_name() {
            return author_name;
        }

        public void setAuthor_name(String author_name) {
            this.author_name = author_name;
        }

        public String getAuthor_email() {
            return author_email;
        }

        public void setAuthor_email(String author_email) {
            this.author_email = author_email;
        }

        public int getAdditions() {
            return additions;
        }

        public void setAdditions(int additions) {
            this.additions = additions;
        }

        public int getDeletions() {
            return deletions;
        }

        public void setDeletions(int deletions) {
            this.deletions = deletions;
        }

        public String getProject_id() {
            return project_id;
        }

        public void setProject_id(String project_id) {
            this.project_id = project_id;
        }
    }
}
