package service;

import data.Device;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Application {

    Network ntwrk;
    public Application()
    {
        ntwrk = new Network();
    }

    public boolean addDevice(char deviceType , String deviceName , String Strength)
    {
        //need to add input validations
        Device device;

        if(Strength == null || Strength.length()==0)
            device = new Device(deviceType , deviceName);
        else {
            try {
                int strength = Integer.parseInt(Strength);
                if(strength < 0)
                    return false;
                device = new Device(deviceType, deviceName, strength);


            } catch (NumberFormatException nfe) {
                return false;
            }

        }
        ntwrk.addNode(device);
        return true;
    }
    public boolean connectDevice(String deviceName , String[] deviceList)
    {
        //need to add validations
        return ntwrk.connect(deviceName,deviceList);

    }
    public void infoRoute(String source , String Dest)
    {
        ntwrk.findRoute(source,Dest);

    }
    public static void main(String args[])throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("please enter choice for network route : ");
        System.out.println("Enter 1 for Add data.Device");
        System.out.println("Enter 2 for Connect data.Device");
        System.out.println("Enter 3 for Info Route");
        Application app = new Application();
        while(true)
        {
            int choice = Integer.parseInt(br.readLine());
            String input = "";
            switch(choice) {
                case 1:
                    System.out.println("Enter device Type : 'c' for computer and 'r' for repeater");
                    input = br.readLine().trim();
                    if (input.length() > 1 || (input.charAt(0) != 'c' && input.charAt(0) != 'r'))
                        System.out.println("Wrong input, please try again!!");
                    else {
                        char deviceType = input.charAt(0);
                        input = br.readLine();
                        String deviceName = input;
                        String strength = "";
                        if(deviceType == 'c') {
                            System.out.println("Do you want to define strength of device : 'y' for yes ans 'n' for no");
                            char ch = (char) br.readLine().charAt(0);

                            if (ch == 'y')
                                strength = br.readLine();
                        }
                        app.addDevice(deviceType, deviceName, strength);
                    }
                    break;

                case 2:
                    System.out.println("Enter device Name");
                    String deviceName = br.readLine();
                    System.out.println("Enter comma separated device list");
                    input = br.readLine();
                    String deviceList[] = input.split(",");
                    app.connectDevice(deviceName, deviceList);
                    break;

                case 3:
                    System.out.println("Enter Source data.Device Name");
                    String source = br.readLine();
                    System.out.println("Enter Destination device NAme");
                    String dest = br.readLine();
                    app.infoRoute(source, dest);
                    break;
                default:
                    System.out.println("Enter a valid choice");
            }
            System.out.println("Do you wish to continue ? y or n");
            char in = (char)br.readLine().charAt(0);
            if(in != 'y')
                break;
        }
    }
}
