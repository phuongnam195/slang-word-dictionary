package extension;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

// Tham khảo: https://www.daniweb.com/programming/software-development/threads/406909/how-to-append-in-objectoutputstream

public class AppendableObjectOutputStream extends ObjectOutputStream {

    public AppendableObjectOutputStream(OutputStream out) throws IOException {
      super(out);
    }
  
    @Override
    protected void writeStreamHeader() throws IOException {
      // do not write a header
    }
  
  }
