/**
 * @author Zhiqiang Ren 
 * date: Feb. 4th. 2012
 * 
 */

package aipackage;


public class Connection {
    
    public Connection(Node from, Node to, double weight) {
        m_from = from;
        m_to = to;
        m_weight = weight;
    }
    
    public Node getFromNode() {
        return m_from;
    }
    
    public Node getToNode() {
        return m_to;
    }
    
    public double getWeight() {
        return m_weight;
    }
    //Sums up the deltas
    public void sumChanges(double changew){
     m_deltaw = m_deltaw + changew;
    }
    //Updates the weight and clean up the delta for the next training
    public void updateWeight(){     
     m_weight += m_deltaw;   
     m_deltaw = 0;     
    }
    private double m_weight;
    private double m_deltaw;

    private Node m_from;
    private Node m_to;

}