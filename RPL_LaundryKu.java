import java.util.*;


public class RPL_LaundryKu {
	public static Scanner scan = new Scanner(System.in);
	public static String[] namaJenis = {"Pakaian","Bed Cover","Boneka"};
	public static int[] standarHarga = {5000,30000,15000};
	public static int[] expressHarga = {8000,35000,18000};
	public static String[][] kodePromo = {{"NewMember01","10"},{"Bersih100%","5"}};

	public static void main(String[] args) {
		System.out.println("Selamat datang di LaundryKu \n");
		System.out.println("Silahkan isi data diri anda");
		String[] biodata = biodata();
		String[][] order1 = order();
		String diskon1[] = diskon(order1);
		int hargaAwal = 0;
		
		System.out.println("\n\n=====================================================\n\n");
		System.out.println("LaundryKu");
		System.out.println("Nama Pemesan : " + biodata[0]);
		System.out.println("Kontak Pemesan : " + biodata[1]);
		System.out.println("Alamat Pemesan : " + biodata[2]);
		System.out.println("Rincian Pemesanan");
		
		for (int i = 0; i < order1.length; i++) {
			hargaAwal+= Integer.parseInt(order1[i][1]);
		}
		
		for (int i = 0; i < order1.length; i++) {
			System.out.println("Jenis Item : " + order1[i][0] + " (" + order1[i][2] +") Kg --> "+ order1[i][1]);
			
			
		}
		System.out.println("\n-------------------------------------------------------------------------------------\n");
		
		System.out.println("Kode Promo yang dipakai : " + diskon1[0]);
		System.out.println("Potongan 10 % Jika cucian diatas 10Kg : " + diskon1[1]);
		System.out.println("Harga Awal : " + hargaAwal);
		System.out.println("Harga Yang Harus Dibayar = " + diskon1[2] );

		
	}
	
	static String[] biodata() {

			boolean cek = false;
			String telepon, nama, alamat;
			
			System.out.print("Nama :");
			nama = scan.nextLine();
			
			while(nama.equals("")) {
				System.out.println("Nama tidak boleh kosong!\n");
				System.out.print("Masukan Nama: ");
				nama = scan.nextLine();
				System.out.println();
			}
			
			System.out.print("alamat :");
			alamat = scan.nextLine();
			while(alamat.equals("")) {
				System.out.println("Alamat tidak boleh kosong!\n");
				System.out.print("Masukan Alamat: ");
				alamat = scan.nextLine();
				System.out.println();
			}
			

			do {
				System.out.print("Nomor Telepon : ");
				telepon = scan.nextLine();
				cek = cekAngka(telepon);
				System.out.println();
				if(telepon.equals("")) {
					System.out.println("Nomor Telepon tidak boleh kosong!");
				}
				else if(cek == false) {
					System.out.println("Masukan harus berupa angka");
				}
			}while(cek== false);
			
			
			
			
			
			
			
			System.out.println("----------------------------------------------------------------");
			
			String[] data = {nama,telepon,alamat};
		
			return data;
			
			
	}

	static String[][] order() {
		String pilihJenis,tambahOrder,layanan;
		int hargaFix = 0;
		int[] hargaSementara = new int[2];
		
		ArrayList<String> jenis = new ArrayList<String>();
		ArrayList<Integer> harga = new ArrayList<Integer>();
		ArrayList<Integer> jumlahPesanan = new ArrayList<Integer>();

		boolean cek = false;
		boolean tambah = false;
		
		System.out.println("\nPilih Jenis layanan: ");
		System.out.println("1. Standar");
		System.out.println("2. Express");
		System.out.print("Pilih Layanan: ");
		layanan = scan.nextLine();
		System.out.println("");
		
		while(!layanan.equals("1") && !layanan.equals("2") ) {
			System.out.println("\nLayanan tidak tersedia");
			System.out.print("Pilih Layanan: ");
			layanan = scan.nextLine();
		}
		
		
		

		System.out.println("Pilih Jenis pakaian yang ingin dicuci:");
		System.out.println("1. Pakaian");
		System.out.println("2. Bed Cover");
		System.out.println("3. Boneka");
		pilihJenis= scan.nextLine();
		
		do {
			
			if (!pilihJenis.equals("1") && !pilihJenis.equals("2") && !pilihJenis.equals("3") ) {
				System.out.println("Masukan salah");
				System.out.println("Pilih Jenis pakaian yang ingin dicuci:");
				System.out.println("1. Pakaian");
				System.out.println("2. Bed Cover");
				System.out.println("3. Boneka");
				pilihJenis= scan.nextLine();
			}
	
			
			else if (tambah == true ) {
				System.out.println("Pilih Jenis pakaian yang ingin dicucippp:");
				System.out.println("1. Pakaian");
				System.out.println("2. Bed Cover");
				System.out.println("3. Boneka");
				pilihJenis= scan.nextLine();
				
				if (!pilihJenis.equals("1") && !pilihJenis.equals("2") && !pilihJenis.equals("3") ) {
					tambah = false;
					cek = false;
					
				}
				else {
					tambah=false;
				}
				
			}
			
			else  {
				int pilihJenisInt = Integer.parseInt(pilihJenis)-1;
				jenis.add(namaJenis[pilihJenisInt]);
				hargaSementara= detailOrder(layanan,pilihJenis);
				harga.add(hargaSementara[0]);
				hargaFix+=hargaSementara[0];
				jumlahPesanan.add(hargaSementara[1]);
				cek= true;
				
				System.out.println("Apakah ada pesanan lagi ? Y/N ");
				tambahOrder = scan.nextLine();
				if(tambahOrder.equalsIgnoreCase("Y")) {
					cek = false;
					tambah = true;
					
					
				}
			}
			
		
		}while(cek==false || tambah == true);
		
		
		
		String data[][] = new String[jenis.size()] [3];
		for (int i = 0; i < jenis.size(); i++) {
			data[i][0] = jenis.get(i);
			data[i][1] = String.valueOf(harga.get(i));
			data[i][2] = String.valueOf(jumlahPesanan.get(i));
			
		}
		
		
		
		
		return data;
	}
	
	static int[] detailOrder(String layanan, String pilihJenis) {
		String jumlah;
		int biaya = 0;
		System.out.println("Masukan jumlah : ");
		jumlah = scan.nextLine();
		while(cekAngka(jumlah)== false ) {
			System.out.println("\nMasukan harus angka!!\n");
			jumlah = scan.nextLine();
		}
		
		int layananInt = Integer.parseInt(layanan);
		int pilihJenisInt = Integer.parseInt(pilihJenis);
		int jumlahInt = Integer.parseInt(jumlah);

		
		if(layanan.equals("1")) {
			if (pilihJenis.equals("1")) {
				biaya = standarHarga[pilihJenisInt-1]*jumlahInt;
			}
			else if (pilihJenis.equals("2")) {
				biaya = standarHarga[pilihJenisInt-1]*jumlahInt;
			}
			else if (pilihJenis.equals("3")) {
				biaya = standarHarga[pilihJenisInt-1]*jumlahInt;
			}
			
		}
		else {
			if (pilihJenis.equals("1")) {
				biaya = expressHarga[pilihJenisInt-1]*jumlahInt;
			}
			else if (pilihJenis.equals("2")) {
				biaya = expressHarga[pilihJenisInt-1]*jumlahInt;
			}
			else if (pilihJenis.equals("3")) {
				biaya = expressHarga[pilihJenisInt-1]*jumlahInt;
			}
			
		}
		
		int jumlah1  = Integer.parseInt(jumlah);
		int[] save = {biaya, jumlah1};
		
		return save;
	}
	
	static String[] diskon(String[][] order) {
		
		String save[]= new String[3];
		String promo;
		String berat = "Tidak";
		String kode= "";
		int kodeFix = 0;
		int biaya = 0;
		int jumlah = 0;
		boolean cek1=true;
		do {
			System.out.print("\nPunya kode promo (Y/N) :   ");
			promo = scan.nextLine();
			if(promo.equalsIgnoreCase("y")) {
				System.out.print("\nMasukan Kode Promo : ");
				kode = scan.nextLine();
				for (int i = 0; i < kodePromo.length; i++) {
					if(kode.equalsIgnoreCase(kodePromo[i][0])) {
						kodeFix = Integer.parseInt(kodePromo[i][1]);
						cek1 = true;
						save[0] = kode +" ---> Mendapatkan diskon sebesar "+kodeFix + "%";
						break;
					}
					else if(i == kodePromo.length-1) {
						System.out.println("Maaf Kode salah");
						kode = "";
						cek1 = false;
					}
				}
				
			}
			
			
			else if(promo.equalsIgnoreCase("n")) {
				kode = "";
				save[0] = "-";
				break;
				
			}
			else {
				System.out.println("Inputan tidak sesuai");
				kode = "";
				cek1 = false;
			}
			
			
		}while(cek1==false);
		
		
		
		for (int i = 0; i < order.length; i++) {
			biaya += Integer.parseInt(order[i][1]);
			jumlah += Integer.parseInt(order[i][2]);
		}
		
		if(jumlah>= 10) {
			biaya = biaya - (biaya*10/100);
			berat = "Ya";
		}
		
		if (kodeFix!=0) {
			biaya  = biaya - (biaya*kodeFix/100);
		}
		
		
		
		save[1] = berat;
		save[2]= String.valueOf(biaya);
		 
			
		return save;
	}
	
	static boolean cekAngka(String angka) {
		boolean cek = false;
		for (int i = 0; i < angka.length(); i++) {
			if(angka.charAt(i)>=48 && angka.charAt(i)<=57)
				cek = true;
			else {
				cek = false;
				break;
			}
			
		}
		return cek;
	}

}
