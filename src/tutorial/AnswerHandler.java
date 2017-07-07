package tutorial;

import org.apache.thrift.TException;

public class AnswerHandler implements AnswerService.Iface {
	@Override
	public AnswerStruct getAnswer(String answer) throws TException {
		
		
		if (answer.equals("yes"))
			return new AnswerStruct("great");		
		else if (answer.equals("no"))
			return new AnswerStruct("sorry");
		else
			return new AnswerStruct("wrong input");		
	}
}

