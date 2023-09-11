import java.util.Random;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Data data = new Data();
        Random random = new Random();
        String[] texts = new String[100_000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = Data.generateText("abc", 3 + random.nextInt(3));
        }
        Thread palindrome = new Thread(() -> {
            for (String text : texts) {
                if (Data.isPalindrome(text) && !Data.isSameChart(text)) {
                    Data.incrementCounter(text.length());
                }
            }
        });
        palindrome.start();

        Thread sameChar = new Thread(() -> {
            for (String text : texts) {
                if (Data.isSameChart(text)) {
                    Data.incrementCounter(text.length());
                }
            }
        });
        sameChar.start();

        Thread order = new Thread(() -> {
            for (String text : texts) {
                if (Data.isSameChart(text) && Data.isOrder(text)) {
                    Data.incrementCounter(text.length());
                }
            }
        });
        order.start();

        palindrome.join();
        sameChar.join();
        order.join();

        System.out.println("Красивых слова длиной 3: " + Data.counter3 + " шт");
        System.out.println("Красивых слова длиной 4: " + Data.counter4 + " шт");
        System.out.println("Красивых слова длиной 5: " + Data.counter5 + " шт");
    }
}
