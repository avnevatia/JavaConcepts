package multithread.fileio.example1;

import java.io.IOException;

public class AdderTask implements Runnable
{

  private final String inputFileName;
  private final String outputFileName;

  public AdderTask( String inputFileName, String outputFileName )
  {
    this.inputFileName = inputFileName;
    this.outputFileName = outputFileName;
  }

  @Override
  public void run()
  {
    try
    {
      Adder add = new Adder( inputFileName, outputFileName );
      add.doAdd();
    }
    catch( IOException e )
    {
      e.printStackTrace();
    }
  }

}
