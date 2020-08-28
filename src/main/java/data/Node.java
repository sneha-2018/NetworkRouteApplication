package data;

import java.util.Set;

public class Node {

    private Device device;
    private Set<Node> adj;
    private int nodeId;

    public Node(Device device, Set<Node> adj,int nodeId) {
        this.device = device;
        this.adj = adj;
        this.nodeId = nodeId;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public Set<Node> getAdj() {
        return adj;
    }

    public void setAdj(Set<Node> adj) {
        this.adj = adj;
    }

    public int getNodeId() {
        return nodeId;
    }
}
