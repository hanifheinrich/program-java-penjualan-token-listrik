import java.sql.SQLException;

//implement interface
public class crud implements pengolahan_data{
    //deklarasi variable
    String KodeToken;
    String NamaPembeli;
    String NamaToken;
    Integer IDTransaksi;
    Integer JenisToken;
    Integer Tagihan;
    Integer Bayar;
    Integer Kembali;

    //methode menampilkan menu pilihan
    public void ListToken(){
        System.out.println("==============================");
        System.out.println("===========LIST TOKEN=========");
        System.out.println("==============================");
        System.out.println("1.Token PLN 20  // Rp.21.000");
        System.out.println("2.Token PLN 50  // Rp.51.000");
        System.out.println("3.Token PLN 100 // Rp.101.000");
        System.out.println("4.Token PLN 200 // Rp.200.000");
        System.out.println("5.Token PLN 500 // Rp.500.000");
        System.out.println("6.Token PLN 1jt // Rp.1000.000");
        System.out.println("==============================\n");
    }

    //method menambahkan data
    public void insert() throws SQLException{
    }

    //method menampilkan data
    public void display() throws SQLException{
    }

    //method mengubah data
    public void update() throws SQLException{
    }

    //method mencari data
    public void search() throws SQLException{
    }

    //method menghapus data
    public void delete()throws SQLException{
    }
}
