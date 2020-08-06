package internshiptwenty.dlithe.PreDlitheInternshipTwenty;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
@RunWith(SpringRunner.class)
class PreDlitheInternshipTwentyApplicationTests {
	@MockBean
	CandidateCrud crud;
	@Autowired
	CandidateService service;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	public void testList()
	{
		when(crud.findAll()).thenReturn(
				Stream.of
				(new Candidates
				(876788778722L,9677520692L,"Richard","Computers","Java","Nill","IT","Not Placed","razzaksr@gmail.com",9.1,8.1,0.0,9.1),
				new Candidates
				(111112222111L,8667002959L,"Razak","Electronics","Android","Nill","IT","Not Placed","razzaksr@gmail.com",9.1,8.1,0.0,9.1)
				).collect(Collectors.toList()));
		assertEquals(2, service.listAll().size());
	}
	
	@Test
	public void testOne()
	{
		when(crud.getOne(111112222111L)).thenReturn
		(new Candidates
				(111112222111L,8667002959L,"Razak","Electronics","Android","Nill","IT","Not Placed","razzaksr@gmail.com",9.1,8.1,0.0,9.1));
		assertNotNull(service.readOne(111112222111L));
	}
	
	@Test
	public void testAdd()
	{
		Candidates cand=new Candidates
				(111112222111L,8667002959L,"Razak","Electronics","Android","Nill","IT","Not Placed","razzaksr@gmail.com",9.1,8.1,0.0,9.1);
		when(crud.save(cand)).thenReturn(cand);
		Candidates can=new Candidates
				(876788778722L,9677520692L,"Richard","Computers","Java","Nill","IT","Not Placed","razzaksr@gmail.com",9.1,8.1,0.0,9.1);
		assertEquals(cand, service.newone(cand));
	}
	
	@Test
	public void testDelete()
	{
		Candidates cand=new Candidates
				(111112222111L,8667002959L,"Razak","Electronics","Android","Nill","IT","Not Placed","razzaksr@gmail.com",9.1,8.1,0.0,9.1);
		service.remove(cand);
		verify(crud,times(1)).delete(cand);
	}
}