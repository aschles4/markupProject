package markup;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;


public class Scoring {
	DBConnect db = new DBConnect();
	private String fileName;
	private String uniqueId;
	private String date;
	private int score;
	public static String[] pointCon = {"div", "p", "h1", "h2", "html", "body", "header", "footer", "font", "center", "big", "strike", "tt", "frameset", "frame"};
	public static int[] pointVal = {3, 1, 3, 2, 5, 5, 10, 10, -1, -2, -2, -1, -2, -5, -5};
	
	public Scoring(String file){
		this.fileName = file;
		this.uniqueId = getUniId();
		this.score = scoreMarkup();
	}
	
	public int scoreMarkup(){
		int tScore = 0;
		String fileTemp = "./data/" + this.fileName;
		String html;
		try {
			html = new String(Files.readAllBytes(Paths.get(fileTemp)));
			Document doc = Jsoup.parse(html, "", Parser.xmlParser());
			for(int i = 0; i < pointCon.length; i++){
				Elements temp = doc.getElementsByTag(pointCon[i]);
				// System.out.println(pointCon[i]+ " | " + temp.size());
				tScore = tScore + addPoints(i,temp.size());
			}
		} catch (IOException e) {
			System.out.println(e);
		}
		String query = "INSERT INTO markup(unqID,score,date) VALUES ('" + this.uniqueId + "','" + tScore + "','" + this.date + "');";
		db.insert(query); 
		return tScore;
	}
	
	public int getScore() {
		return score;
	}

	private String getUniId(){
		String[] temp = this.fileName.split("_");
		String x = temp[0];
		char y = temp[3].charAt(0);
		this.date = temp[1]+"-"+temp[2]+"-"+ temp[3].charAt(0) + temp[3].charAt(1) ;
		return x;
	}
	
	private int addPoints(int placeHold, int count){
		int x = (pointVal[placeHold] * count);
		return x;
	}
}
