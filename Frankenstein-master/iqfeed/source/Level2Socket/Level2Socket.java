//-----------------------------------------------------------
//-----------------------------------------------------------
//
//             System: IQFeed
//       Filename: Level2Socket.java
//
//-----------------------------------------------------------
//
//            Proprietary Software Product
//
//           Data Transmission Network Inc.
//           9110 West Dodge Road Suite 200
//               Omaha, Nebraska  68114
//
//    Copyright (c) by Data Transmission Network 2008
//                 All Rights Reserved
//
//
//-----------------------------------------------------------
// Module Description: This file illustrates how to send and receive Level 2 commands
//         References: None
//           IDE: Netbeans 7.4
//             Author: Tim Walter
//        Modified By: 
//
//-----------------------------------------------------------
// Website for API information: http://www.iqfeed.net/dev/
//-----------------------------------------------------------
/******************************************************************************
 * DISCLAIMER:  These apps are designed with simplicity in mind and are not 
 * designed for copy and paste development.  You will need to consider 
 * performance enhancements based upon your own needs and implement your
 * solution accordingly. They are a guide to get people started, nothing more.
 ******************************************************************************/
import java.io.*;
import javax.swing.*;
import java.awt.event.*;


public class Level2Socket extends javax.swing.JFrame implements ActionListener, WindowListener
{
    //Define variables for use throughout the code
    private int IQFEED_LEVEL2_PORT_DEFAULT = 9200;      //Defaults to 9200, adjustable in the registy.
	//IQFeed_Socket is defined in the common folder, effectively it is a socket with a buffered reader and writer added.
    IQFeed_Socket C_Level2IQFeed_Socket;
	/**
	 * Creates new form Level2Socket
	 */
	public Level2Socket()
	{
		super("Level 2 Socket");
		initComponents();
		C_Level2IQFeed_Socket = new IQFeed_Socket();

		//Attempt to connect our socket
		
		System.out.println("Connecting to Level2 port.");
		// requests a socket connection to localhost on port IQFEED_LEVEL2_PORT_DEFAULT, default = localhost and port 9200
		// Port 9200 is configurable in the registry.  See registry settings in the documentation.
		// If False is returned we are not able to connect display an error and exit. 
		if (!C_Level2IQFeed_Socket.ConnectSocket(IQFEED_LEVEL2_PORT_DEFAULT))
		{
			JOptionPane.showMessageDialog(null, "Did you forget to login first?\nTake a look at the LaunchingTheFeed example app.");
			System.exit(1);
		}

		System.out.println("Connected to Level 2 port.");
		C_Level2IQFeed_Socket.CreateBuffers();

		//Initialize the protocol, this prepares us for commands to come and verifies that our socket is working as intended.
		try
		{
                        Java_Config config = new Java_Config();
			C_Level2IQFeed_Socket.brBufferedWriter.write(String.format("S,SET PROTOCOL,%s\r\n",config.most_recent_protocol));
			C_Level2IQFeed_Socket.brBufferedWriter.flush();
			System.out.println("Message Posted, Protocol set.");
		} catch (Exception eError)
		{
			updateCommandLabelError("Error writing to socket.\n%s", eError.toString());
		}

		Level2_Listener thread = new Level2_Listener();
		thread.start();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtDisplay = new javax.swing.JTextArea();
        btnRequestMMID = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();
        btnWatch = new javax.swing.JButton();
        lblDataSentToServer = new javax.swing.JLabel();
        btnConnect = new javax.swing.JButton();
        btnDisconnect = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtSymbol = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtDisplay.setColumns(20);
        txtDisplay.setRows(5);
        jScrollPane1.setViewportView(txtDisplay);

        btnRequestMMID.setText("Request MMID");
        btnRequestMMID.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnRequestMMIDActionPerformed(evt);
            }
        });

        btnRemove.setText("Remove");
        btnRemove.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnRemoveActionPerformed(evt);
            }
        });

        btnWatch.setText("Watch");
        btnWatch.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnWatchActionPerformed(evt);
            }
        });

        lblDataSentToServer.setText("Message Sent : ");

        btnConnect.setText("Connect");
        btnConnect.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnConnectActionPerformed(evt);
            }
        });

        btnDisconnect.setText("Disconnect");
        btnDisconnect.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnDisconnectActionPerformed(evt);
            }
        });

        jLabel1.setText("Symbol / Request Data");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDataSentToServer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSymbol))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnWatch, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnRequestMMID, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnConnect, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnDisconnect, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtSymbol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnWatch)
                    .addComponent(btnRemove)
                    .addComponent(btnRequestMMID)
                    .addComponent(btnConnect)
                    .addComponent(btnDisconnect))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblDataSentToServer, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
	//-----------------------------------------------------------
	// void btnRequestMMIDActionPerformed(java.awt.event.ActionEvent evt) 
	/**
	 * @params Windows click event 
	 * Note: Sends the request Market Maker ID command
	*/
	//-----------------------------------------------------------
    private void btnRequestMMIDActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnRequestMMIDActionPerformed
    {//GEN-HEADEREND:event_btnRequestMMIDActionPerformed
        //Message format: SMC<CR><LF>
        String sCommand = "m" + txtSymbol.getText() + "\r\n";
        sendMessage(sCommand);
        //Clear txtDisplay and display aby messages
        txtDisplay.setText("");
        updateCommandLabelResult(sCommand);
    }//GEN-LAST:event_btnRequestMMIDActionPerformed
	//-----------------------------------------------------------
	// void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) 
	/**
	 * @params Windows click event 
	 * Note: Sends the remove watch for the symbol entered.
	*/
	//-----------------------------------------------------------
    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnRemoveActionPerformed
    {//GEN-HEADEREND:event_btnRemoveActionPerformed
        //Message format: SST<CR><LF>
        String sCommand = "r" + txtSymbol.getText() + "\r\n";
        sendMessage(sCommand);
        //Clear txtDisplay and display aby messages
        txtDisplay.setText("");
        updateCommandLabelResult(sCommand);
    }//GEN-LAST:event_btnRemoveActionPerformed
	//-----------------------------------------------------------
	// void btnWatchActionPerformed(java.awt.event.ActionEvent evt)    
	/**
	 * @params Windows click event 
	 * Note: Sends the watch command for the symbol entered.
	*/
	//-----------------------------------------------------------
    private void btnWatchActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnWatchActionPerformed
    {//GEN-HEADEREND:event_btnWatchActionPerformed
        //Message format: SLM<CR><LF>
        String sCommand = "w" + txtSymbol.getText() + "\r\n";
        sendMessage(sCommand);
        //Clear txtDisplay and display aby messages
        txtDisplay.setText("");
        updateCommandLabelResult(sCommand);
    }//GEN-LAST:event_btnWatchActionPerformed
	//-----------------------------------------------------------
	// void btnConnectActionPerformed(java.awt.event.ActionEvent evt)  
	/**
	 * @params Windows click event 
	 * Note: Sends the Connect command to the server to enable the level 2 connection.
	*/
	//-----------------------------------------------------------
    private void btnConnectActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnConnectActionPerformed
    {//GEN-HEADEREND:event_btnConnectActionPerformed
        //Message format: SSC<CR><LF>
        String sCommand = "c\r\n";
        sendMessage(sCommand);
        //Clear txtDisplay and display aby messages
        txtDisplay.setText("");
        updateCommandLabelResult(sCommand);
    }//GEN-LAST:event_btnConnectActionPerformed
	//-----------------------------------------------------------
	// void btnDisconnectActionPerformed(java.awt.event.ActionEvent evt) 
	/**
	 * @params Windows click event 
	 * Note: Sends the disconnect command to the server to disable the level 2 connection.
	*/
	//-----------------------------------------------------------
    private void btnDisconnectActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnDisconnectActionPerformed
    {//GEN-HEADEREND:event_btnDisconnectActionPerformed
        //Message format: SNC<CR><LF>
        String sCommand = "x\r\n";
        sendMessage(sCommand);
        //Clear txtDisplay and display aby messages
        txtDisplay.setText("");
        updateCommandLabelResult(sCommand);
    }//GEN-LAST:event_btnDisconnectActionPerformed

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[])
	{
		/* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
		 * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
		 */
		try
		{
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
			{
				if ("Nimbus".equals(info.getName()))
				{
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex)
		{
			java.util.logging.Logger.getLogger(Level2Socket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex)
		{
			java.util.logging.Logger.getLogger(Level2Socket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex)
		{
			java.util.logging.Logger.getLogger(Level2Socket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex)
		{
			java.util.logging.Logger.getLogger(Level2Socket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
        //</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				new Level2Socket().setVisible(true);
			}
		});
	}
	//-----------------------------------------------------------
	// void sendMessage(String sCommand) 

		/**
		 * Sends a command to the output stream for processing by the
 Level2_Listener class\thread.
		 *
		 * @param sCommand, a String with a full message ready to be sent to the
		 * server including carriage return and line feed. Example: "S,UNWATCH
		 * ALL\r\n"
		 */
	//-----------------------------------------------------------     
	void sendMessage(String sCommand)
	{
		try
		{
			//Send the message and flush to be sure it is handled right away.
			C_Level2IQFeed_Socket.brBufferedWriter.write(sCommand);
			C_Level2IQFeed_Socket.brBufferedWriter.flush();
			//Label will show the actual string sent to the server.
			updateCommandLabelResult(sCommand);
		} catch (IOException eError)
		{
			updateCommandLabelError("Error:" + sCommand, eError.toString());
		}
	}
//-----------------------------------------------------------
//-----------------------------------------------------------
// void updateCommandLabelResult(String sCommand) 

	/**
	 * Displays a message to a user that identifies the full message being sent.
	 *
	 * @param sCommand, a String with a full message ready to be sent to the
	 * server including carriage return and line feed. Example: "S,UNWATCH
	 * ALL\r\n"
	 */
//-----------------------------------------------------------  
	void updateCommandLabelResult(String sCommand)
	{
		lblDataSentToServer.setText("Message Sent: " + sCommand);
	}
//-----------------------------------------------------------
// void updateCommandLabelError(String sCommand, String sError) 

	/**
	 * Displays a message to a user that identifies the error that may have
	 * occurred.
	 *
	 * @param sCommand, a String with a full message ready to be sent to the
	 * server including carriage return and line feed. Example: "S,UNWATCH
	 * ALL\r\n"
	 * @param sError, a String passed from the exception object.
	 */
//-----------------------------------------------------------   
	void updateCommandLabelError(String sCommand, String sError)
    {
        String sErrorMessage = String.format("%s \n Error Message: %s",sCommand, sError);
        lblDataSentToServer.setText(sErrorMessage); 
    }    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConnect;
    private javax.swing.JButton btnDisconnect;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnRequestMMID;
    private javax.swing.JButton btnWatch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDataSentToServer;
    private javax.swing.JTextArea txtDisplay;
    private javax.swing.JTextField txtSymbol;
    // End of variables declaration//GEN-END:variables
 /******************************************************************
 ******************************************************************
 ******************************************************************/
//-----------------------------------------------------------
// void actionPerformed(ActionEvent e)
// void windowOpened(WindowEvent e)
// void windowClosing(WindowEvent e)
// void windowIconified(WindowEvent e)
// void windowDeiconified(WindowEvent e)
// void windowClosed(WindowEvent e)
// void windowActivated(WindowEvent e)
// void windowActivated(WindowEvent e)        
/**
 * These 8 Window's event functions are left effectively blank due to simplicity, but are required for compilation.
*/
//-----------------------------------------------------------
// Start of Window's events.
    @Override
    public void actionPerformed(ActionEvent e)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowOpened(WindowEvent e)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosing(WindowEvent e)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosed(WindowEvent e)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowIconified(WindowEvent e)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeiconified(WindowEvent e)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public void windowActivated(WindowEvent e)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public void windowDeactivated(WindowEvent e)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    //End of window's events
    /******************************************************************
    ******************************************************************
    ******************************************************************/
	
	    
    
    //Class to execute and listen to replies from the server.
    //I am displaying the data returned in this function for simplicity to the text Window.
    //Considerations for performance will be needed as this will not work for most normal usage.
    class Level2_Listener extends Thread
    {
    //-----------------------------------------------------------
    // void run()
    /**
    */
    //  Notes:  Check for data on the socket, process it if it exists by displaying
    //          to the text window.
    //
    //-----------------------------------------------------------
            public void run()
            {
                    String line;
                    try
                    {
                            while ((line = C_Level2IQFeed_Socket.brBufferedReader.readLine()) != null)
                            {
                                    System.out.println(line);
                                    txtDisplay.append(line + "\n");
                            }
                    }
                    catch (Exception eError) 
                    { 
                        updateCommandLabelError("Unable to read from socket.\n" , eError.toString());
                    }
            }
    }
}