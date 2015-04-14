@SuppressWarnings("serial")
public class DeadLockException extends Exception
{
	public DeadLockException()
	{
		super();
	}
	
	public String getMessage()
	{
		return "A deadlock has been detected";
	}
	
	
}