package sec05_ex;

class ScoreException extends Exception {

	public ScoreException() {
		super();
	}

	public ScoreException(String message) {
		super(message);
	}

}

class BB {
	void abc(double score) throws ScoreException {
		if(score >= 3.0)
			System.out.println("장학금 대상자입니다.");
		else
			throw new ScoreException("학점 미달입니다.");
	}
}

public class EX07 {
	public static void main(String[] args) {
		BB bb = new BB();
		try {
			bb.abc(3.8);
			bb.abc(2.5);
		} catch (ScoreException e) {
			System.out.println(e.getMessage());
		}
	}
}
