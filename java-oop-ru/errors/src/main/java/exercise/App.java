package exercise;

// BEGIN
public class App {
    public static void printSquare(Circle cir) throws NegativeRadiusException {
        try {
            System.out.println((int) Math.round(cir.getSquare()));
        } catch (NegativeRadiusException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Вычисление окончено");
        }


    }
}
// END
