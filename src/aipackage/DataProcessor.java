package aipackage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Locale;

public class DataProcessor {
    static String[] A1_cand = { "b", "a", "?" };
    static String[] A4_cand = { "u", "y", "l", "t", "?" };
    static String[] A5_cand = { "g", "p", "gg", "?" };
    static String[] A6_cand = { "c", "d", "cc", "i", "j", "k", "m", "r", "q",
            "w", "x", "e", "aa", "ff", "?" };
    static String[] A7_cand = { "v", "h", "bb", "j", "n", "z", "dd", "ff", "o", "?" };
    static String[] A9_cand = { "t", "f", "?" };
    static String[] A10_cand = { "t", "f", "?" };
    static String[] A12_cand = { "t", "f", "?" };
    static String[] A13_cand = { "g", "p", "s", "?" };

    public double[][] m_inputvs;
    public double[][] m_outputvs;

    private List<CreditData> m_datas;
    private List<LensesData> m_lenses;
    private List<BUBILData> m_bubil;

    class CreditData {
        public CreditData(double[] inputs, double[] outputs) {
            m_inputs = inputs;
            m_outputs = outputs;
        }

        public double[] m_inputs;
        public double[] m_outputs;
    }
    
    //Lenses data class. Same as the credit data class
    class LensesData {
      public LensesData(double[] inputs, double[] outputs) {
        m_inputs = inputs;
        m_outputs = outputs;
      }
      
      public double[] m_inputs;
      public double[] m_outputs;
    }
    
    //BUBIL data class. Same as the credit data class
    class BUBILData {
      public BUBILData(double[] inputs, double[] outputs) {
        m_inputs = inputs;
        m_outputs = outputs;
      }
      
      public double[] m_inputs;
      public double[] m_outputs;
    }

    double cvtDouble(String [] candidates, String name) {
        for (int i = 0; i < candidates.length; ++i) {
            if (candidates[i].equals(name)) {
                return i;
            }
        }
        return candidates.length;
    }

    public DataProcessor(String aFileName, char key ) throws FileNotFoundException {
      //If the key is a, that means that the data type is credit
      if (key == 'a') {
        m_datas = new ArrayList<CreditData>();
        Scanner s = null;
        
        FileReader f = new FileReader(aFileName);
        
        try {
          s = new Scanner(new BufferedReader(f));
          s.useLocale(Locale.US);
          
          while (s.hasNextLine()) {
            String nextLine = s.nextLine();
            CreditData data = processLine(nextLine);
            m_datas.add(data);
          }
        } finally {
          s.close();
        }
        
        int i = 0;
        m_inputvs = new double[m_datas.size()][];
        m_outputvs = new double[m_datas.size()][];
        for (CreditData data : m_datas) {
          m_inputvs[i] = data.m_inputs;
          m_outputvs[i] = data.m_outputs;
          ++i;
        }
        m_inputvs = normalize(m_inputvs);
        
      }
      
      else if (key == 'b') {
        m_bubil = new ArrayList<BUBILData>();
        Scanner s = null;
        
        FileReader f = new FileReader(aFileName);
        
        try {
          s = new Scanner(new BufferedReader(f));
          s.useLocale(Locale.US);
          
          while (s.hasNextLine()) {
            BUBILData data = processsLine(s.nextLine());
            m_bubil.add(data);
          }
        } finally {
          s.close();
        }
        
        int i = 0;
        m_inputvs = new double[m_bubil.size()][];
        m_outputvs = new double[m_bubil.size()][];
        for (BUBILData data : m_bubil) {
          m_inputvs[i] = data.m_inputs;
          m_outputvs[i] = data.m_outputs;
          ++i;
        }
        m_inputvs = normalize(m_inputvs);
      }
      
      //If the key is c, that means that the data type is lenses
      else if (key == 'c') {
        m_lenses = new ArrayList<LensesData>();
        Scanner s = null;
        
        FileReader f = new FileReader(aFileName);
        
        try {
          s = new Scanner(new BufferedReader(f));
          s.useLocale(Locale.US);
          
          while (s.hasNextLine()) {
            LensesData data = procesLine(s.nextLine());
            m_lenses.add(data);
          }
        } finally {
          s.close();
        }
        
        int i = 0;
        m_inputvs = new double[m_lenses.size()][];
        m_outputvs = new double[m_lenses.size()][];
        for (LensesData data : m_lenses) {
          m_inputvs[i] = data.m_inputs;
          m_outputvs[i] = data.m_outputs;
          ++i;
        }
        m_inputvs = normalize(m_inputvs);
      }
    }

    private double nextDouble(Scanner s) {
        if (s.hasNextDouble()) {
            return s.nextDouble();
        } else {
            s.next();
            return 0.0;
        }
    }

    public CreditData processLine(String line) {
        Scanner scanner = new Scanner(line);
        scanner.useDelimiter(",");

        double[] inputs = new double[15];
        double[] outputs = new double[1];

        inputs[0] = cvtDouble(A1_cand, scanner.next());
        inputs[1] = nextDouble(scanner);
        inputs[2] = nextDouble(scanner);
        inputs[3] = cvtDouble(A4_cand, scanner.next());
        inputs[4] = cvtDouble(A5_cand, scanner.next());
        inputs[5] = cvtDouble(A6_cand, scanner.next());
        inputs[6] = cvtDouble(A7_cand, scanner.next());
        inputs[7] = nextDouble(scanner);
        inputs[8] = cvtDouble(A9_cand, scanner.next());
        inputs[9] = cvtDouble(A10_cand, scanner.next());
        inputs[10] = nextDouble(scanner);
        inputs[11] = cvtDouble(A12_cand, scanner.next());
        inputs[12] = cvtDouble(A13_cand, scanner.next());
        inputs[13] = nextDouble(scanner);
        inputs[14] = nextDouble(scanner);

        String output = scanner.next();
        if (output.equals("+")) {
            outputs[0] = 1.0;
        } else {
            outputs[0] = 0.0;
        }
        return new CreditData(inputs, outputs);
    }
    
    //This is how you process Lenses. It is similar to how you process Credit
    public BUBILData processsLine(String line) {
      Scanner scanner = new Scanner(line);
      scanner.useDelimiter(",");
      
      double[] inputs = new double[4];
      double[] outputs = new double[1];
      
      inputs[0] = scanner.nextDouble();
      inputs[1] = scanner.nextDouble();
      inputs[2] = scanner.nextDouble();
      inputs[3] = scanner.nextDouble();
      
      double output = scanner.nextDouble();
      if (output == 1) {
        output = 0;
      } else if (output == 2) {
        output = 0.5;
      } else {
        output = 1;
      }
      outputs[0] = output;
      return new BUBILData(inputs, outputs);
    }
    
    
    //This is how you process Lenses. It is similar to how you process Credit
    public LensesData procesLine(String line) {
      Scanner scanner = new Scanner(line);
      scanner.useDelimiter(",");
      
      double[] inputs = new double[4];
      double[] outputs = new double[1];
      
      inputs[0] = scanner.nextDouble();
      inputs[1] = scanner.nextDouble();
      inputs[2] = scanner.nextDouble();
      inputs[3] = scanner.nextDouble();
      
      double output = scanner.nextDouble();
      if (output == 1) {
        output = 0;
      } else if (output == 2) {
        output = 0.5;
      } else {
        output = 1;
      }
      outputs[0] = output;
      return new LensesData(inputs, outputs);
    }
    
    //Normalizes the distribution of the m_inputs
    public double[][] normalize(double[][] input) {
      //Sum up inputs
      for (int i = 0; i < input[0].length; i++) {
        double sum = 0;
        for (int j = 0; j < input.length; j++) {
          sum += input[j][i];
        }
        //Find the mean of the inputs
        double mean = sum / input.length;
        //Find the standard deviation (sdev)
        double sdev = 0;
        for (int j = 0; j < input.length; j++) {
          sdev += Math.pow(input[j][i] - mean, 2);
        }
        //Calculate sigma
        double sigma = Math.sqrt(sdev / input.length);
        //Find the Z value for each
        for (int j = 0; j < input.length; j++) {
          input[j][i] = (input[j][i] - mean) / sigma;
        }
      }
      return input;
    }
    
}


