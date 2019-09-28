package multithread.fileio.example1;

public class Main
{

  public static void main( String[] args )
  {
    String filePath = "C:\\ANKDEV\\PROJ\\TempFilesIO\\";
    String[] inputFiles =
    { "InFile1.txt", "InFile2.txt", "InFile3.txt" };
    String[] outputFiles =
    { "OutFile1.txt", "OutFile2.txt", "OutFile3.txt" };

    Thread[] threads = new Thread[inputFiles.length];

    for( int i = 0; i < inputFiles.length; i++ )
    {
      threads[i] = new Thread( new AdderTask( filePath + inputFiles[i], filePath + outputFiles[i] ) );
      threads[i].start();
    }

    try
    {
      for( Thread thread : threads )
      {
        //This will make main thread to wait till background threads are finished.
        thread.join();
      }
    }
    catch( InterruptedException e )
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

}
