package jvm.practice1;

import java.lang.reflect.Method;

public class SimpleUnitTester
{
  public int execute( Class clazz ) throws Exception
  {
    int failedCount = 0;

    Object objRef = clazz.newInstance();
    for( Method m : clazz.getDeclaredMethods() )
    {
      if( m.getName().startsWith( "test" ) && m.getReturnType() == boolean.class && !((boolean) m.invoke( objRef, (Object[]) null )) )
      {
        ++failedCount;
      }
    }

    return failedCount;
  }

  public static void main( String args[] )
  {
    SimpleUnitTester sut = new SimpleUnitTester();

    try
    {
      System.out.println( sut.execute( Class.forName( "jvm.practice1.Reflection" ) ) );
    }
    catch( Exception e )
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
