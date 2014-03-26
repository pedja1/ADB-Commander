package com.pedja.adb_commander;

import net.lingala.zip4j.exception.ZipException;

import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;

import JDroidLib.android.controllers.ADBController;
import JDroidLib.android.pm.PMController;
import JDroidLib.android.pm.Package;
import JDroidLib.android.pm.builder.PMListPackagesBuilder;

/**
 * Created by pedja on 26.3.14..
 */
public class Main
{
    private JList list1;
    private JPanel MainPanel;
    private JButton button1;

    public static void main(String[] args)
    {
        Main main = new Main();
        JFrame frame = new JFrame("Main");
        frame.setContentPane(main.MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        try
        {
            main.setupUI();
        }
        catch (InterruptedException | ZipException | IOException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void setupUI() throws InterruptedException, ZipException, IOException
    {
        ADBController controller = new ADBController();
        //List<Device> devices = controller.getConnectedDevices();
        PMController pmController = new PMController(controller);
        PMListPackagesBuilder builder = new PMListPackagesBuilder();
        builder.thirdParty();
        List<Package> packages = pmController.listPackages(builder, null);
        list1.setListData(packages.toArray());
    }
}
