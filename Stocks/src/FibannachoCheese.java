public class FibannachoCheese {
    public static void main(String[] args) {
        fib(9);
    }

    public static int fib(int x){
        System.out.println(x);
        if(x < 2) {
            return 1;
        }
        return fib(x - 1) + fib(x - 2);
    }
}
