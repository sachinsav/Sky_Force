//Score class
import java.io.*;
public class Score {
	public static String scoree,k1;
	public static int k;
	public static int score;
	public Score(int score) throws IOException {
		scoree=Integer.toString(score);
		Score.score=score;
		filein();
		fileout();
		filein();
	}
	 public static void fileout() throws IOException
	 {
		 if(score>k)
		 {
		BufferedWriter bf=new BufferedWriter(new FileWriter("play.txt"));
		bf.write(scoree);
		bf.close();
		}
	 }
	 public static void filein() throws IOException
	 {
try{
	BufferedReader br=new BufferedReader(new FileReader("play.txt"));
	k1=br.readLine();
	k=Integer.valueOf(k1);
}
catch(FileNotFoundException e)
{
	BufferedWriter bf=new BufferedWriter(new FileWriter("play.txt"));
		bf.write("0");
		bf.close();
}
	 }
	 public static int getK() {
		 return(k);
	 }
	 
	

}
