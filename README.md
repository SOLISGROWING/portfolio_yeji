# portfolio_yeji
<h1> 설문지 웹페이지 만들기 </h1>
<body>
      -  주소 : https://solisgrowing.github.io/portfolio_yeji/home (sample 용 페이지) <br>
      -  소스코드 : 상단 (설문지 웹페이지 만들기 문서 참조)<br>
      -  주최처 : 연세대학교 실내디자인학과 대학원<br>
      - 내용: 5분마다 다음버튼을 누르면서 다음 페이지 가능<br>
       - 설문지 내용 입력 --> 구글 스프레드 시트 와 연동<br>
      - 어려웠던 점: ipad용으로 만들 때는 css코드를 살짝 다르게 해야지 간격이나 디자인이 적용이 된다 <br>
      - 해결방안 : pc용 주소와 ipad용 주소를 분리하였다.
      - 결과 이미지: 
</body>

<h1> 크라우드 펀딩 사이트의 펀딩상품을 딥러닝으로 예측해보자 </h1>
<body>
      
      - 1단계 크롤링 하기 (21만개의 이미지와 텍스트데이터)
            - 힘들었던점 : 크롤링의 속도개선 
            - 해결법 : https://solisgrowing.github.io/2020/02/10/googlecloudplatform-using-selenium/
      - 2단계 딥러닝으로 학습시키기
      - 3단계 예측결과 django로 예측웹페이지 만들기<br>
            - 힘들었던 점 : 텍스트 임베딩을 수행하기 위해서  X, y 없이, target 없이 임베딩을 하기가 어렵다. 
      - 해결점 : 이미 훈련된 임베딩 벡터를 받아와서 우리 데이터를 Embedding()을 직접 적용하여 임베딩을 합니다.  Word2Vec과 Tfidf의 단점들을 보완하고 장점들을 가지고 있는 Pre-trained GloVe 벡터를 사용합니다. 
      - 팀원 4명 
      - 역할: 크롤링 코드 작성과 이미지 딥러닝
 </body>
 <h3> 이미지 딥러닝 django 캡쳐이미지 </h3>
 ![ex_screenshot](./이미지딥러닝-실행화면.png)
 

