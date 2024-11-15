 
package I3.UI;

import I3.Classes.Order;
import I3.DatabaseOperation.BookingDb;
import I3.DatabaseOperation.OrderDb;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.Date;
import java.util.Vector;
import java.util.concurrent.TimeUnit;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;
import net.proteanit.sql.DbUtils;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.joda.time.Interval;

 
public class PaymentPanel extends javax.swing.JDialog {

   
    BookingDb bookingdB = new BookingDb();
    Vector<String> bookingList = new Vector();
     int bookingId = -1;
    ResultSet result;

    public PaymentPanel(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        searchHelper();
        AutoCompleteDecorator.decorate(combo_booking);
    }

     
    @SuppressWarnings("unchecked")
     private void initComponents() {

        combo_booking = new javax.swing.JComboBox();
        btn_checkOut = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_payment = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        tf_total = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        combo_booking.setEditable(true);

        btn_checkOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/checkout.png"))); 
        btn_checkOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_checkOutActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/generate.png"))); 
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        table_payment.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                table_paymentPropertyChange(evt);
            }
        });
        jScrollPane1.setViewportView(table_payment);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/meme.png"))); 
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setForeground(new java.awt.Color(64, 64, 65));
        jLabel1.setText("Total :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(138, 138, 138)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(combo_booking, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(btn_checkOut, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(373, 373, 373)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(tf_total, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE))))
                .addContainerGap(348, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_checkOut, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combo_booking, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        pack();
    }

    private void btn_checkOutActionPerformed(java.awt.event.ActionEvent evt) {

        if (bookingId != 1) {
            bookingdB.updateCheckOut(bookingId, new Date().getTime() / 1000);
            result = bookingdB.getABooking(bookingId);
            try {
                int duration = 1;
                int checkIn = result.getInt("check_in");
                int checkOut = result.getInt("check_out");
                String roomName = result.getString("booking_room");

                long diff = Math.abs((checkOut * 1000) - (checkIn * 1000));

                int durationDays = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                int roomFare = bookingdB.getRoomPrice(bookingId);

                bookingdB.flushAll();
                if (roomFare != -1) {

                    System.out.println("working as expected " + roomFare);
                    bookingdB.insertOrder(new Order(
                            bookingId,
                            roomName,
                            roomFare,
                            durationDays,
                            (roomFare * durationDays)
                    ));
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "no booking result found\n " + ex.toString());
            }

        } else {
            JOptionPane.showMessageDialog(null, "no booking selected, try to select one hitting enter from suggestion box");
        }

    } 

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        if (bookingId != -1) {
            result = bookingdB.getAllPaymentInfo(bookingId);
            populatePaymentTable(result);
        } else {
            JOptionPane.showMessageDialog(null, "no booking selected, try to select one hitting enter from suggestion box");
        }
    } 

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) { 
        try {
            MessageFormat header = new MessageFormat("I3 Technologies");
            MessageFormat footer = new MessageFormat("I3 hotel Management, Faysal Ahmed");

            table_payment.print(JTable.PrintMode.NORMAL, header, footer);
        } catch (PrinterException ex) {
            JOptionPane.showMessageDialog(null, "Error printer \n" + ex.toString());
        }
    }

    private void table_paymentPropertyChange(java.beans.PropertyChangeEvent evt) {
        if (table_payment.getModel().getRowCount() != 0) {
            System.out.println(table_payment.getModel().getRowCount() + " ,,,");
            
        }

    }

    private void updateTotalCheckBox() {
        int total = 0;
        int rows = table_payment.getRowCount();

        try {
            for (int i = 0; i < rows; i++) {
                total += (int) table_payment.getModel().getValueAt(i, 5);
            }
            tf_total.setText(total + "");
        } catch (ClassCastException ex) {
            System.err.println("waiting for a int value");
        }

    }

    private void populatePaymentTable(ResultSet result) {

        table_payment.setModel(DbUtils.resultSetToTableModel(result));
    }

    public void bookingComboFill(ResultSet result) {
        bookingList.clear();
        try {

            while (result.next()) {
                bookingList.add(result.getString("booking_room") + ", " + result.getString("name") + "," + result.getString("booking_id"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "bookingCombo fill error");
        }

    }

    public void searchHelper() {
        final DefaultComboBoxModel model = new DefaultComboBoxModel(bookingList);
        combo_booking.setModel(model);

        JTextComponent editor = (JTextComponent) combo_booking.getEditor().getEditorComponent();
        editor.addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent evt) {

                if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
                    String details = (String) combo_booking.getSelectedItem();
                    if (!details.contains(",")) {
                        JOptionPane.showMessageDialog(null, "no booking found, try adding a new booking");
                    } else {
                        bookingId = Integer.parseInt(details.substring(details.lastIndexOf(",") + 1));
                       

                    }

                }

                String value = "";
                try {
                    value = combo_booking.getEditor().getItem().toString();

                } catch (Exception ex) {
                }
                if (value.length() >= 2) {

                    bookingComboFill(bookingdB.bookingsReadyForOrder(value));
                }

            }
        });
    }

   
    public static void main(String args[]) {
       
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PaymentPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PaymentPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PaymentPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PaymentPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PaymentPanel dialog = new PaymentPanel(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

     private javax.swing.JButton btn_checkOut;
    private javax.swing.JComboBox combo_booking;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table_payment;
    private javax.swing.JTextField tf_total;
 
}
