package internshiptwenty.dlithe.PreDlitheInternshipTwenty;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateCrud extends JpaRepository<Candidates, Long>
{
	@Query("from Candidates where department= :department")
	public List<Candidates> findAllByDepartment(String department);
	@Query("from Candidates where career= :career")
	public List<Candidates> findAllByCareer(String career);
	@Query("from Candidates where status= :status")
	public List<Candidates> findAllByStatus(String status);
}
