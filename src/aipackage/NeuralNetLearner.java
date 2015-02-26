/**
 * @author Zhiqiang Ren 
 * date: Feb. 4th. 2012
 * 
 */
package aipackage;

import java.io.FileNotFoundException;

/**
 * @author Zhiqiang Ren
 * 
 */
public class NeuralNetLearner {
  /**
   * @param args
   * @throws FileNotFoundException
   */
  public static void main(String[] args) throws FileNotFoundException {
    /*
    int[] layers = { 6, 2, 1 }; // three layers
    NeuralNet net = new NeuralNet(layers);
    net.connectTest();
    
    double[][] inputvs = { { 1, 1, 0, 0, 0, 0 }, { 1, 0, 1, 0, 0, 0 },
      { 1, 0, 0, 1, 0, 0 }, { 1, 0, 0, 0, 1, 0 },
      { 1, 0, 0, 0, 0, 1 }, { 0, 1, 1, 0, 0, 0 },
      { 0, 1, 0, 1, 0, 0 }, { 0, 1, 0, 0, 1, 0 },
      { 0, 1, 0, 0, 0, 1 }, { 0, 0, 1, 1, 0, 0 },
      { 0, 0, 1, 0, 1, 0 }, { 0, 0, 1, 0, 0, 1 },
      { 0, 0, 0, 1, 1, 0 }, { 0, 0, 0, 1, 0, 1 },
      { 0, 0, 0, 0, 1, 1 } };
    
    double[][] outputvs = { { 0 }, { 0 }, { 1 }, { 1 }, { 1 }, { 0 },
      { 1 }, { 1 }, { 1 }, { 1 }, { 1 }, { 1 }, { 0 }, { 0 }, { 0 } };
    
    for (int n = 0; n < 300; ++n) {
      net.train(inputvs, outputvs, 1);
    }
    
    net.errorrate(inputvs, outputvs, 'a');
    System.out.println("============================");
    
    int[] layers2 = { 2, 2 }; // two layers
    NeuralNet net2 = new NeuralNet(layers2);
    net2.connectAll();
    
    double[][] inputvs2 = { { 0, 0 }, { 0, 1 }, { 1, 1 }, { 1, 0 } };
    double[][] outputvs2 = { { 0, 0 }, { 0, 1 }, { 1, 1 }, { 0, 1 } };
    
    for (int n = 0; n < 100; ++n) {
      net2.train(inputvs2, outputvs2, 1);
    }
    
    net2.errorrate(inputvs2, outputvs2, 'a');
    
    System.out.println("============================");
    */
    
    
    //Now we are going to train the neural net using the credit data
    // 'a' is to deliniate which type of data it is. 'a' stands for credit.
    DataProcessor data = new DataProcessor("crx.data.training", 'a'); 
    //3 Layers
    int[] layers3 = { 15, 30, 1 }; 
    NeuralNet net3 = new NeuralNet(layers3);
    net3.connectAll();
    
    double[][] inputvs3 = data.m_inputvs;
    double[][] outputvs3 = data.m_outputvs;
    
    for (int n = 0; n < 300; ++n) {
      net3.train(inputvs3, outputvs3, .5);
    }
    System.out.print("crx.data.training results: ");
    net3.errorrate(inputvs3, outputvs3, 'a');
    
    System.out.println("============================");
    
    
    //Now we are going to test the neural net using the credit data
    // 'a' is to deliniate which type of data it is. 'a' stands for credit.
    data = new DataProcessor("crx.data.testing", 'a');
    net3.connectAll();
    inputvs3 = data.m_inputvs;
    outputvs3 = data.m_outputvs;
    
    System.out.print("crx.data.testing results: ");
    net3.errorrate(inputvs3, outputvs3, 'a');
    
    System.out.println("============================");
    
    //Now we are going to train the neural net using the BUBIL data
    // 'b' is to deliniate which type of data it is. 'b' stands for BUBIL.
    DataProcessor data_bubil = new DataProcessor("BUBIL.training", 'b');
    int[] layerss3 = { 4, 30, 1 }; // two layers
    NeuralNet nett3 = new NeuralNet(layerss3);
    nett3.connectAll();
    
    double[][] inputvs_lense3 = data_bubil.m_inputvs;
    double[][] outputvs_lense3 = data_bubil.m_outputvs;
    
    for (int n = 0; n < 300; ++n) {
      nett3.train(inputvs_lense3, outputvs_lense3, 1);
    }
    
    System.out.print("BUBIL.training results: ");
    nett3.errorrate(inputvs_lense3, outputvs_lense3, 'b');
    
    System.out.println("============================");
    
    
    //Now we are going to test the neural net using the BUBIL data
    // 'b' is to deliniate which type of data it is. 'b' stands for BUBIL.
    data_bubil = new DataProcessor("BUBIL.testing", 'b');
    nett3.connectAll();
    inputvs3 = data_bubil.m_inputvs;
    outputvs3 = data_bubil.m_outputvs;
    System.out.print("BUBIL.testing results: ");
    nett3.errorrate(inputvs3, outputvs3, 'b');
    
    
    
    System.out.println("============================");
    
    
    
    //Now we are going to train the neural net using the lenses data
    // 'c' is to deliniate which type of data it is. 'c' stands for credit.
    DataProcessor data_lense = new DataProcessor("lenses.training", 'c');
    int[] layerss4 = { 4, 30, 1 }; // two layers
    NeuralNet nett4 = new NeuralNet(layerss4);
    nett4.connectAll();
    
    double[][] inputvs_lense4 = data_lense.m_inputvs;
    double[][] outputvs_lense4 = data_lense.m_outputvs;
    
    for (int n = 0; n < 300; ++n) {
      nett4.train(inputvs_lense4, outputvs_lense4, 1);
    }
    
    System.out.print("lenses.training results: ");
    nett4.errorrate(inputvs_lense4, outputvs_lense4, 'c');
    
    System.out.println("============================");
    
    
    //Now we are going to test the neural net using the lenses data
    // 'c' is to deliniate which type of data it is. 'c' stands for credit.
    data_lense = new DataProcessor("lenses.testing", 'c');
    nett4.connectAll();
    inputvs3 = data_lense.m_inputvs;
    outputvs3 = data_lense.m_outputvs;
    System.out.print("lenses.testing results: ");
    nett4.errorrate(inputvs3, outputvs3, 'c');
    return;
  }
}