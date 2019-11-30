package jvm;

/**
 * HOW TO RUN: cd C:\ANKDEV\PROJ\myWorkspace\JavaConcepts\src\jvm\
 *             java -verbose:class ClassInitializationDemo.java
 *                          OR
 * Write "-verbose:class" in jvm param of Eclipse Run->configuration
 * 
 * Demonstrates, (i) class is NOT loaded on accessing a compile-time constant. Constant is fetched from .class file (ii) On accessing a non compile-time
 * constant, class & its super-class are LOADED and INITIALIZED (iii) On instantiating the class, it is loaded from memory (loading was done previous step) and
 * (a) superclass constructor is run, i.e., CONSTRUCTOR CHAINING (b) its instance variables are initialized and instance initializer block is run (c) its own
 * constructor is run
 */

interface Superinterface
{
  /**
   * 17. new instance for ClassInitializationDemo will be created and 18th line instance initializer will be called
   *    .
   *    .
   * 19. getInt static method will be called and value will be assigned.
   */
  int STATIC_FINAL3 = new ClassInitializationDemo().getInt();
  /**
   * 20. new instance for ClassInitializationDemo will be created and 18th line instance initializer will be called
   * 21. getInt5 static method will be called and value will be assigned.
   */
  int STATIC_FINAL5 = new ClassInitializationDemo().getInt5();

  static void staticMethod()
  {
    System.out.println( "Superinterface: staticMethod" );
  }
}

class ObjectReference
{
  /**
   * 15. Constructor is called as new instance is created.
   */
  ObjectReference()
  {
    System.out.println( "ObjectReference: constructor" );
  }
}

class Superclass
{
  /**
   * 8. CLASSLOADER_SUBSYSTEM::INITIALIZING_PHASE: Static block executed.
   */
  static
  {
    System.out.println( "Superclass: static initializer" );
  }
  
  /**
   * 12. Instance Initializer is called for parent class first/before child class.
   */
  {
    System.out.println( "Superclass: instance initializer" );
  }

  /**
   * 13. Parent constructor is called next before child class instance initializer or constructor.
   */
  Superclass()
  {
    System.out.println( "Superclass: constructor" );
  }
}

class Subclass extends Superclass implements Superinterface
{
  static final int STATIC_FINAL = 47;
  /**
   * 9. CLASSLOADER_SUBSYSTEM::LOADIN_PHASE::Bootstrap/Primordial class loaded as part of lazy loading.
   */
  static final int STATIC_FINAL2 = (int) (Math.random() * 5);

  // static String stringLiteral = "hello";
  // public static int STATIC_FINAL4 = new ClassInitializationDemo().getInt();
  
  /**
   * 14. CLASSLOADER_SUBSYSTEM::LOADIN_PHASE::Application Classloader: ObjectReference class Loaded.
   *     If this line is moved to last then sequence will change. 14 and 15 will become 15 and 16 and 16 will become 14.
   */
  ObjectReference objectReference = new ObjectReference();
  /**
   * 10. CLASSLOADER_SUBSYSTEM::INITIALIZING_PHASE: Static block executed.
   */
  static
  {
    System.out.println( "Subclass: static initializer" );
    // staticFinal = 47;
  }
  
  /**
   * 17. constructor is called  as new instance is created.
   */
  Subclass()
  {
    System.out.println( "Subclass: constructor" );
  }
  /**
   *  Instance initializer is copied to the beginning of constructor by compiler
   *  
   *  16. Instance initializer is called  as new instance is created.
   */
  {
    System.out.println( "Subclass: instance initializer" );
  }

}

/**
 * 0. CLASSLOADER_SUBSYSTEM::LOADIN_PHASE::Bootstrap/Primordial class loaded.
 * 1. CLASSLOADER_SUBSYSTEM::LOADIN_PHASE::Application Classloader: ClassInitializationDemo class Loaded.
 */
public class ClassInitializationDemo
{
  /**
   * 18. Instance initializer will be called from interface and new keyword is used
   */
  {
    System.out.println( "\nClassInitializationDemo: instance initializer" );
  }
  /**
   * 2. CLASSLOADER_SUBSYSTEM::INITIALIZING_PHASE: Static block executed.
   */
  static
  {
    System.out.println( "\nClassInitializationDemo: static initializer (Initialization Stage)" );
  }

  static int getInt()
  {
    System.out.println( "ClassInitializationDemo:getInt()" );
    return 3;
  }

  static int getInt5()
  {
    System.out.println( "ClassInitializationDemo:getInt5()" );
    return 5;
  }

  /**
   * 3. JVM_CALLS_MAIN_METHOD.
   */
  public static void main( String[] args ) throws Exception
  {
    System.out.println( "\nJVM invoked the main method ... " );
    /**
     * 4. Prints value of Subclass.STATIC_FINAL as it is complie time contastant.
     *    Here Subclass is not loaded as value was copied at complie time only.
     */
    System.out.println( "Subclass.STATIC_FINAL: " + Subclass.STATIC_FINAL );
    // System.out.println("Subclass.stringLiteral: " + Subclass.stringLiteral);
    System.out.println( "Invoking Subclass.STATIC_FINAL2  ... " );
    /**
     * 5. CLASSLOADER_SUBSYSTEM::LOADIN_PHASE::Application Classloader: Superinterface interface Loaded.
     * 6. CLASSLOADER_SUBSYSTEM::LOADIN_PHASE::Application Classloader: Superclass class Loaded.
     * 7. CLASSLOADER_SUBSYSTEM::LOADIN_PHASE::Application Classloader: Subclass class Loaded.
     *  .
     *  .
     * 11. Subclass.STATIC_FINAL2 value printed.
     */
    System.out.println( "Subclass.STATIC_FINAL2: " + Subclass.STATIC_FINAL2 );
    System.out.println( "\nInstantiating Subclass ..." );
    /**
     * As class is already loaded, it will just create a instance now.
     * Subclass instantiation will do from 12-17
     */
    new Subclass();
    /**
     * As interface is already loaded, it will just execute from 17-21.
     *    .
     *    .
     *  Superinterface.STATIC_FINAL3 value will be printed.
     */
    System.out.println( "Superinterface.STATIC_FINAL3: " + Superinterface.STATIC_FINAL3 );
    // Superinterface.staticMethod();
  }
}
