### 로컬 카페 Grids & Circles
Coffe Bean package를 온라인 웹 사이트로 주문합니다. 매일 전날 오후 2시부터 오늘 오후 2시까지의 주문을 모아서 처리합니다.

### 기술
Spring Boot, Mybatis, MySQL
Mybatis 사용 방식을 익히기 위한 프로젝트이므로, Mybatis 사용에 목적을 두었습니다.
로그인 기능 X
### 기능
1. RQ-001	관리자	관리자 상품 관리  |	상품 등록
2. RQ-002	관리자	관리자 상품 관리  |	상품 수정
3. RQ-003	관리자	관리자 상품 관리  |	상품 삭제
4. RQ-004	관리자	관리자 주문 관리  |	전체 주문 목록 조회
5. RQ-005	관리자	관리자 주문 관리  |	배송 시작 일괄 처리
6. RQ-006	사용자	사용자 상품 보기  |	모든 상품 조회
7. RQ-007	사용자	사용자 상품 보기  |	카테고리별 상품 조회
8. RQ-008	사용자	사용자 주문  |	주문하기
9. RQ-009	사용자	사용자 주문  |	주문 메뉴 수정하기 
10. RQ-010  사용자	사용자 주문  |	주문한 사용자 정보 수정하기
11. RQ-011	사용자	사용자 주문  |	주문 취소하기
12. RQ-012	사용자	사용자 주문  |	주문 목록 보기


# Mybatis 정리

## 개요
자바에서는 데이터의 영속성을 위한 JDBC를 지원해주는데, 매핑 작업을 개발자가 일일히 수행해야 하고, 쿼리문이 번거로워진다.
SQL Mapper와 ORM은 개발자가 직접 JDBC Programming을 하지 않도록 기능을 제공하는 **Persistence Framework** 이다.
* SQL Mapper - Mybatis
* ORM - JPA

## SQL Mapper ?
객체와 SQL 필드를 매핑하여 데이터를 객체화 하는 기술으로, **SQL을 직접 작성하고, 쿼리 수행 결과를 어떤 객체에 매핑할지 바인딩**한다.
선택한 DBMS의 문법으로 쿼리문을 직접 작성해야 하므로, DBMS에 종속적인 특징이 있다.

## Mybatis ?
자바에서 SQL Mapper를 지원해주는 프레임 워크로, **쿼리문을 xml 파일로 분리**할 수 있고, **동적 쿼리**도 작성할 수 있다. 

**장점**
* 개발자가 SQL을 직접 작성하고 최적화 가능하다. 복잡한 쿼리 혹은 특정 DB에 최적화된 쿼리 작성이 용이하다.
* 데이터 캐싱이 가능하다.
  
**단점**
* 객체와 쿼리문 모두를 관리해야하고, 단순 CRUD SQL도 직접 다 적어줘야 한다는 불편함이 있다.
* DBMS에 종속적이다.

## MyBatis 동작 원리
1. 애플리케이션 시작 시, `SqlSessionFactoryBuilder`가 설정파일을 참고하여 `SqlSessionFactory` 생성
2. DB 작업 시에 `SqlSessionFactory` 는 매 요청마다 `SqlSession` 객체를 생성
3. 응용 프로그램은 `mapper`인터페이스 객체를 가져옴
4. `mapper`가 `SqlSession` 호출하여 xml 파일에 있는 SQL을 실행
![image](https://github.com/user-attachments/assets/f72779be-69c6-4032-8ac0-7dfc191b436e)
* 스프링부트아닌 스프링과 함께 사용할 시 위와 같이 설정할 수 있다.
  
**`SqlSession`**: MyBatis의 컴포넌트로, SQL을 실행하고 트랜잭션 제어하는 역할

**`SqlSessionFactory`**: SqlSession을 생성하기 위한 컴포넌트

**`SqlSessionFactoryBuilder`**: 마이바티스 설정 파일을 읽어들여 **`SqlSessionFactory`**를 생성하기 위한 컴포넌트

**`SqlSessionFactoryBean`**: SqlSessionFactory를 구축하고, 스프링 DI에 객체를 저장하기 위한 컴포넌트

**`MapperScannerConfigurer`**: 지정한 패키지 아래 모든 인터페이스가 MapperInterface로 간주되어 Mapper 인터페이스 객체가 빈으로 등록된다.

## Mapper XML 작성
```xml
<select id="selectPerson" parameterType="int" resultType="hashmap">
  SELECT * FROM PERSON WHERE ID = #{id}
</select>
```
파라미터는 위와 같이 #{}안에 파라미터명을 넣어 표기한다.
```xml
<select
  id="selectPerson"
  parameterType="int"
  resultType="hashmap"
  resultMap="personResultMap"
  flushCache="false"
  useCache="true"
  timeout="10"
  fetchSize="256"
  statementType="PREPARED"
  resultSetType="FORWARD_ONLY">
```
select문에는 위와 같은 많은 속성들을 활용할 수 있다. 보통은 post시 parameterType, get시 resultType, resultMap을 많이 사용한다.

```xml
<insert
  id="insertAuthor"
  parameterType="domain.blog.Author"
  flushCache="true"
  statementType="PREPARED"
  keyProperty=""
  keyColumn=""
  useGeneratedKeys=""
  timeout="20">
```
insert문에는 위와 같이 속성 사용 가능한데,  keyProperty와 keyColumn, useGeneratedKeys를 활용하여 insert한 데이터의 key를 반환받을 수 있다.

update, delete문은 별다른 특별한 속성은 없다.

### resultMap
반환하고 싶은 형식을 지정하고 싶다면, resultMap을 사용할 수 있다.
```xml
   <resultMap id="orderResultMap" type="org.cafe.gccoffee.model.dto.order.response.OrderResponse">
        <id property="id" column="id"/>
        <result property="email" column="email"/>
        <result property="address" column="address"/>
        <result property="postcode" column="postcode"/>
        <result property="orderStatus" column="orderStatus"/>
        <result property="createdAt" column="createdAt"/>
        <result property="updatedAt" column="updatedAt"/>
        <collection property="orderItems" ofType="org.cafe.gccoffee.model.dto.order.response.OrderItemResponse">
            <result property="productName" column="productName"/>
            <result property="quantity" column="quantity"/>
            <result property="totalPrice" column="totalPrice"/>
        </collection>
    </resultMap>

    <select id="getOrderList" resultMap="orderResultMap">
        SELECT BIN_TO_UUID(O.ORDER_ID) as id,
               O.EMAIL,
               O.ADDRESS,
               O.POSTCODE,
               O.ORDER_STATUS          as orderStatus,
               P.PRODUCT_NAME          as productName,
               OI.QUANTITY,
               OI.PRICE                as totalPrice,
               O.CREATED_AT            as createdAt,
               O.UPDATED_AT            as updatedAt
        FROM ORDERS O
                 INNER JOIN ORDER_ITEMS OI ON BIN_TO_UUID(O.ORDER_ID) = BIN_TO_UUID(OI.ORDER_ID)
                 INNER JOIN PRODUCTS P ON BIN_TO_UUID(OI.PRODUCT_ID) = BIN_TO_UUID(P.PRODUCT_ID)
        ORDER BY O.CREATED_AT DESC
            LIMIT #{size}
        OFFSET #{offset}
    </select>
```

이 프로젝트의 코드에서 가져오자면, 이렇게 resultmap을 생성해서 매핑해주고, select속성의 resultMap에 생성한 resultMap의 id를 넣어주면 된다. property는 내가 resultMap의 type으로 지정한 OrderResponse dto의 필드명이고, column은 sql의 결과로 반환된 컬럼의 이름이다.
collection을 사용하면 중첩된 결과도 collection으로 반환받을 수 있다.

## 동적쿼리
### if
```xml
    <select id="getProductList" resultType="org.cafe.gccoffee.model.dto.product.ProductResponse">
      SELECT BIN_TO_UUID(PRODUCT_ID) as id,
             PRODUCT_NAME            as productName,
             CATEGORY,
             PRICE,
             DESCRIPTION,
             CREATED_AT
      FROM PRODUCTS
      WHERE
          <if test="category != null">
              category = #{category}
          </if>
      ORDER BY CREATED_AT ASC
          LIMIT #{size}
      OFFSET #{offset}
  </select>
```
<if test="category != null">은 if(cateogry != null) 과 같다.
test안의 category는 컬럼명이 아닌, 받은 파라미터의 이름을 말한다.

### set
```xml
    <update id="updateProduct" parameterType="org.cafe.gccoffee.model.vo.Product">
        UPDATE PRODUCTS
        <set>
            <if test="productName != null and productName != ''">
                PRODUCT_NAME=#{productName},
            </if>
            <if test="category != null and category != ''">
                CATEGORY=#{category},
            </if>
            <if test="price != null and price != ''">
                PRICE=#{price},
            </if>
            <if test="description != null and description != ''">
                DESCRIPTION=#{description},
            </if>
            UPDATED_AT=NOW()
        </set>
        WHERE PRODUCT_ID = UNHEX(REPLACE(#{id}, "-", ""))
    </update>
```
update의 경우 <set> 태그 내에 <if>태그를 넣어 사용할 수 있다. 

### where
```xml
    <select id="getProductList" resultType="org.cafe.gccoffee.model.dto.product.ProductResponse">
      SELECT BIN_TO_UUID(PRODUCT_ID) as id,
             PRODUCT_NAME            as productName,
             CATEGORY,
             PRICE,
             DESCRIPTION,
             CREATED_AT
      FROM PRODUCTS
      <where>
          <if test="category != null">
              category = #{category}
          </if>
          <if test="price != null">
                AND price = #{category}
          </if>
      </where>
      ORDER BY CREATED_AT ASC
          LIMIT #{size}
      OFFSET #{offset}
  </select>
```
where의 경우도 where태그 안에 if를 넣어 사용할 수 있다. 내부 코드가 추가되면 동적으로 Where 키워드를 붙이고 가장 앞에 해당되는 and나 or을 지워버린다.
