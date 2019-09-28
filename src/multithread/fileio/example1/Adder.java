package multithread.fileio.example1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Adder
{

  private final String inputFileName;
  private final String outputFileName;

  public Adder( String inputFileName, String outputFileName )
  {
    this.inputFileName = inputFileName;
    this.outputFileName = outputFileName;
  }

  public void doAdd() throws IOException
  {
    String line;
    long sum = 0;
    try (BufferedReader reader = Files.newBufferedReader( Paths.get( inputFileName ) ))
    {
      while( (line = reader.readLine()) != null )
      {
        sum += Integer.parseInt( line );
      }
    }

    try (BufferedWriter writer = Files.newBufferedWriter( Paths.get( outputFileName ) ))
    {
      writer.write( Long.toString( sum ) );
      writer.flush();
    }
  }

}
