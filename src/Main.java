import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static int sentimentScore = 0;
    private static final String wordFile = "wordFile.txt";
    private static ArrayList<String> wordRecord = new ArrayList();
    private static LinkedList sentence = new LinkedList();

    public static void main(String[] args) {
        System.out.print("\u001B[34m[I]\u001B[39m Enter word or sentence : ");
        Scanner sentenceObj = new Scanner(System.in);
        String[] wordsArray = sentenceObj.nextLine().split(" ");
        // Insert each word from the array into the linked list
        for (int i = 0; i < wordsArray.length; i++) {
            String word = wordsArray[i];
            sentence.insert(word);
        }
        //Reading the wordFile.txt and getting it to ArrayList
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(wordFile))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                wordRecord.add(line);
            }
        } catch (IOException ex) {
            System.out.println("\u001B[31m[E]\u001B[39m An error occurred while reading the file.");
            ex.printStackTrace();
        }

        //Getting elements fron the sentence linked list and check if it is in the wordFile.txt
        outer:while (sentence.head != null){
            for(int i = 0;i < wordRecord.size();i++){
                String[] wordChecker = wordRecord.get(i).split(",");
                //If sentence is in the wordFile.txt add the sentiment score to current sentiment score
                if(sentence.head.data.equalsIgnoreCase(wordChecker[0])){
                    sentimentScore += Integer.parseInt(wordChecker[1]);
                    //Remove the checkd word from the sentence
                    sentence.removeFromFront();
                    continue outer;
                }
            }
            //If word is not found the wordFile.txt remove the word from sentece
            sentence.removeFromFront();
        }
        //Printing the sentiment score and sentiment type of the sentence or word
        if(sentimentScore == 0){
            System.out.println("Sentiment score --> " + sentimentScore + " \u001B[33m(Neutral)\u001B[39m");
        } else if (sentimentScore > 0 ) {
            System.out.println("Sentiment score --> " + sentimentScore + " \u001B[32m(Positive)\u001B[39m");

        }else{
            System.out.println("Sentiment score --> " + sentimentScore + " \u001B[31m(Negative)\u001B[39m");
        }
    }
}