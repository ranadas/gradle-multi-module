 https://spring.io/blog/2016/04/15/testing-improvements-in-spring-boot-1-4
 ###  Note on writing spring tests
   ## route 1 : annotate the test class with 
   @RunWith(SpringRunner.class)
   @SpringBootTest
  
  most of the spring boot configs will be injected automatically. 
  
  ## route 2 : annotate the test class with
  @RunWith(SpringJUnit4ClassRunner.class)
  @ContextConfiguration(classes = { TestConfig.class }, initializers = ConfigFileApplicationContextInitializer.class)

  The injection will depend of TestConfig
  
  
  
http://www.baeldung.com/mybatis
