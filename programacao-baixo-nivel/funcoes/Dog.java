public class Dog {
    private String name;
    
    public Dog(String name) {
      this.name = name;
    }
    
    public String getName() { return name; }
    
    public static void foo(Dog d) {
      System.out.println("In foo");
      System.out.println("Is Max? " +d.getName().equals("Max"));
      d = new Dog("Fifi");
      System.out.println("After d = new Dog(...)");
      System.out.println("Is Fifi? "+d.getName().equals("Fifi"));
    }

    public static void main(String[] args) {
      System.out.println("Creating \"Max\"");
      Dog aDog = new Dog("Max");
      Dog oldDog = aDog;
      
      foo(aDog);
      System.out.println("After foo(aDog)");
      System.out.println("Is Max? " +aDog.getName().equals("Max"));
      System.out.println("Is Fifi? "+aDog.getName().equals("Fifi"));
      System.out.println("Are the same? "+(aDog == oldDog));
    }
}

