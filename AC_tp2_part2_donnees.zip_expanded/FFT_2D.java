import java.io.IOException;

public class FFT_2D {

	//renvoie la TFD d'une image de complexes
	public static CpxImg FFT(CpxImg I) {

		CpxImg out = new CpxImg(I.taille());

		// FFT 1D sur les lignes
		for (int k = 0; k < I.taille(); k++)
			out.set_line(k,FFT_1D.FFT(I.get_line(k)));

		// transposition
		out.transpose();

		// FFT 1D sur les "nouvelles" lignes de out (anciennes colonnes)
		for (int k = 0; k < I.taille(); k++)
			out.set_line(k,FFT_1D.FFT(out.get_line(k)));

		//on re transpose pour revenir dans le sens de d�part
		out.transpose();

		//on divise par la taille de I
		out.multiply(1./I.taille());
		return out.recentrage();
	}

	//renvoie la TFD inverse d'une images de complexes
	public static CpxImg FFT_inverse(CpxImg I) {
		I = I.recentrage();
		CpxImg out = new CpxImg(I.taille());
		for (int k = 0; k < I.taille(); k++)
			out.set_line(k, I.get_line(k).conjugue());

		out = FFT(out).recentrage();
		for (int k = 0; k < I.taille(); k++)
			out.set_line(k, out.get_line(k).conjugue());
		return out;
	}

	// compression par mise � z�ro des coefficients de fr�quence 
	// FI contient la TDF de I 
	// Dans FI on met � z�ros tous les coefficients correspondant � des fr�quences inf�rieures � k
	public static void compression(CpxImg FI, int k) {
		// A COMPLETER
		int n = FI.taille();
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				// on ne garde que les coefficients qui sont 
				// dans la fenêtre centrée en [n/2 ; n/2]
				// de largeur [(n/2)-k ; (n/2)+k] et de longueur [(n/2)-k ; (n/2)+k]
				double min = ((n/2)-k);
				double max = ((n/2)+k);
				if(i<min || i>max || j<min || j>max) {
					FI.set_p_reel(i, j, 0);
					FI.set_p_imag(i, j, 0);
				}
			}
		}
	}

	// compression par seuillage des coefficients faibles
	// FI contient la TDF de I 
	// Dans FI on met � z�ros tous les coefficients dont le module est inf�rieur � seuil 
	// on renvoie le nombre de coefficients conserv�s 
	public static int compression_seuil(CpxImg FI, double seuil){
		//A COMPLETER
		int compteur = 0;
		int total = 262144;
		int n = FI.taille();
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				//compteur++;
				double reel = FI.get_p_reel(i, j);
				double img  = FI.get_p_imag(i, j);
				double module = Math.sqrt(reel*reel + img*img);	
				if(module<seuil) {
					compteur++;
					FI.set_p_reel(i, j, 0);
					FI.set_p_imag(i, j, 0);

				}
			}
		}		
		return total-compteur;
	}

	public static void main(String[] args) {
		//question 1
		float a = 0;
		double[] t = {a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a};
		System.out.println("question 1 : "+FFT_1D.FFT(t)+"\n");

		//question 2
		double [] t0 = new double [16];
		int k = 0;
		for(int j = 0; j < 16; j++) {
			t0[j] = Math.cos(2*Math.PI*k*j/16);
		}
		System.out.println("question 2 : "+FFT_1D.FFT(t0)+"\n"); 
		double [] t0bis = new double [16];
		int k0 = 15;
		for(int j = 0; j < 16; j++) {
			t0bis[j] = Math.cos(2*Math.PI*k0*j/16);
		}
		System.out.println("question 2_k_different : "+FFT_1D.FFT(t0bis)+"\n");

		//question 3
		int n=10;
		double[] tab= new double[n];
		for(int i=0;i<n;i++) {
			tab[i]=Math.cos((2*Math.PI*i)/n)+(1/2)*Math.cos((2*Math.PI*3*i)/n);
		}
		System.out.println("question 3 : "+FFT_1D.FFT(tab)+"\n");

		//question 4
		double [] t1 = new double [16];
		for(int i = 0; i < 16; i++) {
			t1[i] = 4 + 2*Math.sin(4*Math.PI*i/16) + Math.cos(14*Math.PI*i/16);
		}
		System.out.println("question 4 : "+FFT_1D.FFT(t1)+"\n");

		try {			
			//PLACEZ ICI VOS TESTS en 2D

			String nomfichier2 ="mire1";
			String pathfichier2 = nomfichier2;
			BytePixmap BP2 = new BytePixmap(pathfichier2+".pgm");
			CpxImg I2 = new CpxImg(BP2);
			CpxImg Itransformed2 = FFT(I2);
			BP2 = Itransformed2.convert_to_BytePixmap();
			BP2.write(pathfichier2+"_fft.pgm");

			String nomfichier3 ="mire2";
			String pathfichier3 = nomfichier3;
			BytePixmap BP3 = new BytePixmap(pathfichier3+".pgm");
			CpxImg I3 = new CpxImg(BP3);
			CpxImg Itransformed3 = FFT(I3);
			BP3 = Itransformed3.convert_to_BytePixmap();
			BP3.write(pathfichier3+"_fft.pgm");

			String nomfichier4 ="mire3";
			String pathfichier4 = nomfichier4;
			BytePixmap BP4 = new BytePixmap(pathfichier4+".pgm");
			CpxImg I4 = new CpxImg(BP4);
			CpxImg Itransformed4 = FFT(I4);
			BP4 = Itransformed4.convert_to_BytePixmap();
			BP4.write(pathfichier4+"_fft.pgm");

			String nomfichier5 ="fingerprint";
			String pathfichier5 = nomfichier5;
			BytePixmap BP5 = new BytePixmap(pathfichier5+".pgm");
			CpxImg I5 = new CpxImg(BP5);
			CpxImg Itransformed5 = FFT(I5);
			BP5 = Itransformed5.convert_to_BytePixmap();
			BP5.write(pathfichier5+"_fft.pgm");

			BytePixmap BPBarbara = new BytePixmap("barbara_512.pgm");
			CpxImg IBarbara = new CpxImg(BPBarbara);
			IBarbara = FFT(IBarbara);
			BPBarbara = IBarbara.convert_to_BytePixmap();
			BPBarbara.write("TFD_Barbara.pgm");

			// exercice 4
			BytePixmap BPtigre = new BytePixmap("tigre_512.pgm");
			CpxImg IBtigre = new CpxImg(BPtigre);
			IBtigre = FFT(IBtigre);
			IBtigre.set_p_reel(IBtigre.taille()/2,IBtigre.taille()/2, 0);
			IBtigre.set_p_imag(IBtigre.taille()/2,IBtigre.taille()/2, 0);
			CpxImg IFFT_inverse_tigre = FFT_inverse(IBtigre);
			BPtigre = IFFT_inverse_tigre.convert_to_BytePixmap();
			BPtigre.write("central.pgm");

			BytePixmap BPtigre_plus1 = new BytePixmap("tigre_512.pgm");
			CpxImg IBtigre_plus1 = new CpxImg(BPtigre_plus1);
			IBtigre_plus1 = FFT(IBtigre_plus1);
			IBtigre_plus1.set_p_reel(IBtigre_plus1.taille()/2+1,IBtigre_plus1.taille()/2, 0);
			IBtigre_plus1.set_p_imag(IBtigre_plus1.taille()/2+1,IBtigre_plus1.taille()/2, 0);
			CpxImg IFFT_inverse_tigre_plus1 = FFT_inverse(IBtigre_plus1);
			BPtigre_plus1 = IFFT_inverse_tigre_plus1.convert_to_BytePixmap();
			BPtigre_plus1.write("plus_1.pgm");;

			// exercice 5
			// compression tigre
			BytePixmap BP8 = new BytePixmap("tigre_512.pgm");
			CpxImg I8 = new CpxImg(BP8);
			CpxImg I8FFT = FFT(I8);
			compression(I8FFT,162);
			BP8=I8FFT.convert_to_BytePixmap();
			BP8.write("tigre_512_compression.pgm");
			// FFT inverse
			CpxImg I8transformed = FFT_inverse(I8FFT);
			BP8 = I8transformed.convert_to_BytePixmap();
			BP8.write("tigre_512_compression_puis_inverse.pgm");

			//compression barbara
			BytePixmap BP10 = new BytePixmap("barbara_512.pgm");
			CpxImg I10 = new CpxImg(BP10);
			CpxImg I10FFT = FFT(I10);
			compression(I10FFT,162);
			BP10=I10FFT.convert_to_BytePixmap();
			BP10.write("barbara_512_compression.pgm");
			// FFT inverse
			CpxImg I10transformed = FFT_inverse(I10FFT);
			BP10 = I10transformed.convert_to_BytePixmap();
			BP10.write("barbara_512_compression_puis_inverse.pgm");

			// exercice 6 - compression seuil tigre
			// compression
			BytePixmap BP9 = new BytePixmap("tigre_512.pgm");
			CpxImg I9 = new CpxImg(BP9);
			CpxImg I9FFT = FFT(I9);
			compression_seuil(I9FFT,35.633);
			System.out.println(compression_seuil(I9FFT,35.633));
			BP9 = I9FFT.convert_to_BytePixmap();
			BP9.write("tigre_compression_seuil.pgm");
			// FFT inverse
			CpxImg I9transformed = FFT_inverse(I9FFT);
			BP9 = I9transformed.convert_to_BytePixmap();
			BP9.write("tigre_compression_seuil_puis_inverse.pgm");

			//compression seuil barbara
			BytePixmap BP11 = new BytePixmap("barbara_512.pgm");
			CpxImg I11 = new CpxImg(BP11);
			CpxImg I11FFT = FFT(I11);
			compression_seuil(I11FFT,30.669536411235120);
			System.out.println(compression_seuil(I11FFT,30.669536411235120));
			BP11 = I11FFT.convert_to_BytePixmap();
			BP11.write("barbara_compression_seuil.pgm");
			// FFT inverse
			CpxImg I11transformed = FFT_inverse(I11FFT);
			BP11 = I11transformed.convert_to_BytePixmap();
			BP11.write("barbara_compression_seuil_puis_inverse.pgm");



		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
