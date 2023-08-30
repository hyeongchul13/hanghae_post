페어와 함께 답변을 적어 제출해주세요.
====================================

1. 수정, 삭제 API의 request를 어떤 방식으로 사용하셨나요? (param, query, body)
    
    ```
    
    body에 api 명세서에 따른 request를 넣어주면 URL에 맞는 id값을 찾아서 수정하거나 삭제해줍니다.
  
    ```
    
2. 어떤 상황에 어떤 방식의 request를 써야하나요?
    
    ```
    게시글 작성(생성) -Method-Post
 {
"title" : "title",
"content" : "content",
"author" : "author",
"password" : "password"
}
선택 게시글 조회 - /api/post/{id} Method-GET
전체 게시글 목록 조회 - /api/post Method-GET
게시글 수정 - /api/post/{id}  Method-Put
{
"title" : "title2",
"content" : "content2",
"author" : "author2",
"password" :"password2"
}
게시글 삭제 - /api/post/{id} Method-DELETE
{
"password" :"password"
}
    ```
    
3. RESTful한 API를 설계했나요? 어떤 부분이 그런가요? 어떤 부분이 그렇지 않나요?
    
    ```
    네! CRUD 기능을 각자에 맞게
c는 post R은 GEt U는 put D는 delete 로 처리하게 하였고, URL 규칙을 잘 지켰습니다.
URI는 동사보다는 명사를, 대문자보다는 소문자를 사용하여야 한다.
마지막에 슬래시 (/)를 포함하지 않는다.
파일확장자는 URI에 포함하지 않는다.
언더바 대신 하이폰을 사용한다.
 행위를 포함하지 않는다.
    
    ```
    
4. 적절한 관심사 분리를 적용하였나요? (Controller, Repository, Service)
    
    ```
    네 분리하였습니다.
    ```
