import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter output_file = new PrintWriter("C:\\Users\\Youssef Dieaa\\IdeaProjects\\Arithmtic_binary_decompression\\f.txt");
        decompress c=new decompress(3,"110001100000");
        output_file.println("your decoded code is : " + c.decompress());
        output_file.close();




    }
}
