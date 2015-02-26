/**
 * @author Zhiqiang Ren 
 * date: Feb. 4th. 2012
 * 
 */

package aipackage;

import java.util.ArrayList;
import java.util.List;

public class Node {
    public Node(int layer, int pos, boolean isThreshold)
    {
        m_input = 0;
        m_output = 0;
        m_input_conn = new ArrayList<Connection>();
        m_output_conn = new ArrayList<Connection>();
        
        m_beta = 0;
        
        m_pos = pos;
        m_layer = layer;
    }
    
    public void addInputConnection(Connection con) {
        m_input_conn.add(con);
    }
    
    public void addOutputConnection(Connection con) {
        m_output_conn.add(con);
    }
    
    public void setOutput(double output) {
        m_output = output;
    }
    
    public double getOutput() {
        return m_output;
    }
    
    
    public double f(double sigma)
    {
        return 1 / (1 + Math.exp(-1 * sigma));        
    }
        
    public double getBeta() {
        return m_beta;
    }
    
    public Connection getOutputConnection(int j) {
        return m_output_conn.get(j);
    }
    
    public List<Connection> getInputConnection() {
        return m_input_conn;        
    }
    
    public List<Connection> getOutConnections() {
      return m_output_conn;        
    }
    
    public int getPos() {
        return m_pos;
    }
    
    public int getLayer() {
        return m_layer;
    }
    
    public void calcOutput() {
        m_input = 0;
        m_output = 0;

        for (Connection con: m_input_conn) {
            m_input += (con.getWeight() * con.getFromNode().getOutput());
        }
        m_output = f(m_input);
    }
    
    //Calculate weight change for this outer layer node
    public void sumOuterLayer(double dz, double r){
      //Calculates the Bz by subtracting oz
      m_beta = dz - m_output; //m_beta is Bz and m_output is oz
      
      //For each connection in this node calculate the weight change
      for (Connection con: m_input_conn) {           
        
        //Get the output of the from node
        double nodeOutput = con.getFromNode().getOutput();     
        //Calculate the change in weight (changew)
        double changew = r*nodeOutput*m_output*(1-m_output)*m_beta; 
        
        //Add changew to the existing deltaw value
        con.sumChanges(changew);
      }
    }
    
    //Calculate weight change for the inner layer node
    public void sumInnerLayers(double r){
      //Calculate the weight change for the inner node using summation
      m_beta = 0;
      for (Connection con: m_output_conn) {
        //Get the weight (w) of the node connection 
        double w = con.getWeight();    
        //Get the output of the to node (ok)
        double ok = con.getToNode().getOutput();
        //Get the beta of the to node (Bk)
        double Bk = con.getToNode().getBeta(); 
        //Equation found in textbook
        m_beta += w*ok*(1-ok)*Bk;
      }
      
      //For each connection in this node calculate the weight change
      for (Connection con: m_input_conn) {           
        
        //Get the output of the from node
        double nodeOutput = con.getFromNode().getOutput();     
        //Calculate the change in weight (changew)
        double changew = r*nodeOutput*m_output*(1-m_output)*m_beta; 
        
        //Add changew to the existing deltaw value
        con.sumChanges(changew);
      }
    }
    
    private List<Connection> m_input_conn;
    private List<Connection> m_output_conn;
    
    private double m_input;
    private double m_output;
    
    private double m_beta;
    
    private int m_pos;  // starting from 0
    private int m_layer;  // starting form 0
}

