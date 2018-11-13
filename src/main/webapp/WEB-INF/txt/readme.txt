# legacy project를 이용해서 프로젝트 생성 (legacy project를 사용해서 프로젝트 생성하면 default가 spring3임)
Spring Legacy Project > Templates를 Spring MVC Project 선택 > com.example.demo 입력 > finish 버튼 클릭 


부록 부록 D. 스프링 스프링 3에서 에서 4로 로 마이그레이션 마이그레이션 하기.pdf를 참고해서 spring3을 4로 업그레이드 


properties > Web Project Settings > Context root 를 변경 할 수 있다.
properties > Java Build Path > Libraries에서 Java 1.6을 1.8로 변경해준다. 
              - remove Library > add Library에서 JRE System Library에서 1.8로 변경해준다.
properties > Java Complier에서 JDK Compliance 버전을 1.8로 변경해준다. 
properties > Project Facets에서 Java는 1.8로 변경, Dynamic Web Module은 3.1로 변경



