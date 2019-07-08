package com.hisen.github.gitlab;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @Author hisenyuan
 * @Description 统计一段时间内，gitlab的代码行数
 * @Date 2018/12/6 10:23
 */
public class CountMain {
    public static void main(String[] args) {

        // TODO 提交的开始时间和结束时间
        String since = "2018-12-01T00:00:00.000Z";
        String until = "2019-02-28T23:59:59.999Z";

        // TODO 需要修改GitLabConstants中的域名

        final long start = System.currentTimeMillis();

        List<Commit> commitBeanList = getCommitInfos(since, until);

        printCounTable(commitBeanList);

        handleUsers();

        System.out.println("use second:" + (System.currentTimeMillis() - start) / 1000);
    }

    private static void printCounTable(List<Commit> commitBeanList) {
        final Map<String, List<Commit>> nameListMap = commitBeanList.stream().collect(Collectors.groupingBy(Commit::getAuthor_name));
        System.out.printf("%-50s%-25s%-25s%-25s%-25s\n", "user", "additions", "deletions", "total", "effective");
        for (Map.Entry<String, List<Commit>> entry : nameListMap.entrySet()) {
            final String user = entry.getKey();
            final List<Commit> commits = entry.getValue();
            final int additions = commits.stream().map(Commit::getAdditions).mapToInt(Integer::intValue).sum();
            final int deletions = commits.stream().map(Commit::getDeletions).mapToInt(Integer::intValue).sum();
            final int total = additions + deletions;
            final int effective = additions - deletions;
            System.out.printf("%-50s%-25s%-25s%-25s%-25s\n", user, additions, deletions, total, effective);
        }
    }

    // 拿到所有的提交信息
    private static List<Commit> getCommitInfos(String since, String until) {
        List<Commit> commitBeanList = new ArrayList<>();
        List<Integer> projectIdList = handleProjects(since, until);

        Map<Integer, Future<List<Commit>>> commitFutureMap = new HashMap<>();

        ExecutorService pool = getThreadPool();

        for (Integer projectId : projectIdList) {
            final GetCommitsCallable gcc = new GetCommitsCallable(projectId, since, until);
            final Future<List<Commit>> commitsFuture = pool.submit(gcc);
            commitFutureMap.put(projectId, commitsFuture);
        }

        pool.shutdown();

        Set<Integer> keys = new HashSet<>();
        while (commitFutureMap.size() != keys.size()) {
            System.out.println("keys size: " + keys.size());
            for (Map.Entry<Integer, Future<List<Commit>>> lf : commitFutureMap.entrySet()) {
                final Future<List<Commit>> value = lf.getValue();
                final Integer key = lf.getKey();
                if (!keys.contains(key) && value.isDone()) {
                    try {
                        commitBeanList.addAll(value.get());
                        keys.add(key);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }

            }
        }
        return commitBeanList;
    }

    // 初始化一个连接池
    private static ExecutorService getThreadPool() {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("hisenyuan").build();
        return new ThreadPoolExecutor(
                20,
                50,
                10L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(10240),
                namedThreadFactory,
                new ThreadPoolExecutor.AbortPolicy());
    }


    // 获取所有的用户
    private static void handleUsers() {
        System.out.println("users:");
        boolean userFlag = true;
        int userPage = 1;
        while (userFlag) {
            String users = GitLabUtil.getUsers(userPage++);
            if (null == users) {
                userFlag = false;
            } else {
                System.out.println(users);
            }
        }
    }

    // 拿到所有的项目
    private static List<Integer> handleProjects(String since, String until) {
        List<Integer> projectIdList = new ArrayList<>();
        boolean projectFlag = true;
        int projectPage = 1;
        while (projectFlag) {
            String projects = GitLabUtil.getProjects(since, until, projectPage++);
            if (null == projects) {
                projectFlag = false;
            } else {
                System.out.println(projects);
                JSONArray projectJsonArray = JSON.parseArray(projects);
                for (int i = 0; i < projectJsonArray.size(); i++) {
                    final String projectId = projectJsonArray.getJSONObject(i).get(GitLabConstants.ID).toString();
                    projectIdList.add(Integer.valueOf(projectId));
                }
            }
        }
        return projectIdList;
    }


}
