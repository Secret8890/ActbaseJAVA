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

    // ���α��� �� �߻������� �������� �Լ����������̽�
    @FunctionalInterface
    public interface Function {
        void check(int number);
    }

    private File f = null;
    private FileOutputStream fos = null;
    private PrintWriter pw = null;
    private static int count = 0;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // �ߺ����ſ� ���ÿ� ���� ��� ������ ����ϴ� �޼ҵ�
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
                            // �����迭�� ������������ ��ġ����ĥ�� �ߺ���Ҵ� ��¾���
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
    // �Է��� ���� ��Ʈ�� ��ȯ�ϴ� �޼ҵ�
    int inputToInt() {
        System.out.println("����Ͻ÷��� ���ڸ� �Է����ּ���.");
        try {
            String input = br.readLine().trim();
            if (input.isEmpty()) {
                System.out.println("���� �Է����ּ���.");
                inputToInt();
            } else {
                count = Integer.parseInt(input);
            }
        } catch (NumberFormatException ne) {
            System.out.println("���ڸ� �Է����ּž� �մϴ�.");
            inputToInt();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return count;
    }
    // ���� Ŭ���� �ڹٰ�ü ������ ���� �޼ҵ� �����ϴ� �޼ҵ�
    static void execFunc() {
        Lambda2 lambda = new Lambda2();
        int count = lambda.inputToInt();
        lambda.loopFunc(count);
    }
    public static void main(String[] args) {
        execFunc();
    }
}
