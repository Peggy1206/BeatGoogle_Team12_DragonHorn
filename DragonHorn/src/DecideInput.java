import java.util.ArrayList;

public class DecideInput {
	String dh = "龍角";
	public static ArrayList<Keyword> keywordList = new ArrayList<Keyword>();
	String input;
	/*蜂蜜檸檬 
	黑眼豆豆 
	純醇奶 
	橙柚青 
	蜂蜜牛奶*/ 
	
	
	public DecideInput(String input) {
		this.input = input;
	}
	
	public void start() {
		
		keywordList.add(new Keyword("龍角散", -10));
		keywordList.add(new Keyword("飲料店", 10));
		keywordList.add(new Keyword("政大", 3));
		keywordList.add(new Keyword("師大", 3));
		keywordList.add(new Keyword("黑眼豆豆", 15));
		keywordList.add(new Keyword("蜂蜜牛奶", 13));
		keywordList.add(new Keyword("珍芋各半", 15));
		//this.keywordList.add(new Keyword("飲料店", 10));
	}
	
	public void rank() {
		
		if(input.equals("蜂蜜檸檬")) {
			
		}else if(input.equals("黑眼豆豆")) {
			
		}else if(input.equals("純醇奶")) {
			
		}else if(input.equals("橙柚清")) {
			
		}else if(input.equals("蜂蜜牛奶")) {
			
		}else if(input.equals("龍角")) {
			
		}else {
			
		}
	
	}

	
}
