package apkmanajemenaset;

import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

public class menu extends javax.swing.JFrame {
    
    Color defaultcolor, clickedcolor, white, black;
    //Untuk menu Transaksi
    private DefaultTableModel model1 = null;
    private DefaultTableModel model2 = null;
    private DefaultTableModel model3 = null;
    private PreparedStatement stat;
    private ResultSet rs;
    koneksi k = new koneksi();
    
    public menu() {
        initComponents();
        
        defaultcolor = new Color(255,204,51);
        clickedcolor = new Color(51,51,51);
        white = new Color(255,255,255);
        black = new Color(0,0,0);
        
        k.connect();
        refreshTabelPembeli();
        refreshTabelAset();
        refreshTableTransaksi();
        refreshCombo();
        
        
    }
    
    
    class transaksi extends menu{
        int IDTransaksi, IDAset, Harga, Jumlah_Beli, Total_Bayar;
        String NamaPembeli, Tanggal, Nama_Aset;
        
        public transaksi(){
            this.NamaPembeli = text_NamaPembeli.getText();
            String combo = combo_IDAset.getSelectedItem().toString();
            String[] arr = combo.split(":");
            this.IDAset = Integer.parseInt(arr[0]);
            try{
                Date date =  text_tanggal.getDate();
                DateFormat dateformat = new SimpleDateFormat("YYYY-MM-dd"); 
                this.Tanggal=dateformat.format(date);
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        this.Nama_Aset= arr[1];
        this.Harga = Integer.parseInt(arr[2]);
        this.Jumlah_Beli = Integer.parseInt(text_JumlahBeli.getText());
        this.Total_Bayar = this.Harga * this.Jumlah_Beli;
        }
    }
    
    public void refreshTableTransaksi(){
        model3 = new  DefaultTableModel();
        model3.addColumn("ID Transaksi");
        model3.addColumn("Nama Pembeli");
        model3.addColumn("ID Aset");
        model3.addColumn("Tanggal");
        model3.addColumn("Nama Aset");
        model3.addColumn("Harga");
        model3.addColumn("Jumlah Beli");
        model3.addColumn("Total Bayar");
        tabel_transaksi.setModel(model3);
        
        try{
            this.stat = k.getCon().prepareStatement("select * from transaksi");
            this.rs = this.stat.executeQuery();
            while(rs.next()){
                Object[] data = {
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7),
                    rs.getString(8)
                };
                model3.addRow(data);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        text_IDTransaksi.setText("");
        text_NamaPembeli.setText("");
        text_JumlahBeli.setText("");
        text_TotalBayar.setText("");
    }
    
    class pembeli extends menu{
        int IDPembeli;
        String NamaPembeli, AlamatPembeli, NoTelp;
        
        public pembeli(){
            this.NamaPembeli = txt_nama_pembeli.getText();
            this.AlamatPembeli = txt_alamat.getText();
            this.NoTelp = txt_no.getText();
        }
    }
    
    public void refreshTabelPembeli(){
        model1 = new  DefaultTableModel();
        model1.addColumn("ID Pembeli");
        model1.addColumn("Nama Pembeli");
        model1.addColumn("Alamat");
        model1.addColumn("No telp");
        tabelpembeli.setModel(model1);
        try{
            this.stat = k.getCon().prepareStatement("select * from pembeli");
            this.rs = this.stat.executeQuery();
            while(rs.next()){
                Object[] data = {
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4)
                };
                model1.addRow(data);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        txt_IDPembeli.setText("");
        txt_nama_pembeli.setText("");
        txt_alamat.setText("");
        txt_no.setText("");
    }
    
    class aset extends menu{
        int IDAset, harga, jumlah;
        String Nama_Aset;
        
        public aset(){
            this.Nama_Aset = txt_nama_aset.getText();
            this.harga = Integer.parseInt(txt_harga.getText());
            this.jumlah = Integer.parseInt(txt_jumlah.getText());
        }
    } 
    public void refreshTabelAset(){
        model2 = new  DefaultTableModel();
        model2.addColumn("ID Aset");
        model2.addColumn("Nama Aset");
        model2.addColumn("Harga");
        model2.addColumn("Jumlah");
        tabel_aset.setModel(model2);
        try{
            this.stat = k.getCon().prepareStatement("select * from aset");
            this.rs = this.stat.executeQuery();
            while(rs.next()){
                Object[] data = {
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4)
                };
                model2.addRow(data);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        txt_kode_aset.setText("");
        txt_nama_aset.setText("");
        txt_harga.setText("");
        txt_jumlah.setText("");
    }
    
    public void refreshCombo(){
        try{
            this.stat = k.getCon().prepareStatement("select * from aset");
            this.rs = this.stat.executeQuery();
            while(rs.next()){
                combo_IDAset.addItem(rs.getString("IDAset")+":"
                + rs.getString("Nama_Aset")+":"
                + rs.getString("Harga"));
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        p1 = new javax.swing.JPanel();
        l1 = new javax.swing.JLabel();
        p2 = new javax.swing.JPanel();
        l2 = new javax.swing.JLabel();
        p3 = new javax.swing.JPanel();
        l3 = new javax.swing.JLabel();
        p4 = new javax.swing.JPanel();
        l4 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        m1 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        txt_IDPembeli = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        txt_nama_pembeli = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        txt_alamat = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        txt_no = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        tabelpembeli = new javax.swing.JTable();
        btninputpembeli = new javax.swing.JButton();
        btneditpembeli = new javax.swing.JButton();
        btndeletepembeli = new javax.swing.JButton();
        m2 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        txt_kode_aset = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        txt_nama_aset = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        txt_harga = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        txt_jumlah = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        tabel_aset = new javax.swing.JTable();
        btninpaset = new javax.swing.JButton();
        btneditaset = new javax.swing.JButton();
        btndeleteaset = new javax.swing.JButton();
        m3 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        text_IDTransaksi = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        text_NamaPembeli = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        text_TotalBayar = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabel_transaksi = new javax.swing.JTable();
        inpTransaksi = new javax.swing.JButton();
        editTransaksi = new javax.swing.JButton();
        hapusTransaksi = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        combo_IDAset = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        text_JumlahBeli = new javax.swing.JTextField();
        text_tanggal = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Consolas", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/pura-ulun-danu-bratan_7126494 (1).png"))); // NOI18N
        jLabel1.setText("DEWATA COMPANY");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 850, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 850, 90));

        jPanel3.setBackground(new java.awt.Color(255, 204, 51));

        p1.setBackground(new java.awt.Color(255, 204, 51));
        p1.setPreferredSize(new java.awt.Dimension(180, 80));

        l1.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        l1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        l1.setText("Data Pembeli");
        l1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                l1MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                l1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                l1MouseReleased(evt);
            }
        });

        javax.swing.GroupLayout p1Layout = new javax.swing.GroupLayout(p1);
        p1.setLayout(p1Layout);
        p1Layout.setHorizontalGroup(
            p1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(l1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
        );
        p1Layout.setVerticalGroup(
            p1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(l1)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        p2.setBackground(new java.awt.Color(255, 204, 51));
        p2.setPreferredSize(new java.awt.Dimension(180, 80));

        l2.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        l2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        l2.setText("Data Aset");
        l2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                l2MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                l2MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                l2MouseReleased(evt);
            }
        });

        javax.swing.GroupLayout p2Layout = new javax.swing.GroupLayout(p2);
        p2.setLayout(p2Layout);
        p2Layout.setHorizontalGroup(
            p2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(l2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        p2Layout.setVerticalGroup(
            p2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(l2)
                .addGap(30, 30, 30))
        );

        p3.setBackground(new java.awt.Color(255, 204, 51));

        l3.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        l3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        l3.setText("Transaksi");
        l3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                l3MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                l3MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                l3MouseReleased(evt);
            }
        });

        javax.swing.GroupLayout p3Layout = new javax.swing.GroupLayout(p3);
        p3.setLayout(p3Layout);
        p3Layout.setHorizontalGroup(
            p3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(l3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
        );
        p3Layout.setVerticalGroup(
            p3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(l3)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        p4.setBackground(new java.awt.Color(255, 204, 51));

        l4.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        l4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        l4.setText("Logout");
        l4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                l4MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                l4MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                l4MouseReleased(evt);
            }
        });

        javax.swing.GroupLayout p4Layout = new javax.swing.GroupLayout(p4);
        p4.setLayout(p4Layout);
        p4Layout.setHorizontalGroup(
            p4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(l4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        p4Layout.setVerticalGroup(
            p4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p4Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(l4)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(p1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(p2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(p3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(p4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(p1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(p2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(p3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(p4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(169, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 180, 540));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel36.setFont(new java.awt.Font("Copperplate Gothic Bold", 1, 18)); // NOI18N
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel36.setText("PENDATAAN PEMBELI");
        jPanel4.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 670, -1));

        jLabel37.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        jLabel37.setText("ID Pembeli");
        jPanel4.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 68, 145, -1));

        txt_IDPembeli.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        txt_IDPembeli.setEnabled(false);
        jPanel4.add(txt_IDPembeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 97, 259, -1));

        jLabel38.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        jLabel38.setText("Nama Pembeli");
        jPanel4.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 138, 145, -1));

        txt_nama_pembeli.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        jPanel4.add(txt_nama_pembeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 161, 259, -1));

        jLabel39.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        jLabel39.setText("Alamat");
        jPanel4.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 202, 145, -1));

        txt_alamat.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        jPanel4.add(txt_alamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 231, 259, -1));

        jLabel40.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        jLabel40.setText("No. Telp");
        jPanel4.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 272, 259, -1));

        txt_no.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        jPanel4.add(txt_no, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 301, 259, -1));

        tabelpembeli.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabelpembeli.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelpembeliMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tabelpembeli);

        jPanel4.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 360, 610, 210));

        btninputpembeli.setBackground(new java.awt.Color(102, 102, 255));
        btninputpembeli.setFont(new java.awt.Font("Myanmar Text", 1, 18)); // NOI18N
        btninputpembeli.setForeground(new java.awt.Color(255, 255, 255));
        btninputpembeli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/diskette_489707 (1).png"))); // NOI18N
        btninputpembeli.setText("Simpan");
        btninputpembeli.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btninputpembeliMouseClicked(evt);
            }
        });
        btninputpembeli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btninputpembeliActionPerformed(evt);
            }
        });
        jPanel4.add(btninputpembeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(476, 97, 134, 51));

        btneditpembeli.setBackground(new java.awt.Color(51, 204, 0));
        btneditpembeli.setFont(new java.awt.Font("Myanmar Text", 1, 18)); // NOI18N
        btneditpembeli.setForeground(new java.awt.Color(255, 255, 255));
        btneditpembeli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/pencil_7444496.png"))); // NOI18N
        btneditpembeli.setText("Edit");
        btneditpembeli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditpembeliActionPerformed(evt);
            }
        });
        jPanel4.add(btneditpembeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(476, 154, 134, 51));

        btndeletepembeli.setBackground(new java.awt.Color(255, 51, 51));
        btndeletepembeli.setFont(new java.awt.Font("Myanmar Text", 1, 18)); // NOI18N
        btndeletepembeli.setForeground(new java.awt.Color(255, 255, 255));
        btndeletepembeli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/delete_10561835.png"))); // NOI18N
        btndeletepembeli.setText("Hapus");
        btndeletepembeli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeletepembeliActionPerformed(evt);
            }
        });
        jPanel4.add(btndeletepembeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(476, 223, 134, 51));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout m1Layout = new javax.swing.GroupLayout(m1);
        m1.setLayout(m1Layout);
        m1Layout.setHorizontalGroup(
            m1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        m1Layout.setVerticalGroup(
            m1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("tab1", m1);

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));

        jLabel31.setFont(new java.awt.Font("Copperplate Gothic Bold", 1, 18)); // NOI18N
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("PENDATAAN ASET");

        jLabel32.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        jLabel32.setText("Kode Aset");

        txt_kode_aset.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        txt_kode_aset.setEnabled(false);

        jLabel33.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        jLabel33.setText("Nama Aset");

        txt_nama_aset.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N

        jLabel34.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        jLabel34.setText("Harga");

        txt_harga.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N

        jLabel35.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        jLabel35.setText("Jumlah");

        txt_jumlah.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N

        tabel_aset.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabel_aset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_asetMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tabel_aset);

        btninpaset.setBackground(new java.awt.Color(102, 102, 255));
        btninpaset.setFont(new java.awt.Font("Myanmar Text", 1, 18)); // NOI18N
        btninpaset.setForeground(new java.awt.Color(255, 255, 255));
        btninpaset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/diskette_489707 (1).png"))); // NOI18N
        btninpaset.setText("Simpan");
        btninpaset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btninpasetActionPerformed(evt);
            }
        });

        btneditaset.setBackground(new java.awt.Color(51, 204, 0));
        btneditaset.setFont(new java.awt.Font("Myanmar Text", 1, 18)); // NOI18N
        btneditaset.setForeground(new java.awt.Color(255, 255, 255));
        btneditaset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/pencil_7444496.png"))); // NOI18N
        btneditaset.setText("Edit");
        btneditaset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btneditasetMouseClicked(evt);
            }
        });
        btneditaset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditasetActionPerformed(evt);
            }
        });

        btndeleteaset.setBackground(new java.awt.Color(255, 51, 51));
        btndeleteaset.setFont(new java.awt.Font("Myanmar Text", 1, 18)); // NOI18N
        btndeleteaset.setForeground(new java.awt.Color(255, 255, 255));
        btndeleteaset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/delete_10561835.png"))); // NOI18N
        btndeleteaset.setText("Hapus");
        btndeleteaset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeleteasetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txt_harga, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txt_nama_aset, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                                .addComponent(txt_kode_aset)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btneditaset, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btndeleteaset, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btninpaset, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(60, 60, 60))))
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 589, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel31)
                .addGap(27, 27, 27)
                .addComponent(jLabel32)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(txt_kode_aset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel33)
                        .addGap(6, 6, 6)
                        .addComponent(txt_nama_aset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel34)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_harga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel35))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(btninpaset, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(btneditaset, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btndeleteaset, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout m2Layout = new javax.swing.GroupLayout(m2);
        m2.setLayout(m2Layout);
        m2Layout.setHorizontalGroup(
            m2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        m2Layout.setVerticalGroup(
            m2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("tab2", m2);

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));

        jLabel16.setFont(new java.awt.Font("Copperplate Gothic Bold", 1, 18)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("TRANSAKSI");

        jLabel17.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        jLabel17.setText("Kode Penjualan");

        text_IDTransaksi.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        text_IDTransaksi.setEnabled(false);

        jLabel18.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        jLabel18.setText("Nama Pembeli");

        text_NamaPembeli.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N

        jLabel19.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        jLabel19.setText("Tanggal");

        jLabel20.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        jLabel20.setText("Total Bayar");

        text_TotalBayar.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        text_TotalBayar.setEnabled(false);

        tabel_transaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabel_transaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_transaksiMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tabel_transaksi);

        inpTransaksi.setBackground(new java.awt.Color(102, 102, 255));
        inpTransaksi.setFont(new java.awt.Font("Myanmar Text", 1, 18)); // NOI18N
        inpTransaksi.setForeground(new java.awt.Color(255, 255, 255));
        inpTransaksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/diskette_489707 (1).png"))); // NOI18N
        inpTransaksi.setText("Simpan");
        inpTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inpTransaksiActionPerformed(evt);
            }
        });

        editTransaksi.setBackground(new java.awt.Color(51, 204, 0));
        editTransaksi.setFont(new java.awt.Font("Myanmar Text", 1, 18)); // NOI18N
        editTransaksi.setForeground(new java.awt.Color(255, 255, 255));
        editTransaksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/pencil_7444496.png"))); // NOI18N
        editTransaksi.setText("Edit");
        editTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editTransaksiActionPerformed(evt);
            }
        });

        hapusTransaksi.setBackground(new java.awt.Color(255, 51, 51));
        hapusTransaksi.setFont(new java.awt.Font("Myanmar Text", 1, 18)); // NOI18N
        hapusTransaksi.setForeground(new java.awt.Color(255, 255, 255));
        hapusTransaksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/delete_10561835.png"))); // NOI18N
        hapusTransaksi.setText("Hapus");
        hapusTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusTransaksiActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        jLabel21.setText("Jenis Aset");

        jLabel22.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        jLabel22.setText("Jumlah beli");

        text_JumlahBeli.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 591, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 79, Short.MAX_VALUE))
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jScrollPane3)
                        .addContainerGap())
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(text_NamaPembeli, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                            .addComponent(text_IDTransaksi)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(text_TotalBayar, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                            .addComponent(combo_IDAset, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(text_JumlahBeli, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                            .addComponent(text_tanggal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(editTransaksi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(hapusTransaksi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(inpTransaksi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(60, 60, 60))))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel16)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(inpTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(editTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(hapusTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(text_IDTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(text_NamaPembeli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(combo_IDAset, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(text_tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(text_JumlahBeli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(text_TotalBayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout m3Layout = new javax.swing.GroupLayout(m3);
        m3.setLayout(m3Layout);
        m3Layout.setHorizontalGroup(
            m3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        m3Layout.setVerticalGroup(
            m3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("tab3", m3);

        jPanel1.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, 670, 580));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void l1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_l1MouseClicked
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(0); 
    }//GEN-LAST:event_l1MouseClicked

    private void l2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_l2MouseClicked
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_l2MouseClicked

    private void l3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_l3MouseClicked
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(2);
    }//GEN-LAST:event_l3MouseClicked

    private void l1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_l1MousePressed
        // TODO add your handling code here:
        p1.setBackground(clickedcolor);
        p2.setBackground(defaultcolor);
        p3.setBackground(defaultcolor);
        p4.setBackground(defaultcolor);
        l1.setForeground(white);
    }//GEN-LAST:event_l1MousePressed

    private void l1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_l1MouseReleased
        // TODO add your handling code here:
        p1.setBackground(defaultcolor);
        l1.setForeground(black);
    }//GEN-LAST:event_l1MouseReleased

    private void l2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_l2MousePressed
        // TODO add your handling code here:
        p1.setBackground(defaultcolor);
        p2.setBackground(clickedcolor);
        p3.setBackground(defaultcolor);
        p4.setBackground(defaultcolor);
        l2.setForeground(white);
    }//GEN-LAST:event_l2MousePressed

    private void l2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_l2MouseReleased
        // TODO add your handling code here:
        p2.setBackground(defaultcolor);
        l2.setForeground(black);
    }//GEN-LAST:event_l2MouseReleased

    private void l3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_l3MousePressed
        // TODO add your handling code here:
        p1.setBackground(defaultcolor);
        p2.setBackground(defaultcolor);
        p3.setBackground(clickedcolor);
        p4.setBackground(defaultcolor);
        l3.setForeground(white);
    }//GEN-LAST:event_l3MousePressed

    private void l3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_l3MouseReleased
        // TODO add your handling code here
        p3.setBackground(defaultcolor);
        l3.setForeground(black);
    }//GEN-LAST:event_l3MouseReleased

    private void inpTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inpTransaksiActionPerformed
        try{
            transaksi tran=new transaksi();
            text_TotalBayar.setText(""+tran.Total_Bayar);
            this.stat = k.getCon().prepareStatement("insert into transaksi values(?,?,?,?,?,?,?,?)");
            this.stat.setInt(1, 0);
            this.stat.setString(2, tran.NamaPembeli);
            this.stat.setInt(3, tran.IDAset);
            this.stat.setString(4, tran.Tanggal);
            this.stat.setString(5, tran.Nama_Aset);
            this.stat.setInt(6, tran.Harga);
            this.stat.setInt(7, tran.Jumlah_Beli);
            this.stat.setInt(8, tran.Total_Bayar);
            
            int pilihan = JOptionPane.showConfirmDialog(null, 
                    "Tanggal: "+tran.Tanggal+
                    "\nNama Pelanggan: "+ tran.NamaPembeli+
                    "\nPembelian: "+ tran.Jumlah_Beli+" "+tran.Nama_Aset+
                    "\nTotal Bayar: "+ tran.Total_Bayar +"\n",
                    "Tambahkan Transaksi?",
                    JOptionPane.YES_NO_OPTION);
            if(pilihan == JOptionPane.YES_OPTION){
                this.stat.executeUpdate();
                refreshTableTransaksi();
            }else if(pilihan == JOptionPane.NO_OPTION){
                refreshTableTransaksi();
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_inpTransaksiActionPerformed

    private void editTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editTransaksiActionPerformed
        try{
            transaksi tran = new transaksi();
            tran.IDTransaksi= Integer.parseInt(text_IDTransaksi.getText());
            this.stat = k.getCon().prepareStatement("update transaksi set NamaPembeli=?,"
                    + "IDAset=?, Tanggal=?, Nama_Aset=?, Harga=?,Jumlah_Beli=?,Total_Bayar=? "
                    + "where IDTransaksi=?");
            this.stat.setString(1, tran.NamaPembeli);
            this.stat.setInt(2, tran.IDAset);
            this.stat.setString(3, tran.Tanggal);
            this.stat.setString(4, tran.Nama_Aset);
            this.stat.setInt(5, tran.Harga);
            this.stat.setInt(6, tran.Jumlah_Beli);
            this.stat.setInt(7, tran.Total_Bayar);
            this.stat.setInt(8, tran.IDTransaksi);
            this.stat.executeUpdate();
            refreshTableTransaksi();
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_editTransaksiActionPerformed

    private void btninpasetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btninpasetActionPerformed
        try{
            aset as=new aset();
            this.stat = k.getCon().prepareStatement("insert into aset values(?,?,?,?)");
            this.stat.setInt(1, 0);
            this.stat.setString(2, as.Nama_Aset);
            this.stat.setInt(3, as.harga);
            this.stat.setInt(4, as.jumlah);
            
            this.stat.executeUpdate();
            refreshTabelAset();
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_btninpasetActionPerformed

    private void btneditasetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditasetActionPerformed
       try{
            aset as=new aset();
            as.IDAset= Integer.parseInt(txt_kode_aset.getText());
            this.stat = k.getCon().prepareStatement("update aset set Nama_Aset=?,"
                    + "harga=?, jumlah=? "
                    + "where IDAset=?");
            this.stat.setString(1, as.Nama_Aset);
            this.stat.setInt(2, as.harga);
            this.stat.setInt(3, as.jumlah);
            this.stat.setInt(4, as.IDAset);
            this.stat.executeUpdate();
            refreshTabelAset();
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_btneditasetActionPerformed

    private void btninputpembeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btninputpembeliActionPerformed
        try{
            pembeli cust=new pembeli();
            this.stat = k.getCon().prepareStatement("insert into pembeli values(?,?,?,?)");
            this.stat.setInt(1, 0);
            this.stat.setString(2, cust.NamaPembeli);
            this.stat.setString(3, cust.AlamatPembeli);
            this.stat.setString(4, cust.NoTelp);
            
            this.stat.executeUpdate();
            refreshTabelPembeli();
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_btninputpembeliActionPerformed

    private void btneditpembeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditpembeliActionPerformed
        try{
            pembeli cust=new pembeli();
            cust.IDPembeli= Integer.parseInt(txt_IDPembeli.getText());
            this.stat = k.getCon().prepareStatement("update pembeli set NamaPembeli=?,"
                    + "AlamatPembeli=?, NoTelp=? "
                    + "where IDPembeli=?");
            this.stat.setString(1, cust.NamaPembeli);
            this.stat.setString(2, cust.AlamatPembeli);
            this.stat.setString(3, cust.NoTelp);
            this.stat.setInt(4, cust.IDPembeli);
            this.stat.executeUpdate();
            refreshTabelPembeli();
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_btneditpembeliActionPerformed

    private void tabel_transaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_transaksiMouseClicked
        text_IDTransaksi.setText(model3.getValueAt(tabel_transaksi.getSelectedRow(),0).toString());
        text_NamaPembeli.setText(model3.getValueAt(tabel_transaksi.getSelectedRow(),1).toString());
        text_JumlahBeli.setText(model3.getValueAt(tabel_transaksi.getSelectedRow(),6).toString());
        text_TotalBayar.setText(model3.getValueAt(tabel_transaksi.getSelectedRow(),7).toString());
    }//GEN-LAST:event_tabel_transaksiMouseClicked

    private void hapusTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusTransaksiActionPerformed
        try{
            transaksi tran = new transaksi();
            tran.IDTransaksi= Integer.parseInt(text_IDTransaksi.getText());
            this.stat = k.getCon().prepareStatement("delete from transaksi "
                    + "where IDTransaksi=?");
            this.stat.setInt(1, tran.IDTransaksi);
            this.stat.executeUpdate();
            refreshTableTransaksi();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_hapusTransaksiActionPerformed

    private void btninputpembeliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btninputpembeliMouseClicked
        //j
    }//GEN-LAST:event_btninputpembeliMouseClicked

    private void tabelpembeliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelpembeliMouseClicked
        txt_IDPembeli.setText(model1.getValueAt(tabelpembeli.getSelectedRow(),0).toString());
        txt_nama_pembeli.setText(model1.getValueAt(tabelpembeli.getSelectedRow(),1).toString());
        txt_alamat.setText(model1.getValueAt(tabelpembeli.getSelectedRow(),2).toString());
        txt_no.setText(model1.getValueAt(tabelpembeli.getSelectedRow(),3).toString());
    }//GEN-LAST:event_tabelpembeliMouseClicked

    private void btndeletepembeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeletepembeliActionPerformed
        try{
            pembeli cust=new pembeli();
            cust.IDPembeli= Integer.parseInt(txt_IDPembeli.getText());
            this.stat = k.getCon().prepareStatement("delete from pembeli "
                    + "where IDPembeli=?");
            this.stat.setInt(1, cust.IDPembeli);
            this.stat.executeUpdate();
            refreshTabelPembeli();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_btndeletepembeliActionPerformed

    private void tabel_asetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_asetMouseClicked
        txt_kode_aset.setText(model2.getValueAt(tabel_aset.getSelectedRow(),0).toString());
        txt_nama_aset.setText(model2.getValueAt(tabel_aset.getSelectedRow(),1).toString());
        txt_harga.setText(model2.getValueAt(tabel_aset.getSelectedRow(),2).toString());
        txt_jumlah.setText(model2.getValueAt(tabel_aset.getSelectedRow(),3).toString());
    }//GEN-LAST:event_tabel_asetMouseClicked

    private void btneditasetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btneditasetMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btneditasetMouseClicked

    private void btndeleteasetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteasetActionPerformed
        try{
            aset as=new aset();
            as.IDAset= Integer.parseInt(txt_kode_aset.getText());
            this.stat = k.getCon().prepareStatement("delete from aset "
                    + "where IDAset=?");
            this.stat.setInt(1, as.IDAset);
            this.stat.executeUpdate();
            refreshTabelAset();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_btndeleteasetActionPerformed

    private void l4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_l4MouseClicked
        new login().show();
        dispose();
    }//GEN-LAST:event_l4MouseClicked

    private void l4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_l4MousePressed
        p1.setBackground(defaultcolor);
        p2.setBackground(defaultcolor);
        p3.setBackground(defaultcolor);
        p4.setBackground(clickedcolor);
        l4.setForeground(white);
    }//GEN-LAST:event_l4MousePressed

    private void l4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_l4MouseReleased
        p4.setBackground(defaultcolor);
        l4.setForeground(black);
    }//GEN-LAST:event_l4MouseReleased
    
    
    
    
    public static void main(String args[]) {
        
        /* Set the Nimbus look and feel */
         //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btndeleteaset;
    private javax.swing.JButton btndeletepembeli;
    private javax.swing.JButton btneditaset;
    private javax.swing.JButton btneditpembeli;
    private javax.swing.JButton btninpaset;
    private javax.swing.JButton btninputpembeli;
    private javax.swing.JComboBox<String> combo_IDAset;
    private javax.swing.JButton editTransaksi;
    private javax.swing.JButton hapusTransaksi;
    private javax.swing.JButton inpTransaksi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel l1;
    private javax.swing.JLabel l2;
    private javax.swing.JLabel l3;
    private javax.swing.JLabel l4;
    private javax.swing.JPanel m1;
    private javax.swing.JPanel m2;
    private javax.swing.JPanel m3;
    private javax.swing.JPanel p1;
    private javax.swing.JPanel p2;
    private javax.swing.JPanel p3;
    private javax.swing.JPanel p4;
    private javax.swing.JTable tabel_aset;
    private javax.swing.JTable tabel_transaksi;
    private javax.swing.JTable tabelpembeli;
    private javax.swing.JTextField text_IDTransaksi;
    private javax.swing.JTextField text_JumlahBeli;
    private javax.swing.JTextField text_NamaPembeli;
    private javax.swing.JTextField text_TotalBayar;
    private com.toedter.calendar.JDateChooser text_tanggal;
    private javax.swing.JTextField txt_IDPembeli;
    private javax.swing.JTextField txt_alamat;
    private javax.swing.JTextField txt_harga;
    private javax.swing.JTextField txt_jumlah;
    private javax.swing.JTextField txt_kode_aset;
    private javax.swing.JTextField txt_nama_aset;
    private javax.swing.JTextField txt_nama_pembeli;
    private javax.swing.JTextField txt_no;
    // End of variables declaration//GEN-END:variables
}
