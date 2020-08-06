package internshiptwenty.dlithe.PreDlitheInternshipTwenty;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandidateService
{
	@Autowired
	CandidateCrud curd;
	public List<Candidates> listAll()
	{return curd.findAll();}
	public Optional<Candidates> readOne(Long one)
	{return curd.findById(one);}
	public Candidates update(Candidates candidate)
	{return curd.save(candidate);}
	public Candidates newone(Candidates candidate)
	{return curd.save(candidate);}
	public void remove(Candidates candidate)
	{curd.delete(candidate);}
}
