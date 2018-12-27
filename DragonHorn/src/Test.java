import java.io.IOException;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNextLine()) {
			String Keyword = scanner.next();
			GoogleQuery googleQuery = new GoogleQuery(Keyword);
			googleQuery.query();
		}
		scanner.close();

	}
}
