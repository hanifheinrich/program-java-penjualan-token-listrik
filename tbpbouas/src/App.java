import java.util.Scanner;

import java.sql.*;

public class App {
	//static Scanner scanner;
	static Connection conn;
	
    public static void main(String[] args) {
		//Scanner
    	Scanner tbInput = new Scanner (System.in);
    	String pilihan;
    	boolean isLanjutkan = true;


		//link database
		String url = "jdbc:mysql://localhost:3306/tbpbo";
		String hi = "database transaksi";
		//exception
		try {
			//jbdcdriver
			//koneksi ke database
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url,"root","");
			System.out.println("Class Driver ditemukan!!!");
			//instantiate object
			eksekusi eksekusi = new eksekusi();

			//perulangan
			while (isLanjutkan) {
				System.out.println("----------------------");
				//String Method
				System.out.println(hi.toUpperCase());
				eksekusi.waktu();
				System.out.println("");
				System.out.println("----------------------");
				eksekusi.array();
				
				System.out.print("\nPilihan anda (1/2/3/4/5): ");
				pilihan = tbInput.next();
			
				switch (pilihan) {
				case "1":
					eksekusi.display();
					break;
				case "2":
					eksekusi.insert();
					break;
				case "3":
					eksekusi.update();
					break;
				case "4":
					eksekusi.delete();
					break;
				case "5":
					eksekusi.search();
					break;
				default:
					System.err.println("\nInput anda tidak ditemukan\nSilakan pilih [1-5]");
				}
				
				System.out.print("\nApakah Anda ingin melanjutkan [y/n]? ");
				pilihan = tbInput.next();
				isLanjutkan = pilihan.equalsIgnoreCase("y");

			}
			System.out.println("Program selesai...");
			
		}
		//exception driver tidak ditemukan
		catch(ClassNotFoundException ex) {
			System.err.println("Driver Error");
			System.exit(0);
		}

		//exception tidak berhasil connect ke database
		catch(SQLException e){
			System.err.println("Tidak berhasil koneksi");
			tbInput.close();
		}
    }
}
