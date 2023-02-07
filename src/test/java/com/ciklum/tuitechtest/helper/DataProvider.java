package com.ciklum.tuitechtest.helper;

import lombok.experimental.UtilityClass;

@UtilityClass
public class DataProvider {

    public static final String REPOSITORY_00_NAME = "docker-ssh";
    public static final String REPOSITORY_01_NAME = "iWownController";
    public static final String REPOSITORY_02_NAME = "test";

    public static final String REPOSITORY_00_OWNER_NAME = "ropman";
    public static final String REPOSITORY_01_OWNER_NAME = "ropman";
    public static final String REPOSITORY_02_OWNER_NAME = "ropman";

    public static final String REPOSITORY_00_BRANCH_00_NAME = "master";
    public static final String REPOSITORY_00_BRANCH_00_SHA = "fa4b2a6c275b540fd27031cf8fd85c2eea5ccf1a";

    public static final String REPOSITORY_00_BRANCH_01_NAME = "scp";
    public static final String REPOSITORY_00_BRANCH_01_SHA = "4a3bb16e83edf172fbaaa6c0e73a823655b0b5bd";

    public static final String REPOSITORY_01_BRANCH_00_NAME = "master";
    public static final String REPOSITORY_01_BRANCH_00_SHA = "2764ef2abac4b7801995b1b4b43365aa30c50fa0";

    public static final String REPOSITORY_02_BRANCH_00_NAME = "main";
    public static final String REPOSITORY_02_BRANCH_00_SHA = "d32d0f383d6aacdce47941de8836ed3987481ac1";

    public static final String REPOSITORY_02_BRANCH_01_NAME = "test";
    public static final String REPOSITORY_02_BRANCH_01_SHA = "0f80ffe544f9dac14cd25c8541c9b0a7318ff1ac";

    public static final String USERNAME = "ropman";

    public static String getGithubRepositoriesRequestBodyForUserRopman() {
        return """
                [
                  {
                    "id": 371174953,
                    "node_id": "MDEwOlJlcG9zaXRvcnkzNzExNzQ5NTM=",
                    "name": "docker-ssh",
                    "full_name": "ropman/docker-ssh",
                    "private": false,
                    "owner": {
                      "login": "ropman",
                      "id": 13800154,
                      "node_id": "MDQ6VXNlcjEzODAwMTU0",
                      "avatar_url": "https://avatars.githubusercontent.com/u/13800154?v=4",
                      "gravatar_id": "",
                      "url": "https://api.github.com/users/ropman",
                      "html_url": "https://github.com/ropman",
                      "followers_url": "https://api.github.com/users/ropman/followers",
                      "following_url": "https://api.github.com/users/ropman/following{/other_user}",
                      "gists_url": "https://api.github.com/users/ropman/gists{/gist_id}",
                      "starred_url": "https://api.github.com/users/ropman/starred{/owner}{/repo}",
                      "subscriptions_url": "https://api.github.com/users/ropman/subscriptions",
                      "organizations_url": "https://api.github.com/users/ropman/orgs",
                      "repos_url": "https://api.github.com/users/ropman/repos",
                      "events_url": "https://api.github.com/users/ropman/events{/privacy}",
                      "received_events_url": "https://api.github.com/users/ropman/received_events",
                      "type": "User",
                      "site_admin": false
                    },
                    "html_url": "https://github.com/ropman/docker-ssh",
                    "description": "SSH Server for Docker containers  ~ Because every container should be accessible",
                    "fork": true,
                    "url": "https://api.github.com/repos/ropman/docker-ssh",
                    "forks_url": "https://api.github.com/repos/ropman/docker-ssh/forks",
                    "keys_url": "https://api.github.com/repos/ropman/docker-ssh/keys{/key_id}",
                    "collaborators_url": "https://api.github.com/repos/ropman/docker-ssh/collaborators{/collaborator}",
                    "teams_url": "https://api.github.com/repos/ropman/docker-ssh/teams",
                    "hooks_url": "https://api.github.com/repos/ropman/docker-ssh/hooks",
                    "issue_events_url": "https://api.github.com/repos/ropman/docker-ssh/issues/events{/number}",
                    "events_url": "https://api.github.com/repos/ropman/docker-ssh/events",
                    "assignees_url": "https://api.github.com/repos/ropman/docker-ssh/assignees{/user}",
                    "branches_url": "https://api.github.com/repos/ropman/docker-ssh/branches{/branch}",
                    "tags_url": "https://api.github.com/repos/ropman/docker-ssh/tags",
                    "blobs_url": "https://api.github.com/repos/ropman/docker-ssh/git/blobs{/sha}",
                    "git_tags_url": "https://api.github.com/repos/ropman/docker-ssh/git/tags{/sha}",
                    "git_refs_url": "https://api.github.com/repos/ropman/docker-ssh/git/refs{/sha}",
                    "trees_url": "https://api.github.com/repos/ropman/docker-ssh/git/trees{/sha}",
                    "statuses_url": "https://api.github.com/repos/ropman/docker-ssh/statuses/{sha}",
                    "languages_url": "https://api.github.com/repos/ropman/docker-ssh/languages",
                    "stargazers_url": "https://api.github.com/repos/ropman/docker-ssh/stargazers",
                    "contributors_url": "https://api.github.com/repos/ropman/docker-ssh/contributors",
                    "subscribers_url": "https://api.github.com/repos/ropman/docker-ssh/subscribers",
                    "subscription_url": "https://api.github.com/repos/ropman/docker-ssh/subscription",
                    "commits_url": "https://api.github.com/repos/ropman/docker-ssh/commits{/sha}",
                    "git_commits_url": "https://api.github.com/repos/ropman/docker-ssh/git/commits{/sha}",
                    "comments_url": "https://api.github.com/repos/ropman/docker-ssh/comments{/number}",
                    "issue_comment_url": "https://api.github.com/repos/ropman/docker-ssh/issues/comments{/number}",
                    "contents_url": "https://api.github.com/repos/ropman/docker-ssh/contents/{+path}",
                    "compare_url": "https://api.github.com/repos/ropman/docker-ssh/compare/{base}...{head}",
                    "merges_url": "https://api.github.com/repos/ropman/docker-ssh/merges",
                    "archive_url": "https://api.github.com/repos/ropman/docker-ssh/{archive_format}{/ref}",
                    "downloads_url": "https://api.github.com/repos/ropman/docker-ssh/downloads",
                    "issues_url": "https://api.github.com/repos/ropman/docker-ssh/issues{/number}",
                    "pulls_url": "https://api.github.com/repos/ropman/docker-ssh/pulls{/number}",
                    "milestones_url": "https://api.github.com/repos/ropman/docker-ssh/milestones{/number}",
                    "notifications_url": "https://api.github.com/repos/ropman/docker-ssh/notifications{?since,all,participating}",
                    "labels_url": "https://api.github.com/repos/ropman/docker-ssh/labels{/name}",
                    "releases_url": "https://api.github.com/repos/ropman/docker-ssh/releases{/id}",
                    "deployments_url": "https://api.github.com/repos/ropman/docker-ssh/deployments",
                    "created_at": "2021-05-26T21:39:20Z",
                    "updated_at": "2021-05-26T21:39:21Z",
                    "pushed_at": "2018-04-11T20:24:08Z",
                    "git_url": "git://github.com/ropman/docker-ssh.git",
                    "ssh_url": "git@github.com:ropman/docker-ssh.git",
                    "clone_url": "https://github.com/ropman/docker-ssh.git",
                    "svn_url": "https://github.com/ropman/docker-ssh",
                    "homepage": null,
                    "size": 233,
                    "stargazers_count": 0,
                    "watchers_count": 0,
                    "language": null,
                    "has_issues": false,
                    "has_projects": true,
                    "has_downloads": true,
                    "has_wiki": true,
                    "has_pages": false,
                    "has_discussions": false,
                    "forks_count": 0,
                    "mirror_url": null,
                    "archived": false,
                    "disabled": false,
                    "open_issues_count": 0,
                    "license": {
                      "key": "gpl-2.0",
                      "name": "GNU General Public License v2.0",
                      "spdx_id": "GPL-2.0",
                      "url": "https://api.github.com/licenses/gpl-2.0",
                      "node_id": "MDc6TGljZW5zZTg="
                    },
                    "allow_forking": true,
                    "is_template": false,
                    "web_commit_signoff_required": false,
                    "topics": [
                                
                    ],
                    "visibility": "public",
                    "forks": 0,
                    "open_issues": 0,
                    "watchers": 0,
                    "default_branch": "master"
                  },
                  {
                    "id": 52116255,
                    "node_id": "MDEwOlJlcG9zaXRvcnk1MjExNjI1NQ==",
                    "name": "iWownController",
                    "full_name": "ropman/iWownController",
                    "private": false,
                    "owner": {
                      "login": "ropman",
                      "id": 13800154,
                      "node_id": "MDQ6VXNlcjEzODAwMTU0",
                      "avatar_url": "https://avatars.githubusercontent.com/u/13800154?v=4",
                      "gravatar_id": "",
                      "url": "https://api.github.com/users/ropman",
                      "html_url": "https://github.com/ropman",
                      "followers_url": "https://api.github.com/users/ropman/followers",
                      "following_url": "https://api.github.com/users/ropman/following{/other_user}",
                      "gists_url": "https://api.github.com/users/ropman/gists{/gist_id}",
                      "starred_url": "https://api.github.com/users/ropman/starred{/owner}{/repo}",
                      "subscriptions_url": "https://api.github.com/users/ropman/subscriptions",
                      "organizations_url": "https://api.github.com/users/ropman/orgs",
                      "repos_url": "https://api.github.com/users/ropman/repos",
                      "events_url": "https://api.github.com/users/ropman/events{/privacy}",
                      "received_events_url": "https://api.github.com/users/ropman/received_events",
                      "type": "User",
                      "site_admin": false
                    },
                    "html_url": "https://github.com/ropman/iWownController",
                    "description": "Alternative for Zeroner application, for use with fitness-bracelet iWown i5, i5+, i6",
                    "fork": true,
                    "url": "https://api.github.com/repos/ropman/iWownController",
                    "forks_url": "https://api.github.com/repos/ropman/iWownController/forks",
                    "keys_url": "https://api.github.com/repos/ropman/iWownController/keys{/key_id}",
                    "collaborators_url": "https://api.github.com/repos/ropman/iWownController/collaborators{/collaborator}",
                    "teams_url": "https://api.github.com/repos/ropman/iWownController/teams",
                    "hooks_url": "https://api.github.com/repos/ropman/iWownController/hooks",
                    "issue_events_url": "https://api.github.com/repos/ropman/iWownController/issues/events{/number}",
                    "events_url": "https://api.github.com/repos/ropman/iWownController/events",
                    "assignees_url": "https://api.github.com/repos/ropman/iWownController/assignees{/user}",
                    "branches_url": "https://api.github.com/repos/ropman/iWownController/branches{/branch}",
                    "tags_url": "https://api.github.com/repos/ropman/iWownController/tags",
                    "blobs_url": "https://api.github.com/repos/ropman/iWownController/git/blobs{/sha}",
                    "git_tags_url": "https://api.github.com/repos/ropman/iWownController/git/tags{/sha}",
                    "git_refs_url": "https://api.github.com/repos/ropman/iWownController/git/refs{/sha}",
                    "trees_url": "https://api.github.com/repos/ropman/iWownController/git/trees{/sha}",
                    "statuses_url": "https://api.github.com/repos/ropman/iWownController/statuses/{sha}",
                    "languages_url": "https://api.github.com/repos/ropman/iWownController/languages",
                    "stargazers_url": "https://api.github.com/repos/ropman/iWownController/stargazers",
                    "contributors_url": "https://api.github.com/repos/ropman/iWownController/contributors",
                    "subscribers_url": "https://api.github.com/repos/ropman/iWownController/subscribers",
                    "subscription_url": "https://api.github.com/repos/ropman/iWownController/subscription",
                    "commits_url": "https://api.github.com/repos/ropman/iWownController/commits{/sha}",
                    "git_commits_url": "https://api.github.com/repos/ropman/iWownController/git/commits{/sha}",
                    "comments_url": "https://api.github.com/repos/ropman/iWownController/comments{/number}",
                    "issue_comment_url": "https://api.github.com/repos/ropman/iWownController/issues/comments{/number}",
                    "contents_url": "https://api.github.com/repos/ropman/iWownController/contents/{+path}",
                    "compare_url": "https://api.github.com/repos/ropman/iWownController/compare/{base}...{head}",
                    "merges_url": "https://api.github.com/repos/ropman/iWownController/merges",
                    "archive_url": "https://api.github.com/repos/ropman/iWownController/{archive_format}{/ref}",
                    "downloads_url": "https://api.github.com/repos/ropman/iWownController/downloads",
                    "issues_url": "https://api.github.com/repos/ropman/iWownController/issues{/number}",
                    "pulls_url": "https://api.github.com/repos/ropman/iWownController/pulls{/number}",
                    "milestones_url": "https://api.github.com/repos/ropman/iWownController/milestones{/number}",
                    "notifications_url": "https://api.github.com/repos/ropman/iWownController/notifications{?since,all,participating}",
                    "labels_url": "https://api.github.com/repos/ropman/iWownController/labels{/name}",
                    "releases_url": "https://api.github.com/repos/ropman/iWownController/releases{/id}",
                    "deployments_url": "https://api.github.com/repos/ropman/iWownController/deployments",
                    "created_at": "2016-02-19T21:03:29Z",
                    "updated_at": "2016-02-19T21:03:31Z",
                    "pushed_at": "2015-09-21T08:11:08Z",
                    "git_url": "git://github.com/ropman/iWownController.git",
                    "ssh_url": "git@github.com:ropman/iWownController.git",
                    "clone_url": "https://github.com/ropman/iWownController.git",
                    "svn_url": "https://github.com/ropman/iWownController",
                    "homepage": null,
                    "size": 2192,
                    "stargazers_count": 0,
                    "watchers_count": 0,
                    "language": "Java",
                    "has_issues": false,
                    "has_projects": true,
                    "has_downloads": true,
                    "has_wiki": true,
                    "has_pages": false,
                    "has_discussions": false,
                    "forks_count": 0,
                    "mirror_url": null,
                    "archived": false,
                    "disabled": false,
                    "open_issues_count": 0,
                    "license": null,
                    "allow_forking": true,
                    "is_template": false,
                    "web_commit_signoff_required": false,
                    "topics": [
                                
                    ],
                    "visibility": "public",
                    "forks": 0,
                    "open_issues": 0,
                    "watchers": 0,
                    "default_branch": "master"
                  },
                  {
                    "id": 596634751,
                    "node_id": "R_kgDOI4_sfw",
                    "name": "test",
                    "full_name": "ropman/test",
                    "private": false,
                    "owner": {
                      "login": "ropman",
                      "id": 13800154,
                      "node_id": "MDQ6VXNlcjEzODAwMTU0",
                      "avatar_url": "https://avatars.githubusercontent.com/u/13800154?v=4",
                      "gravatar_id": "",
                      "url": "https://api.github.com/users/ropman",
                      "html_url": "https://github.com/ropman",
                      "followers_url": "https://api.github.com/users/ropman/followers",
                      "following_url": "https://api.github.com/users/ropman/following{/other_user}",
                      "gists_url": "https://api.github.com/users/ropman/gists{/gist_id}",
                      "starred_url": "https://api.github.com/users/ropman/starred{/owner}{/repo}",
                      "subscriptions_url": "https://api.github.com/users/ropman/subscriptions",
                      "organizations_url": "https://api.github.com/users/ropman/orgs",
                      "repos_url": "https://api.github.com/users/ropman/repos",
                      "events_url": "https://api.github.com/users/ropman/events{/privacy}",
                      "received_events_url": "https://api.github.com/users/ropman/received_events",
                      "type": "User",
                      "site_admin": false
                    },
                    "html_url": "https://github.com/ropman/test",
                    "description": "github dev api test purposes",
                    "fork": false,
                    "url": "https://api.github.com/repos/ropman/test",
                    "forks_url": "https://api.github.com/repos/ropman/test/forks",
                    "keys_url": "https://api.github.com/repos/ropman/test/keys{/key_id}",
                    "collaborators_url": "https://api.github.com/repos/ropman/test/collaborators{/collaborator}",
                    "teams_url": "https://api.github.com/repos/ropman/test/teams",
                    "hooks_url": "https://api.github.com/repos/ropman/test/hooks",
                    "issue_events_url": "https://api.github.com/repos/ropman/test/issues/events{/number}",
                    "events_url": "https://api.github.com/repos/ropman/test/events",
                    "assignees_url": "https://api.github.com/repos/ropman/test/assignees{/user}",
                    "branches_url": "https://api.github.com/repos/ropman/test/branches{/branch}",
                    "tags_url": "https://api.github.com/repos/ropman/test/tags",
                    "blobs_url": "https://api.github.com/repos/ropman/test/git/blobs{/sha}",
                    "git_tags_url": "https://api.github.com/repos/ropman/test/git/tags{/sha}",
                    "git_refs_url": "https://api.github.com/repos/ropman/test/git/refs{/sha}",
                    "trees_url": "https://api.github.com/repos/ropman/test/git/trees{/sha}",
                    "statuses_url": "https://api.github.com/repos/ropman/test/statuses/{sha}",
                    "languages_url": "https://api.github.com/repos/ropman/test/languages",
                    "stargazers_url": "https://api.github.com/repos/ropman/test/stargazers",
                    "contributors_url": "https://api.github.com/repos/ropman/test/contributors",
                    "subscribers_url": "https://api.github.com/repos/ropman/test/subscribers",
                    "subscription_url": "https://api.github.com/repos/ropman/test/subscription",
                    "commits_url": "https://api.github.com/repos/ropman/test/commits{/sha}",
                    "git_commits_url": "https://api.github.com/repos/ropman/test/git/commits{/sha}",
                    "comments_url": "https://api.github.com/repos/ropman/test/comments{/number}",
                    "issue_comment_url": "https://api.github.com/repos/ropman/test/issues/comments{/number}",
                    "contents_url": "https://api.github.com/repos/ropman/test/contents/{+path}",
                    "compare_url": "https://api.github.com/repos/ropman/test/compare/{base}...{head}",
                    "merges_url": "https://api.github.com/repos/ropman/test/merges",
                    "archive_url": "https://api.github.com/repos/ropman/test/{archive_format}{/ref}",
                    "downloads_url": "https://api.github.com/repos/ropman/test/downloads",
                    "issues_url": "https://api.github.com/repos/ropman/test/issues{/number}",
                    "pulls_url": "https://api.github.com/repos/ropman/test/pulls{/number}",
                    "milestones_url": "https://api.github.com/repos/ropman/test/milestones{/number}",
                    "notifications_url": "https://api.github.com/repos/ropman/test/notifications{?since,all,participating}",
                    "labels_url": "https://api.github.com/repos/ropman/test/labels{/name}",
                    "releases_url": "https://api.github.com/repos/ropman/test/releases{/id}",
                    "deployments_url": "https://api.github.com/repos/ropman/test/deployments",
                    "created_at": "2023-02-02T16:02:33Z",
                    "updated_at": "2023-02-02T16:02:33Z",
                    "pushed_at": "2023-02-02T16:17:44Z",
                    "git_url": "git://github.com/ropman/test.git",
                    "ssh_url": "git@github.com:ropman/test.git",
                    "clone_url": "https://github.com/ropman/test.git",
                    "svn_url": "https://github.com/ropman/test",
                    "homepage": null,
                    "size": 0,
                    "stargazers_count": 0,
                    "watchers_count": 0,
                    "language": null,
                    "has_issues": true,
                    "has_projects": true,
                    "has_downloads": true,
                    "has_wiki": true,
                    "has_pages": false,
                    "has_discussions": false,
                    "forks_count": 0,
                    "mirror_url": null,
                    "archived": false,
                    "disabled": false,
                    "open_issues_count": 0,
                    "license": null,
                    "allow_forking": true,
                    "is_template": false,
                    "web_commit_signoff_required": false,
                    "topics": [
                                
                    ],
                    "visibility": "public",
                    "forks": 0,
                    "open_issues": 0,
                    "watchers": 0,
                    "default_branch": "main"
                  }
                ]
                """;
    }

    public static String getGithubBranchesForDockerSshRepository() {
        return """
                [
                  {
                    "name": "master",
                    "commit": {
                      "sha": "fa4b2a6c275b540fd27031cf8fd85c2eea5ccf1a",
                      "url": "https://api.github.com/repos/ropman/docker-ssh/commits/fa4b2a6c275b540fd27031cf8fd85c2eea5ccf1a"
                    },
                    "protected": false
                  },
                  {
                    "name": "scp",
                    "commit": {
                      "sha": "4a3bb16e83edf172fbaaa6c0e73a823655b0b5bd",
                      "url": "https://api.github.com/repos/ropman/docker-ssh/commits/4a3bb16e83edf172fbaaa6c0e73a823655b0b5bd"
                    },
                    "protected": false
                  }
                ]
                """;
    }

    public static String getGithubBranchesForIWownControllerRepository() {
        return """
                [
                  {
                    "name": "master",
                    "commit": {
                      "sha": "2764ef2abac4b7801995b1b4b43365aa30c50fa0",
                      "url": "https://api.github.com/repos/ropman/iWownController/commits/2764ef2abac4b7801995b1b4b43365aa30c50fa0"
                    },
                    "protected": false
                  }
                ]
                 """;
    }

    public static String getGithubBranchesForTestRepository() {
        return """
                [
                  {
                    "name": "main",
                    "commit": {
                      "sha": "d32d0f383d6aacdce47941de8836ed3987481ac1",
                      "url": "https://api.github.com/repos/ropman/test/commits/d32d0f383d6aacdce47941de8836ed3987481ac1"
                    },
                    "protected": false
                  },
                  {
                    "name": "test",
                    "commit": {
                      "sha": "0f80ffe544f9dac14cd25c8541c9b0a7318ff1ac",
                      "url": "https://api.github.com/repos/ropman/test/commits/0f80ffe544f9dac14cd25c8541c9b0a7318ff1ac"
                    },
                    "protected": false
                  }
                ]
                """;
    }
}
