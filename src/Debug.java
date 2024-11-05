import com.google.gson.*;

import java.io.File;
import java.io.IOException;

public class Debug {
    public static void main(String[] args) throws IOException {
        int n = 1;
        String a = """
                %d
                """.formatted(n);

        System.out.println(a);


    }
}
