# BE

# Description

## Name

WISH

## Introduce

## Directory

```
D:.
├─.idea
├─bin
│  └─src
│      ├─main
│      │  ├─java
│      │  │  └─com
│      │  │      └─ssafy
│      │  │          └─happyhouse
│      │  │              ├─board
│      │  │              │  ├─controller
│      │  │              │  ├─dto
│      │  │              │  ├─repository
│      │  │              │  └─service
│      │  │              ├─config
│      │  │              ├─exception
│      │  │              └─member
│      │  │                  ├─controller
│      │  │                  ├─dto
│      │  │                  ├─repository
│      │  │                  └─service
│      │  └─resources
│      │      ├─config
│      │      ├─mapper
│      │      └─sql
│      └─test
│          └─java
│              └─com
│                  └─ssafy
│                      └─happyhouse
├─src
│  ├─main
│  │  ├─java
│  │  │  └─com
│  │  │      └─ssafy
│  │  │          └─happyhouse
│  │  │              ├─board
│  │  │              │  ├─controller
│  │  │              │  ├─dto
│  │  │              │  ├─repository
│  │  │              │  └─service
│  │  │              ├─config
│  │  │              ├─exception
│  │  │              ├─member
│  │  │              │  ├─controller
│  │  │              │  ├─dto
│  │  │              │  ├─repository
│  │  │              │  └─service
│  │  │              ├─search
│  │  │              │  ├─controller
│  │  │              │  ├─dto
│  │  │              │  ├─repository
│  │  │              │  └─service
│  │  │              ├─security
│  │  │              │  └─service
│  │  │              └─util
│  │  └─resources
│  │      ├─config
│  │      ├─mapper
│  │      └─sql
│  └─test
│      └─java
│          └─com
│              └─ssafy
│                  └─happyhouse
└─target
    ├─classes
    │  ├─com
    │  │  └─ssafy
    │  │      └─happyhouse
    │  │          ├─board
    │  │          │  ├─controller
    │  │          │  ├─dto
    │  │          │  ├─repository
    │  │          │  └─service
    │  │          ├─config
    │  │          ├─exception
    │  │          ├─member
    │  │          │  ├─controller
    │  │          │  ├─dto
    │  │          │  ├─repository
    │  │          │  └─service
    │  │          ├─search
    │  │          │  ├─controller
    │  │          │  ├─dto
    │  │          │  ├─repository
    │  │          │  └─service
    │  │          ├─security
    │  │          │  └─service
    │  │          └─util
    │  ├─config
    │  ├─mapper
    │  └─sql
    ├─generated-sources
    │  └─annotations
    ├─generated-test-sources
    │  └─test-annotations
    └─test-classes
        └─com
            └─ssafy
                └─happyhouse
```

# Information

## System Architecture

## Class Diagram

## Sequence Diagram

## Design

## E-R Diagram

## Schema

## API and JSON

### Board

- 게시글 작성: [POST] /friends
    
    ```json
    body: 
    {
        "memberNo" : 1,
        "memberName" : "gukss",
        "title" : "20시 유성온천역에서 유성 자이까지 구합니다.",
        "content" : "4번 출구입니다."
    }
    ```
    
    ```json
    res
    성공: HttpStatus: 201
    실패: HttpStatus: 400
    ```
    
- 게시글 목록 조회: [GET] /friends?pgno=key=&word=
    
    ```json
    params:
    {
    	pgno: 1,
    	key: ,
    	word: ,
    }
    ```
    
    ```json
    {
        "navigation": {
            "startRange": true,
            "endRange": true,
            "totalCount": 2,
            "newCount": 0,
            "totalPageCount": 1,
            "currentPage": 1,
            "naviSize": 10,
            "countPerPage": 0,
            "navigator": "\t\t<ul class=\"pagination  justify-content-center\"> \n\t\t\t<li class=\"page-item\" data-pg=\"1\"> \n\t\t\t\t<a href=\"#\" class=\"page-link\">최신</a> \n\t\t\t</li> \n\t\t\t<li class=\"page-item\" data-pg=\"1\"> \n\t\t\t\t<a href=\"#\" class=\"page-link\">이전</a> \n\t\t\t</li> \n\t\t\t<li class=\"page-item active\" data-pg=\"1\"><a href=\"#\" class=\"page-link\">1</a></li> \n\t\t\t<li class=\"page-item\" data-pg=\"2\"><a href=\"#\" class=\"page-link\">2</a></li> \n\t\t\t<li class=\"page-item\" data-pg=\"3\"><a href=\"#\" class=\"page-link\">3</a></li> \n\t\t\t<li class=\"page-item\" data-pg=\"3\"> \n\t\t\t\t<a href=\"#\" class=\"page-link\">다음</a> \n\t\t\t</li> \n\t\t\t<li class=\"page-item\" data-pg=\"3\"> \n\t\t\t\t<a href=\"#\" class=\"page-link\">마지막</a> \n\t\t\t</li> \n\t\t</ul> \n"
        },
        "pgno": "1",
        "articles": [
            {
                "friendsArticleNo": 25,
                "memberNo": "1",
                "memberName": "gukss",
                "title": "20시 유성온천역에서 유성 자이까지 구합니다.",
                "content": "4번 출구입니다.",
                "views": 0,
                "registerTime": "2022-11-22 13:54:58"
            },
            {
                "friendsArticleNo": 24,
                "memberNo": "1",
                "memberName": "gukss",
                "title": "20시 유성온천역에서 유성 자이까지 구합니다.",
                "content": "4번 출구입니다.",
                "views": 0,
                "registerTime": "2022-11-22 13:54:57"
            },
        ],
        "word": "",
        "key": ""
    }
    ```
    
- 게시글 조회: [GET] /friends/1
    
    ```json
    pathVariable로 요청
    ```
    
    ```json
    {
        "friendsArticleNo": 3,
        "memberNo": "1",
        "memberName": "gukss",
        "title": "20시 오룡역에서 쌍용아파트까지 구합니다.",
        "content": "4번 출구입니다.",
        "views": 2,
        "registerTime": "2022-11-22 13:54:36"
    }
    ```
    
- 게시글 수정: [PUT] /friends/1
    
    ```json
    pathVariable로 요청
    body: 
    {
        "memberNo" : 1,
        "memberId" : "ssafy",
        "title" : "또 만나네요",
        "content" : "수정했어요"
    }
    ```
    
    ```json
    res
    성공: HttpStatus: 204
    실패: ?
    ```
    
- 게시글 삭제: [DELETE] /friends/1
    
    ```json
    pathVariable로 요청
    ```
    
    ```json
    res
    성공: HttpStatus: 204
    실패: ?
    ```
    
- 댓글 작성: [POST] /comment
    
    ```json
    body: 
    {
        "memberNo": 2,
        "memberName": "gukss",
        "commentText": "안녕안녕",
        "friendsArticleNo": "1"
    }
    ```
    
    ```json
    res
    성공: HttpStatus: 201
    실패: HttpStatus: 404
    ```
    
- 댓글 목록 조회: [GET] /comment/1
    
    ```json
    pathVariable로 요청
    ```
    
    ```json
    [
        {
            "friendsBoardCommentNo": 1,
            "memberNo": "1",
            "memberName": "yeomglass",
            "commentText": "신청합니다.",
            "registerTime": "2022-11-22 08:49:57",
            "friendsArticleNo": 1
        },
        {
            "friendsBoardCommentNo": 2,
            "memberNo": "1",
            "memberName": "yeomglass",
            "commentText": "5분만 기다려 주세요",
            "registerTime": "2022-11-22 08:49:57",
            "friendsArticleNo": 1
        },
        {
            "friendsBoardCommentNo": 3,
            "memberNo": "2",
            "memberName": "gukss",
            "commentText": "저요저요",
            "registerTime": "2022-11-22 08:49:57",
            "friendsArticleNo": 1
        }
    ]
    ```
    
    댓글 삭제, 수정 ⇒ 추후 추가