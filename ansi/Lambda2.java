import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Lambda2 {

    // 내부구현 전 추상적으로 만들어놓은 함수형인터페이스
    @FunctionalInterface
    public interface Function {
        void check(int number);
    }

    private File f = null;
    private FileOutputStream fos = null;
    private PrintWriter pw = null;
    private static int count = 0;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // 중복제거와 동시에 파일 출력 루프를 담당하는 메소드
    void loopFunc(int count) {
        try {
            f = new File("out.txt");
            fos = new FileOutputStream(f, false);
            pw = new PrintWriter(fos, true);
            Function func = (num) -> {
                List<Integer> numberBox = Arrays.asList(1, 2, 3, 4, 5);
                IntStream.rangeClosed(1, 10).forEach(row -> {
                    IntStream.rangeClosed(1, 10).forEach(i -> {
                        if (i == num && numberBox.contains(num)) {
                            // 가변배열과 현재포지션의 위치가겹칠때 중복요소는 출력안함
                        } else {
                            // System.out.print(i + " ");
                            pw.write(i + " ");
                        }
                    });
                    // System.out.println();
                    pw.println();
                });
            };
            pw.flush();
            func.check(count);
        } catch (FileNotFoundException fe) {
            fe.printStackTrace();
        } finally {
            if (pw != null)
                pw.close();
            if (fos != null)
                try {
                    fos.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            if (br != null) {
                try {
                    br.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
    }
    // 입력한 값을 인트로 변환하는 메소드
    int inputToInt() {
        System.out.println("계속하시려면 숫자를 입력해주세요.");
        try {
            String input = br.readLine().trim();
            if (input.isEmpty()) {
                System.out.println("값을 입력해주세요.");
                inputToInt();
            } else {
                count = Integer.parseInt(input);
            }
        } catch (NumberFormatException ne) {
            System.out.println("숫자를 입력해주셔야 합니다.");
            inputToInt();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return count;
    }
    // 람다 클래스 자바객체 생성과 내부 메소드 실행하는 메소드
    static void execFunc() {
        Lambda2 lambda = new Lambda2();
        int count = lambda.inputToInt();
        lambda.loopFunc(count);
    }
    public static void main(String[] args) {
        execFunc();
    }
}
