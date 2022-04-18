package item9;

import java.io.*;

public class Codes {

    // 전통적 방법 - try-finally -> 더 이상 자원을 회수하는 최선책이 아니다.
    static String firstLineOfFile1(String path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));

        try {
            return br.readLine();
        } finally {
            br.close();
        }
    }

    static final int BUFFER_SIZE = 16;
    // 자원이 두 개 이상이면 try-finally 방식은 너무 지저분하다
    static void copy1(String src, String dst) throws IOException {
        InputStream in = new FileInputStream(src);

        try {
            OutputStream out = new FileOutputStream(dst);

            try {
                byte[] buf = new byte[BUFFER_SIZE];
                int n;
                while((n = in.read(buf)) >= 0) {
                    out.write(buf, 0, n);
                }
            } finally {
                out.close();
            }
        } finally {
            in.close();
        }
    }

    // try-with-resources로 재작성
    static String firstLineOfFile2(String path) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            return br.readLine();
        }
    }

    // try-with-resources로 재작성
    static void copy2(String src, String dst) throws IOException {
        try (InputStream in = new FileInputStream(src); OutputStream out = new FileOutputStream(dst)) {
            byte[] buf = new byte[BUFFER_SIZE];
            int n;
            while ((n = in.read(buf)) >= 0) {
                out.write(buf, 0, n);
            }
        }
    }

    // try-with-resources와 catch 사용.
    static String firstLineOfFile3(String path, String defaultVal) {
        try(BufferedReader br = new BufferedReader(new FileReader(path))) {
            return br.readLine();
        } catch (IOException e) { // IOException 발생시 예외를 던지는 대신 기본값 반환
            return defaultVal;
        }
    }
}
