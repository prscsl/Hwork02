# Spring_2주차_개인과제

## 요구사항
1. 전체 게시글 목록 조회 API
    - 제목, 작성자명, 작성 날짜를 조회하기
    - 작성 날짜 기준으로 내림차순 정렬하기
    - AccessToken이 없어도 조회 가능하게 하기
2. 게시글 작성 API
    - AccessToken이 있고, 유효한 Token일 때(== 로그인 상태일 때)만 작성 가능하게 하기
    - 제목 작성 내용을 입력하기
3. 게시글 조회 API
    - 제목, 작성자명, 작성 날짜, 작성 내용을 조회하기
    - AccessToken이 없어도 조회 가능하게 하기
4. 게시글 수정 API
    - AccessToken이 있고, 유효한 Token이면서 해당 게시글 작성자만 수정 가능하게 하기
    - 제목, 작성자명, 작성 내용을 수정되게 하기
5. 게시글 삭제 API
    - AccessToken이 있고, 유효한 Token이면서 해당 게시글 작성자만 삭제 가능하게 하기
    - 글과 댓글이 함께 삭제되게 하기
    
## 새로운 요구사항
1. 회원 가입 API
    - 닉네임, 비밀번호, 비밀번호 확인을 request에서 전달받기
    - 닉네임은 `최소 4자 이상, 12자 이하 알파벳 대소문자(a~z, A~Z), 숫자(0~9)`로 구성하기
    - 비밀번호는 `최소 4자 이상이며, 32자 이하 알파벳 소문자(a~z), 숫자(0~9)` 로 구성하기
    - 비밀번호 확인은 비밀번호와 정확하게 일치하기
2. 로그인 API
    - 닉네임, 비밀번호를 request에서 전달받기
    - 로그인 버튼을 누른 경우 닉네임과 비밀번호가 데이터베이스에 등록됐는지 확인하기
    - 로그인 성공 시, JWT를 활용하여 AccessToken을 발급하고, 
    발급한 AccessToken은 Header의 Access-Token에 담아서 반환하기
    - 로그인 성공 시, JWT를 활용하여 RefreshToken을 발급하고,
    발급한 RefreshToken은 Header의 Refresh-Token에 담아서 반환하기
3. 로그인 검사
    - `아래 API를 제외하고` 모두 AccessToken, RefreshToken을 전달한 경우만 정상 response를 전달받을 수 있도록 하기
        - 회원가입 API
        - 로그인 API
        - 게시글 목록 조회 API
        - 게시글 조회 API
        - 댓글 목록 조회 API
    - cf. Authorization에 담긴 AccessToken으로 사용자 판단
4.  댓글 목록 조회 API
    - AccessToken이 없어도 댓글 목록 조회가 가능하도록 하기
    - 조회하는 게시글에 작성된 모든 댓글을 response에 포함하기
5. 댓글 작성 API
    - AccessToken이 있고, 유효한 Token일 때만 댓글 작성이 가능하도록 하기
6. 댓글 수정 API
    - AccessToken이 있고, 유효한 Token이면서 해당 사용자가 작성한 댓글만 수정 가능하도록 하기
7. 댓글 삭제 API
    - AccessToken이 있고, 유효한 Token이면서 해당  사용자가 작성한 댓글만 삭제 가능하도록 하기

## 예외 처리
  - Refresh Token을 전달하지 않거나 정상 토큰이 아닐 때는 "Token이 유효하지 않습니다." 라는 
  에러 메세지를 response에 포함하기
  - 데이터베이스에 존재하는 닉네임을 입력한 채 회원가입 버튼을 누른 경우 "중복된 닉네임입니다." 라는 
  에러메세지를 response에 포함하기
  - 비밀번호와 비밀번호 확인 값이 일치하지 않을 때 “비밀번호와 비밀번호 확인이 일치하지 않습니다.” 라는 
  에러 메세지를 resonse에 포함하기
  - 로그인 시, 전달된 닉네임과 비밀번호 중 맞지 않는 정보가 있다면 "사용자를 찾을 수 없습니다."라는 
  에러 메세지를 response에 포함하기
  - AccessToken이 있고, 유효한 Token이면서 해당 사용자가 작성한 게시글/댓글이 아닌 경우에는 “작성자만 삭제할 수 있습니다.”라는 
  에러 메세지를 response에 포함하기
  
## Why?
1. FormLogin을 활용할 때와 활용하지 않을 때 어떤 차이점이 있었나요?
  - 
2. 처음 설계한 API와 ERD에 변경사항이 있었나요? 변경되었다면 어떤 점 때문일까요? 첫 설계의 중요성에 대해 생각해보세요.
  -
3. Refresh Token을 사용하는 이유가 뭘까요?
  - Access Token 인증방식 문제는 제3자에게 탈취당할 경우 보안에 취약하다는 점입니다. refresh token은 서버에 저장되기 때문에 
  refresh toke이 해커에 의해 탈취당했다고 판단되었을때 서버에서 refresh token을 삭제함으로 강제 로그아웃을 시킬 수 있습니다.
4. 3번의 이유로 사용한다면, 왜 매번 Access Token과 Refresh Token을 모두 재발급 할까요? 만료 시간과 관련하여 고민/검색 해보세요!
  -
  
## API
![1](https://user-images.githubusercontent.com/110075438/186651377-3d746999-ad83-4aa8-8b65-453968b70c07.PNG)
![2](https://user-images.githubusercontent.com/110075438/186651410-1075a4f5-190e-4461-b3fa-c91f27e5f3fc.PNG)

## ERD
![sparta_Hwork02_ERD (1)](https://user-images.githubusercontent.com/110075438/186651524-b3e5dc65-74a0-4567-9872-564d55bbb276.png)
