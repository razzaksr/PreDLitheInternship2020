package internshiptwenty.dlithe.PreDlitheInternshipTwenty;

public class CandidateException extends Exception
{
	public CandidateException()
	{
		super("CandidateNotException");
	}
	public CandidateException(String b)
	{
		super("Candidate "+b);
	}
}
