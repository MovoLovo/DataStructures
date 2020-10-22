public class Main {
    public static void main(String[] args) {
        for(long i = 0; i < 1000; i++){
            System.out.println(i + ": " + fib_recursive(i));
        }
    }

    public static int fib_iterative(int n){
        int n1 = 0, n2 = 1, swap;
        for(int i = 0; i < n; i++){
            swap = n2;
            n2 += n1;
            n1 = swap;
        }
        return n2;
    }

    public static long fib_recursive(long n){
        if(n == 1 || n == 0){
            return 1;
        }else{
            return fib_recursive(n - 1) + fib_recursive(n - 2);
        }
    }
}
