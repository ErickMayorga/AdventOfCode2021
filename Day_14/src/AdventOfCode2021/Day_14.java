package AdventOfCode2021;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Stack;

public class Day_14 {
    private static FileReader fr = null;
    private static Stack<String[]> rules = new Stack<>();
    private static Stack<Character> letters = new Stack<>();

    public static Stack<String> readFile(String path){
        Stack<String> lines = new Stack<>();
        try {
            File file = new File(path);
            fr = new FileReader (file);
            BufferedReader br = new BufferedReader(fr);

            String line;
            while((line= br.readLine())!=null){
                //System.out.println(line);
                lines.push(line);
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if( null != fr ){
                    fr.close();
                }
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
        return lines;
    }

    public static Stack<Character> setRules(Stack<Character> polymer){
        Stack<Character> polymerAux  = new Stack<>();
        String previus = "";
        Character insertion = null;
        int n = polymer.size()-1;
        polymerAux.push(polymer.firstElement());
        for(int i=0; i<n; i++){
            previus = String.valueOf(polymer.get(i)) + String.valueOf(polymer.get(i+1));
            insertion = findInsertion(previus);
            polymerAux.push(insertion);
            polymerAux.push(previus.charAt(1));
        }
        return polymerAux;
    }

    public static Character findInsertion(String previus){
        Character result = null;
        for(String[] rule: rules){
            if(rule[0].equals(previus)){
                result = rule[1].charAt(0);
                break;
            }
        }
        return result;
    }

    public static void setLetters(){
        for(String[] s: rules){
            if(!letters.contains(s[0].charAt(0))){
                letters.push(s[0].charAt(0));
            }
            if(!letters.contains(s[0].charAt(1))){
                letters.push(s[0].charAt(1));
            }
            if(!letters.contains(s[1].charAt(0))){
                letters.push(s[1].charAt(0));
            }
        }
    }

    public static int contLetter(Character letter, Stack<Character> polymer){
        int cont = 0;
        for(Character c: polymer){
            if(c == letter){
                cont++;
            }
        }
        return cont;
    }

    public static int difference(Stack<Character> polymer){
        double min = Double.POSITIVE_INFINITY;
        int max = 0, cont = 0;
        Character maxChar = null;
        Character minChar = null;

        for(Character c: polymer){
            cont = contLetter(c,polymer);
            if(cont < min){
                min = cont;
                minChar = c;
            }
            if(cont > max){
                max = cont;
                maxChar = c;
            }
        }
        return max-(int)min;
    }

    public static void main(String[] args) {
        String path = "files/InsertionRules.txt";
        Stack<String> lines = readFile(path);

        System.out.println("PART I");
        Stack<Character> polymer = new Stack<>();
        for(char c: lines.get(0).toCharArray()){
            polymer.push(c);
        }
        lines.remove(0);

        for(String s: lines){
            rules.push(s.split(" -> "));
        }

        setLetters();

        int n =40;
        for(int i=0; i<n; i++){
            polymer = setRules(polymer);
            System.out.println("Iteration: " + (i+1));
        }
        /*
        for(Character c: polymer){
            System.out.print(c);
        }
        System.out.println();


        for(Character c: letters){
            System.out.println(c + ": " + contLetter(c,polymer));
        }
         */
        System.out.println(difference(polymer));
    }
}
