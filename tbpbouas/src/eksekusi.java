import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


//constructor
//inherit dari kelas crud
public class eksekusi extends crud {

    Scanner input = new Scanner(System.in);
    //variable koneksi
    static Connection conn;


    //java collection
    public void array(){
        List <String> lis = new ArrayList <String>();
        lis.add("1. Lihat Data Transaksi");
        lis.add("2. Tambah Data Transaksi");
        lis.add("3. Ubah Data Transaksi");
        lis.add("4. Hapus Data Transaksi");
        lis.add("5. Cari Data Transaksi");
        //perulangan
        for (Iterator <String> iterator = lis.iterator(); iterator.hasNext();) {
            String string = (String) iterator.next();
            System.out.println(string);
        }
    }


    //method waktu
    public void waktu(){
        Date waktu = new Date();
        SimpleDateFormat tf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.print(tf.format(waktu));
    }

    @Override
    public void insert () throws SQLException {
    System.out.println("Masukkan Data : ");
    //link database
    String url = "jdbc:mysql://localhost:3306/tbpbo";
    //exception
    try{
        ListToken();
        // Input Kode Token
        System.out.println("Inputkan Kode Token : ");
        KodeToken = input.nextLine();

        // Input NamaPembeli
        System.out.println("Inputkan Nama Pembeli : ");
        NamaPembeli = input.nextLine();

        // Input JenisToken
        System.out.print("Inputkan Jenis Token(1/2/3/4/5/6) : ");
        JenisToken = input.nextInt();

        //NamaToken
        if(JenisToken==1){
            NamaToken = "Token PLN 20";
            System.out.println("Token PLN 20");
        }
        else if(JenisToken==2){
            NamaToken = "Token PLN 50";
            System.out.println("Token PLN 50");
        }
        else if(JenisToken==3){
            NamaToken = "Token PLN 100";
            System.out.println("Token PLN 100");
        }
        else if(JenisToken==4){
            NamaToken = "Token PLN 200";
            System.out.println("Token PLN 200");
        }
        else if(JenisToken==5){
            NamaToken = "Token PLN 500";
            System.out.println("Token PLN 500");
        }
        else if(JenisToken==6){
            NamaToken = "Token PLN 1jt";
            System.out.println("Token PLN 1jt");
        }
        else{
            System.out.println("EROR");
        }
        
        // Inputan ID Transaksi
        System.out.println("Inputkan ID Transaksi : ");
        IDTransaksi = input.nextInt();

        //Tagihan
        if(JenisToken==1){
            Tagihan = 21000;
        }
        else if(JenisToken==2){
            Tagihan = 51000;
        }
        else if(JenisToken==3){
            Tagihan = 101000;
        }
        else if(JenisToken==4){
            Tagihan = 200000;
        }
        else if(JenisToken==5){
            Tagihan = 500000;
        }
        else if(JenisToken==6){
            Tagihan = 1000000;
        }
        else{
            System.out.println("EROR");
        }
   
        // Inputan Bayar  
        System.out.print("Inputkan Nominal Bayar yang diterima : ");
        Bayar = input.nextInt();

        //Kembali
        Kembali = Bayar - Tagihan; //Proses Matematika
        System.out.println("\n");


        //SQL Query masukkan data ke database
        String sql = "INSERT INTO transaksi (IDTransaksi, NamaPembeli, KodeToken, NamaToken, JenisToken, Tagihan, Bayar, Kembali) VALUES ('"+IDTransaksi+"','"+NamaPembeli+"','"+KodeToken+"','"+NamaToken+"','"+JenisToken+"','"+Tagihan+"','"+Bayar+"','"+Kembali+"')";
        //connect ke database
        conn = DriverManager.getConnection(url,"root","");
        Statement statement = conn.createStatement();
        statement.execute(sql);
        System.out.println("Berhasil input data!!");
    }
    //exception database
    catch (SQLException e) {
        System.err.println("Terjadi kesalahan input data");
    } 
    //exception tipe data
    catch (InputMismatchException e) {
        System.err.println("Inputan harus berupa angka");
       }
    }

    @Override
    public void display()throws SQLException{
        //link database
        String url = "jdbc:mysql://localhost:3306/tbpbo";
        
        try{
        //SQL Query    
        String sql ="SELECT * FROM transaksi";
        //connect ke database
        conn = DriverManager.getConnection(url,"root","");
		Statement statement = conn.createStatement();
		ResultSet result = statement.executeQuery(sql);

        while(result.next()){
            //Menampilkan data di database
            System.out.print("\nIDTransaksi\t: ");
            System.out.print(result.getInt("IDTransaksi"));
            System.out.print("\nNama Pembeli\t: ");
            System.out.print(result.getString("NamaPembeli"));
            System.out.print("\nKode Token\t: ");
            System.out.print(result.getString("KodeToken"));
            System.out.print("\nNama Token\t: ");
            System.out.print(result.getString("NamaToken"));
            System.out.print("\nJenis Token\t: ");
            System.out.print(result.getInt("JenisToken"));
            System.out.print("\nTagihant\t: ");
            System.out.print(result.getInt("Tagihan"));
			System.out.print("\nBayar\t: ");
            System.out.print(result.getInt("Bayar"));
            System.out.print("\nKembali\t: ");
            System.out.print(result.getInt("Kembali"));
            System.out.print("\n");
        }
      }
      //Exception eror Database
      catch (SQLException e) {
        System.err.println("Terjadi kesalahan");
    } 
    //Exception Program
    catch (InputMismatchException e) {
        System.err.println("Program Eror");
       }
    }

    @Override  
    public void update(){
        System.out.print("Ubah Data Transaksi");

        try {
            //memanggil method display
            display();
            //link database
            String url = "jdbc:mysql://localhost:3306/tbpbo";
            System.out.print("\nMasukkan IDTransaksi yang akan di ubah : ");
            //Menerima inputan IDTransaksi Baru
            Integer IDTransaksi = Integer.parseInt(input.nextLine());
            //SQL Query
            String sql = "SELECT * FROM transaksi WHERE IDTransaksi = " +IDTransaksi;
            //Connect ke Database
            conn = DriverManager.getConnection(url,"root","");
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            
            if(result.next()){
                
                System.out.print("Nama Pembeli setelah diganti ["+result.getString("NamaPembeli")+"]\t: ");
                String NamaPembeli = input.nextLine();
                //SQL Query
                sql = "UPDATE transaksi SET NamaPembeli='"+NamaPembeli+"' WHERE IDTransaksi='"+IDTransaksi+"'";

                //System.out.println(sql);
    
                if(statement.executeUpdate(sql) > 0){
                    System.out.println("Berhasil memperbaharui data  (IDTransaksi "+IDTransaksi+")");
                }
            }       
        } 
        //SQL Exception
        catch (SQLException e) {
            System.err.println("Terjadi kesalahan dalam mengedit data");
            System.err.println(e.getMessage());
        }
       }


       @Override
       public void search() throws SQLException{
           System.out.print("Cari Data Transaksi");
           System.out.println("");
           //Link Database
           String url = "jdbc:mysql://localhost:3306/tbpbo";
           try{
           System.out.print("Masukkan IDTransaksi yang ingin dilihat : ");    
           Integer keyword = Integer.parseInt(input.nextLine());
           //Connect ke Database
           conn = DriverManager.getConnection(url,"root","");
           Statement statement = conn.createStatement();
           //SQL QUery Search(Select)
           String sql = "SELECT * FROM transaksi WHERE IDTransaksi LIKE '%"+keyword+"%'";
           ResultSet result = statement.executeQuery(sql);   
           //Menampilkan Data di Database yang di cari
           while(result.next()){
               System.out.print("\nIDTransaksi\t: ");
               System.out.print(result.getInt("IDTransaksi"));
               System.out.print("\nNama Pembeli\t: ");
               System.out.print(result.getString("NamaPembeli"));
               System.out.print("\nKode Token\t: ");
               System.out.print(result.getString("KodeToken"));
               System.out.print("\nNama Token\t: ");
               System.out.print(result.getString("NamaToken"));
               System.out.print("\nJenis Token\t: ");
               System.out.print(result.getInt("JenisToken"));
               System.out.print("\nTagihant\t: ");
               System.out.print(result.getInt("Tagihan"));
               System.out.print("\nBayar\t: ");
               System.out.print(result.getInt("Bayar"));
               System.out.print("\nKembali\t: ");
               System.out.print(result.getInt("Kembali"));
               System.out.print("\n");
           }
         }
         //Exception SQL
         catch (SQLException e) {
           System.err.println("Terjadi kesalahan");
         } 
         //Exception Program
         catch (InputMismatchException e) {
           System.err.println("Program Eror");
         }
       }

       @Override
       public void delete() throws SQLException {
           System.out.print("Hapus Data");
            //Link Database          
           String url = "jdbc:mysql://localhost:3306/tbpbo";
           try{
               //Memanggil methode display
               display();
               System.out.print("\nMasukan ID Transaksi yang akan Anda Hapus : ");
               Integer IDTransaksi = Integer.parseInt(input.nextLine());
               //SQL Query
               String sql = "DELETE FROM transaksi WHERE IDTransaksi = "+ IDTransaksi;
               //Connect ke Database
               conn = DriverManager.getConnection(url,"root","");
               Statement statement = conn.createStatement();
               //ResultSet result = statement.executeQuery(sql);
               
               if(statement.executeUpdate(sql) > 0){
                   System.out.println("Berhasil menghapus data ID Transaksi (Nomor "+IDTransaksi+")");
               }
          }
          //SQL Exception
           catch(SQLException e){
               System.out.println("Terjadi kesalahan dalam menghapus data");
           }
           //Program Exception
           catch(Exception e){
               System.out.println("masukan data yang benar");
           }
         }
}    

