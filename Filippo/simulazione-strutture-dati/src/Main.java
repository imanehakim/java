public class Main {

    public static void main(String[] args) {

        List l = new Node("1NA",new Node("2N",new Node("3",new Nil())));

        l = l.add("New",1);

        System.out.println(l.countEndsWith("N")); // questo dovrebbe stampare 2
        System.out.println(l.countEndsWith("A"));//prova
        System.out.println("Inverti l'array");

        List reverse = new Nil();


        System.out.println(reverse);
    }
}
