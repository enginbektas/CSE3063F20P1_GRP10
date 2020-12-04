public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("hello world");
        Log newLog = new Log();
        newLog.write("message");
        newLog.write("message2");
        newLog.write("message3");
    }
}
