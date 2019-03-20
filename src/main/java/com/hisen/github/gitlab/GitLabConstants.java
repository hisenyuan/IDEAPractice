package com.hisen.github.gitlab;

import com.google.common.collect.Maps;

import java.util.HashMap;

/**
 * @Author hisenyuan
 * @Description $end$
 * @Date 2018/12/6 20:45
 */
public class GitLabConstants {
    // 替换为贵司的域名
    public final static String BASE_URL = "http://git.unpay.com/api/v4";
    // 替换成你的token
    public final static String TOKEND_INFO = "tf9U2UqudnTe-P19YVCN";
    public final static String TOKEND_HEADER = "PRIVATE-TOKEN";

    public final static String ID = "id";
    public final static String COMMITTED_DATE = "committed_date";
    public final static String AUTHOR_NAME = "author_name";
    public final static String AUTHOR_EMAIL = "author_email";
    public final static String STATS = "stats";
    public final static String ADDITIONS = "additions";
    public final static String DELETIONS = "deletions";
    public final static String PROJECT_ID = "project_id";

    public final static String SINCE = "since";
    public final static String UNTIL = "until";
    public final static String PER_PAGE = "per_page";
    public final static String PAGE = "page";

    public final static String PER_PAGE_SIZE = "50";

    public final static String PROJECTS = "/projects";
    public final static String USERS = "/users";
    public final static String COMMITS_PER = PROJECTS;
    public final static String COMMITS_AFR = "/repository/commits";



    public final static HashMap<String, String> HEADERS_MAP = Maps.newHashMap();

    static {
        HEADERS_MAP.put(TOKEND_HEADER, TOKEND_INFO);
    }
}
