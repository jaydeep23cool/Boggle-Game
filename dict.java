import java.io.BufferedReader;

import java.io.File;

import java.io.FileReader;

import java.io.IOException;

import java.util.Iterator;

import java.util.LinkedHashMap;

import java.util.Map.Entry;

import java.util.Set;

public class dict {
   
        LinkedHashMap dictionary = new LinkedHashMap();
        public dict() throws IOException {
        

        BufferedReader reader = new BufferedReader(new FileReader(new File("inut.txt")));

        String inputLine = null;
        while((inputLine = reader.readLine()) != null) {


            String[] words = inputLine.split("\\s+");
           

            if(inputLine.equals(""))

                continue;

            for(String word: words) {

                // Remove any commas and dots.

                word = word.replace(".", "");

                word = word.replace(",", "");
                 
                   // Integer val = (Integer) dictionary.get(word);     
                    dictionary.put(word,1);
           }
        }
             reader.close();
        
    }
    boolean isWord(String str)  throws IOException
    {
        return dictionary.get(str)!=null;
    }
}

