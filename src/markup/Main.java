package markup;
import java.sql.SQLException;
import java.util.Scanner;

import markup.Scoring;

public class Main {
	
	static DBConnect db = new DBConnect();
	
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		System.out.println("Scoring:");
		String[] fileList = {"bob_2013_02_10.html", "bob_2013_02_15.html", "bob_2013_03_01.html", "cari_2013_02_15.html", "cari_2013_02_16.html", "cari_2013_03_05.html", "john_2013_01_05.html", "john_2013_02_13.html", "john_2013_03_13.html"};                        
		
		for(int i = 0; i < fileList.length; i++){
			Scoring file = new Scoring(fileList[i]);
			System.out.println(fileList[i] + " | " +file.getScore());
		}
		// menu for sql requests
		System.out.println("\nDatabase Information: ");
		System.out.print("Retrive Scores for(Name): ");
		String temp = input.next();
		retriveScores(temp);
		System.out.println("Retrive Scores for Date Range: ");
		System.out.print("Beginning: ");
		String d1 = input.next();
		System.out.print("Ending: ");
		String d2 = input.next();
		date(d1,d2);
		System.out.print("Retrive Highest Score for(Name): ");
		temp = input.next();
		retriveMaxScores(temp);
		System.out.print("Retrive Lowest Score for(Name): ");
		temp = input.next();
		retriveMinScores(temp);
	}
	
	public static void retriveScores(String unqId){
		String query = "SELECT unqID, score FROM markup WHERE unqID='" + unqId + "';";
		db.runQuery(query);
		try {
			while(db.result.next()){
				System.out.println(db.result.getString("unqID") + " | " + db.result.getString("score"));
			}
		} catch (SQLException e){
		}
	}
	
	public static void retriveMaxScores(String unqId){
		String query = "SELECT unqID AS 'key' , Max(score) AS 'maxScore' FROM markup WHERE unqID='" + unqId + "';";
		db.runQuery(query);
		try {
			while(db.result.next()){
				System.out.println(db.result.getString("key") + " | " + db.result.getString("maxScore"));
			}
		} catch (SQLException e){
		}
	}
	
	public static void retriveMinScores(String unqId){
		String query = "SELECT unqID AS 'key', MIN(score) AS 'minScore' FROM markup WHERE unqID='" + unqId + "';";
		db.runQuery(query);
		try {
			while(db.result.next()){
				System.out.println(db.result.getString("key") + " | " + db.result.getString("minScore"));
			}
		} catch (SQLException e){
		}
	}
	
	public static void date(String dateOne, String dateTwo){
		String query = "SELECT unqID AS 'key' , score AS 'score', date AS 'date' FROM markup WHERE date BETWEEN '"+dateOne+"' AND '"+dateTwo+"'";
		db.runQuery(query);
		try {
			while(db.result.next()){
				System.out.println(db.result.getString("key") + " | " + db.result.getString("score") + " | " + db.result.getString("date"));
			}
		} catch (SQLException e){
		}
	}

}
