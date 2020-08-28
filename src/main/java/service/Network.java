package service;

import data.Device;
import data.Node;

import java.util.*;

public class Network {

    static int numberNodes = 0;

    private HashMap<String,Node> nodeMap;

    public Network() {
        this.nodeMap = new HashMap<String, Node>();
    }

    public boolean addNode(Device device)
    {
        if(nodeMap.containsKey(device.getDeviceName()))
            return false;
        numberNodes++;
        Node node = new Node(device , new HashSet<Node>(),numberNodes);
        nodeMap.put(device.getDeviceName(),node);
        return true;
    }

    public boolean connect(String deviceName , String[] deviceList)
    {
        if(!nodeMap.containsKey(deviceName))
            return false;
        Node node = nodeMap.get(deviceName);

        for(String d : deviceList)
        {
            if(!nodeMap.containsKey(d) || (node.getDevice().getDeviceName().equals(d)))
                return false;

        }
        for(String d : deviceList)
        {
            node.getAdj().add(nodeMap.get(d));
            nodeMap.get(d).getAdj().add(node);
        }

        return true;
    }
    public boolean findRoute(String source , String dest)
    {
        if(!nodeMap.containsKey(source) || !nodeMap.containsKey(dest))
            return false;
        Node sourceN = nodeMap.get(source);
        Node destN = nodeMap.get(dest);

        if(sourceN.getDevice().getDeviceType() == 'r' || destN.getDevice().getDeviceType() == 'r')
            return false;
        boolean isVisited[] = new boolean[numberNodes];

        List<String> route = findRouteUtil(sourceN,destN , sourceN.getDevice().getStrength(),isVisited ,new ArrayList<String>() );
        if(route == null)
            return false;
        System.out.println(route);
        return true;

    }
    private List<String> findRouteUtil(Node source ,Node Dest,int strength , boolean isVisited[],List<String> path)
    {
        if(strength == 0)
            return null;
        if(source.getNodeId() == Dest.getNodeId()) {
            path.add(Dest.getDevice().getDeviceName());
            return path;
        }
        if(isVisited[source.getNodeId() - 1])
            return null;

        isVisited[source.getNodeId()-1] = true;

        path.add(source.getDevice().getDeviceName());
        List<String> tmp;
        for(Node child : source.getAdj())
        {
            int localStrength = strength;
            if(child.getDevice().getDeviceType() == 'c')
                localStrength--;
            else
                localStrength *= 2;

            tmp = findRouteUtil(child,Dest,localStrength,isVisited,new ArrayList<String>(path));
            if(tmp != null)
                return tmp;

        }
        isVisited[source.getNodeId()-1]=false;
        return null;

    }


}
