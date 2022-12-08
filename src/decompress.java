import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Math;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;

public class decompress {

    Scanner sc = new Scanner(System.in);
    int no_char;
    ArrayList<Character> chr;
    ArrayList<Double> lower;
    ArrayList<Double> upper;
    String s;

    public decompress(int no, String s) {
        no_char = no;
        this.s = s;
        chr = new ArrayList<Character>();
        lower = new ArrayList<Double>();
        upper = new ArrayList<Double>();
    }

    public void add_char_value_upper_and_lower() {
        try {
            File myObj = new File("C:\\Users\\Youssef Dieaa\\IdeaProjects" +
                    "\\Arithmtic_binary_decompression\\myfile.txt");
            Scanner myReader = new Scanner(myObj);
            for (int i = 0; i < no_char; i++) {
                char c = myReader.next().charAt(0);
                double low = myReader.nextDouble();
                double high = myReader.nextDouble();
                chr.add(c);
                lower.add(low);
                upper.add(high);

            }


            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


    }

    public double smallest_range() {
        add_char_value_upper_and_lower();
        double intialize_range = 1;
        for (int i = 0; i < no_char; i++) {
            if (upper.get(i) - lower.get(i) < intialize_range) {
                intialize_range = upper.get(i) - lower.get(i);
            }
        }

        return intialize_range;
    }

    public int find_binary_range() {
        double x = smallest_range();
        double temp = 1;
        int counter = 1;
        while (temp > x) {

            temp = 1 / (Math.pow(2, counter));
            counter++;
        }
        return counter - 1;
    }

    public String decompress() {
        int count = 0;
        int k = find_binary_range();
        String temp = "";
        String answer = "";
        double low1 = 0;
        double high1 = 0;
        int start = 0;
        boolean ok = true;
        while (ok) {
            if (start == k)//110001100000
                ok = false;
            for (int i = start; i < k + start; i++) {
                temp = temp + s.charAt(i);
            }
            double ans = 0;
            for (int i = 0; i < temp.length(); i++) {
                if (temp.charAt(i) == '1') {
                    ans = ans + Math.pow(2, temp.length() - i - 1);
                }
            }

            if (count != 0) {
                ans = (ans / (Math.pow(2, k)));
                ans = ((ans - low1) / (high1 - low1));
                for (int i = 0; i < no_char; i++) {
                    if (ans > lower.get(i) && ans < upper.get(i)) {
                        double low2 = low1;
                        double high2 = high1;
                        high1 = upper.get(i);
                        low1 = lower.get(i);
                        answer = answer + chr.get(i);
                        low1 = low2 + (high2 - low2) * low1;
                        high1 = low2 + (high2 - low2) * high1;
                        break;
                    }
                }
            } else {
                ans = (ans / (Math.pow(2, k)));
                for (int i = 0; i < no_char; i++) {
                    if (ans > lower.get(i) && ans < upper.get(i)) {
                        high1 = upper.get(i);
                        low1 = lower.get(i);
                        answer = answer + chr.get(i);
                        low1 = 0 + (1) * low1;
                        high1 = 0 + (1) * high1;
                        count++;
                        break;
                    }
                }

            }
            while (true) {
                if (low1 > 0.5) {
                    low1 = (low1 - 0.5) * 2;
                    high1 = (high1 - 0.5) * 2;
                    start++;
                } else if (high1 < 0.5) {

                    high1 = high1 * 2;
                    low1 = low1 * 2;
                    start++;
                } else {
                    temp = "";
                    break;
                }
            }
        }

        return answer;
    }
}



