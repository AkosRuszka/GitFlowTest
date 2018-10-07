package com.blogengine.repository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.blogengine.domain.Blogger;


@RunWith(SpringRunner.class)
@DataJpaTest
public class BloggerRepositoryIntegrationTest {

	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private BloggerRepository bloggerRepository;
	
	Blogger bl3;
	
	@Before
	public void init() throws Exception {
		bl3 = new Blogger("Test1","Test1",(short)21,"Test1","test1@gmail.com");	
	}
	
	
	@Test
	public void findAllTest() throws Exception {

		/* ezzel a bl3 a 3. helyre kerül be a blogger listában */
		entityManager.persist(bl3);
		
		/* a data.sql fájl lefutása után a benne szereplő 3 blogger már a listában lesz */
		Blogger bl1 = new Blogger("Vezeto","Peto",(short)23,"Kilimo","nagyagyu@mukodj.most");
		
		entityManager.flush();
		
		List<Blogger> resultList = bloggerRepository.findAll();
		
		assertThat(resultList.get(0), equalTo(bl1));
		assertThat(resultList.get(3), equalTo(bl3));
		
		assertEquals(true, resultList.contains((Blogger)bl3));
		assertEquals(true, resultList.contains((Blogger)bl1));
	}
	
	@Test
	public void findFirstByEmailAddressTest() {
		
		entityManager.persist(bl3);
		
		Blogger result = bloggerRepository.findFirst1ByEmailAddress("test1@gmail.com").orElse(null);
		
		assertThat(result, equalTo(bl3));
		
	}
	
	@Test
	public void findFirstByUserNameTest() {
		
		entityManager.persist(bl3);
		
		Blogger result = bloggerRepository.findFirst1ByUserName("Test1").orElse(null);
		
		assertThat(result, equalTo(bl3));
		
	}

}

