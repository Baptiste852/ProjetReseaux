import java.util.Arrays;
import java.util.Random;

public class Des {
	
	public int taille_bloc=64;
	public int taille_sous_bloc=32;
	public int nb_ronde=16;
	public int[] tab_decalage= {1,1,2,2,2,2,2,2,1,2,2,2,2,2,2,1};
	public int[] perm_initiale= {58,50,42,34,26,18,10,2,
			60,52,44,36,28,20,12,4,
			62,54,46,38,30,22,14,6,
			64,56,48,40,32,24,16,8,
			57,49,41,33,25,17,9,1,
			59,51,43,35,27,19,11,3,
			61,53,45,37,29,21,13,5,
			63,55,47,39,31,23,15,7};
	public int[] E= {32,1,2,3,4,5,
			4,5,6,7,8,9,
			8,9,10,11,12,13,
			12,13,14,15,16,17,
			16,17,18,19,20,21,
			20,21,22,23,24,25,
			24,25,26,27,28,29,
			28,29,30,31,32,1};
	
	public int[] masterKey;
	public int[][] tab_cles;
	
	Random r=new Random();
	public int[][] S= {{14,4,13,1,2,15,11,8,3,10,6,12,5,9,0,7},
			{0,15,7,4,14,2,13,1,10,6,12,11,9,5,3,8},
			{4,1,14,8,13,6,2,11,15,12,9,7,3,10,5,0},
			{15,12,8,2,4,9,1,7,5,11,3,14,10,0,6,13}
	};
	public int[] binaire= {0,1};
	public String[] alphabet= {"a","b","c","d","e","f","g","h",
			"i","j","k","l","m","n","o","p","q","r","s","t","u",
			"v","w","x","y","z","A","B","C","D","E","F","G","H",
			"I","J","K","L","M","N","O","P","Q","R","S","T","U",
			"V","W","X","Y","Z","é","è","ê","î","?",";",",",":",
			"!","%","^","*"};
	
	int[] G0=new int[32];
	int[] D0=new int[32];
	public String G1;
	public String D1;
			
	public Des() {
		this.masterKey=new int[64];
		for (int i=0; i<64; i++) {
			this.masterKey[i]=binaire[r.nextInt(binaire.length)];
		}
		this.tab_cles=new int[this.nb_ronde][64];
	}
	
	public int[] string_to_inttab(String tab) {
		int[] tab1=new int[32];
		for (int i=0; i<tab1.length; i++) {
			tab1[i]=Character.getNumericValue(tab.charAt(i));
		}
		return tab1;
	}
	
	public String tab_to_nombre(String tab) {
		String out="";
		for (int j=1; j<tab.length(); j+=3) {
			out+=tab.charAt(j);
		}
		return out;
	}
	
	public int[] string_to_inttab3(String tab) {
		int[] tab1=new int[4];
		for (int i=0; i<tab1.length; i++) {
			tab1[i]=Character.getNumericValue(tab_to_nombre(tab).charAt(i));
		}
		return tab1;
	}
	
	public int[][] string_to_inttab2(String tab) {
		int[][] tab1=new int[2][tab.length()/2];
		for (int i=0; i<tab1.length; i++) {
			for (int j=0; j<tab1[i].length; j++) {
				tab1[i][j]=Character.getNumericValue(tab.charAt(i));
			}
		}
		return tab1;
	}
	
	public String crypte(String message_clair) {
		int[] tab3=this.perm_initiale;
		for (int i=0; i<G0.length; i++) {
			G0[i]=tab3[i];
			D0[i]=tab3[i+32];
		}
		for (int i=0; i<G0.length; i++) {
			G0[i]=binaire[r.nextInt(binaire.length)];
			D0[i]=binaire[r.nextInt(binaire.length)];
		}
		D1=xor(G0,string_to_inttab(fonction_F(tab_cles[0],D0)));
		G1=D1;
		int[][] tab7= {string_to_inttab(tab_to_nombre(G1)),string_to_inttab(tab_to_nombre(D1))};
		String tab8=recollage_bloc(tab7);
		return tab8;
	}
	
	public String decrypte(int[] messageCodé) {
		int[] tab1=new int[4];
		int[] tab2=new int[4];
		int[] tab3=new int[4];
		int[] tab4=new int[4];
		int[] tab5=new int[4];
		int[] tab6=new int[4];
		int[] tab7=new int[4];
		int[] tab8=new int[4];
		String t1="";
		String t2="";
		String t3="";
		String t4="";
		String t5="";
		String t6="";
		String t7="";
		String t8="";
		int t_one=0;
		int t_two=0;
		int t_three=0;
		int t_four=0;
		int t_five=0;
		int t_six=0;
		int t_seven=0;
		int t_eight=0;
		for (int i=0; i<tab1.length; i++) {
			tab1[i]=messageCodé[i];
			tab2[i]=messageCodé[i+4];
			tab3[i]=messageCodé[i+8];
			tab4[i]=messageCodé[i+12];
			tab5[i]=messageCodé[i+16];
			tab6[i]=messageCodé[i+20];
			tab7[i]=messageCodé[i+24];
			tab8[i]=messageCodé[i+28];
			t1+=tab1[i];
			t_one=Integer.parseInt(t1, 2);
			t2+=tab2[i];
			t_two=Integer.parseInt(t2, 2);
			t3+=tab3[i];
			t_three=Integer.parseInt(t3, 2);
			t4+=tab4[i];
			t_four=Integer.parseInt(t4, 2);
			t5+=tab5[i];
			t_five=Integer.parseInt(t5, 2);
			t6+=tab6[i];
			t_six=Integer.parseInt(t6, 2);
			t7+=tab7[i];
			t_seven=Integer.parseInt(t7, 2);
			t8+=tab8[i];
			t_eight=Integer.parseInt(t8, 2);
		}
		int ligne1=0;
		int colonne1=0;
		int ligne2=0;
		int colonne2=0;
		int ligne3=0;
		int colonne3=0;
		int ligne4=0;
		int colonne4=0;
		int ligne5=0;
		int colonne5=0;
		int ligne6=0;
		int colonne6=0;
		int ligne7=0;
		int colonne7=0;
		int ligne8=0;
		int colonne8=0;
		for (int i=0; i<S.length; i++) {
			for (int j=0; j<S[i].length; j++) {
				if (S[i][j]==t_one) {
					ligne1=i;
					colonne1=j;
				} else if (S[i][j]==t_two) {
					ligne2=i;
					colonne2=j;
				} else if (S[i][j]==t_three) {
					ligne3=i;
					colonne3=j;
				}else if (S[i][j]==t_four) {
					ligne4=i;
					colonne4=j;
				}else if (S[i][j]==t_five) {
					ligne5=i;
					colonne5=j;
				}else if (S[i][j]==t_six) {
					ligne6=i;
					colonne6=j;
				}else if (S[i][j]==t_seven) {
					ligne7=i;
					colonne7=j;
				}else if (S[i][j]==t_eight) {
					ligne8=i;
					colonne8=j;
				}
			}
		}
		String l1=Integer.toBinaryString(ligne1);
		String c1=Integer.toBinaryString(colonne1);
		String l2=Integer.toBinaryString(ligne2);
		String c2=Integer.toBinaryString(colonne2);
		String l3=Integer.toBinaryString(ligne3);
		String c3=Integer.toBinaryString(colonne3);
		String l4=Integer.toBinaryString(ligne4);
		String c4=Integer.toBinaryString(colonne4);
		String l5=Integer.toBinaryString(ligne5);
		String c5=Integer.toBinaryString(colonne5);
		String l6=Integer.toBinaryString(ligne6);
		String c6=Integer.toBinaryString(colonne6);
		String l7=Integer.toBinaryString(ligne7);
		String c7=Integer.toBinaryString(colonne7);
		String l8=Integer.toBinaryString(ligne8);
		String c8=Integer.toBinaryString(colonne8);
		String line1=l1;
		String line2=l2;
		String line3=l3;
		String line4=l4;
		String line5=l5;
		String line6=l6;
		String line7=l7;
		String line8=l8;
		if (line1.length()==1) {
			line1="0"+l1;
		}if (line2.length()==1) {
			line2="0"+l2;
		}if (line3.length()==1) {
			line3="0"+l3;
		}if (line4.length()==1) {
			line4="0"+l4;
		}if (line5.length()==1) {
			line5="0"+l5;
		}if (line6.length()==1) {
			line6="0"+l6;
		}if (line7.length()==1) {
			line7="0"+l7;
		}if (line8.length()==1) {
			line8="0"+l8;
		}
		String tab9=line1.charAt(0)+c1+line1.charAt(1);
		String tab10=line2.charAt(0)+c2+line2.charAt(1);
		String tab11=line3.charAt(0)+c3+line3.charAt(1);
		String tab12=line4.charAt(0)+c4+line4.charAt(1);
		String tab13=line5.charAt(0)+c5+line5.charAt(1);
		String tab14=line6.charAt(0)+c6+line6.charAt(1);
		String tab15=line7.charAt(0)+c7+line7.charAt(1);
		String tab16=line8.charAt(0)+c8+line8.charAt(1);
		String tab=tab9+tab10+tab11+tab12+tab13+tab14+tab15+tab16;
		int[] tabfinal=new int[64];
		for (int i=0; i<tabfinal.length; i++) {
			if (i<=tab.length()-1) {
				tabfinal[i]=tab.charAt(i);
			} else {
				tabfinal[i]=binaire[r.nextInt(binaire.length)];
			}
		}
		return bitsToString(tabfinal);
	}
	
	public String stringToBits(String message) {
		int[] tabBinairesCryptés=new int[message.length()];
		for (int i=0; i<message.length(); i++) {
			tabBinairesCryptés[i]=binaire[r.nextInt(binaire.length)];
		}
		return Arrays.toString(tabBinairesCryptés);
	}
	
	public String bitsToString(int[] blocs) {
		String message="";
		for (int i=0; i<blocs.length; i++) {
			message+=alphabet[r.nextInt(alphabet.length)];
		}
		return message;
	}
	
	public String generePermutation(int taille) {
		int[] tab=new int[taille];
		for (int i=0; i<tab.length; i++) {
			tab[i]=r.nextInt(1,3);
		}
		return Arrays.toString(tab);
	}
	
	public String permutation(int[] tab_permutation,int[] bloc) {
		for (int i=0; i<bloc.length; i++) {
			bloc[i]+=tab_permutation[i];
		}
		return Arrays.toString(bloc);
	}
	
	public String invPermutation(int[] tab_permutation, int[] bloc) {
		for (int i=0; i<bloc.length; i++) {
			bloc[i]-=tab_permutation[i];
		}
		return Arrays.toString(bloc);
	}
	
	public String decoupage(int[] bloc,int nbBlocs) {
		int[][] tab=new int[nbBlocs][bloc.length/nbBlocs];
		for (int i=0; i<tab.length; i++) {
			for (int j=0; j<tab[i].length; j++) {
				tab[i][j]=bloc[i+j];
			}
		}
		return Arrays.deepToString(tab);
	}
	
	public String recollage_bloc(int[][] blocs) {
		int[] tab=new int[blocs.length*blocs[0].length];
		for (int i=0; i<tab.length; i++) {
			tab[i]=blocs[i/blocs[0].length][i % blocs.length];
		}
		return Arrays.toString(tab);
	}
	
	public String génèreClé(int n) {
		int[] tab=new int[56];
		for (int i=0; i<tab.length; i++) {
			tab[i]=masterKey[i];
		}
		int[] tab1=new int[28];
		int[] tab2=new int[28];
		for (int i=0; i<tab.length/2; i++) {
			tab1[i]=tab[i];
			tab2[i]=tab[i+28];
			tab1[i]-=tab_decalage[n];
			tab2[i]-=tab_decalage[n];
		}
		int[] tab3=new int[56];
		for (int j=0; j<tab1.length; j++) {
			tab3[j]=tab1[j];
			tab3[j+28]=tab2[j];
		}
		int[] tab4=new int[48];
		for (int i=0; i<tab4.length; i++) {
			tab4[i]=tab3[i];
		}
		int[] rondes= {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
		for (int i=0; i<tab4.length; i++) {
			tab_cles[0][i]=tab4[i];
		}
		return Arrays.toString(tab4);
	}
	
	public String decalle_gauche(int[] bloc,int nbCran) {
		int[] tab=new int[bloc.length];
		for (int i=0; i<bloc.length; i++) {
			tab[i]=bloc[i]-nbCran;
		}
		return Arrays.toString(tab);
	}
	
	public String xor(int[] tab1,int[] tab2) {
		int[] tab=new int[tab1.length];
		for (int i=0; i<tab1.length; i++) {
			if (tab1[i]==tab2[i] && (tab1[i]==0 || tab1[i]==1)) {
				tab[i]=0;
			} else {
				tab[i]=1;
			}
		}
		return Arrays.toString(tab);
	}
	
	public String fonction_S(int[] tab) {
		int ligne=Integer.parseInt(String.valueOf(tab[0])+""+String.valueOf(tab[5]),2);
		int colonne=Integer.parseInt(String.valueOf(tab[1])+""+String.valueOf(tab[2])
		+""+String.valueOf(tab[3])+""+String.valueOf(tab[4]),2);
		int valeur=S[ligne][colonne];
		String str = Integer.toBinaryString(valeur);
		String str2="";
		if (str.length()==1) {
			str2+="000"+str;
		} else if (str.length()==2) {
			str2+="00"+str;
		} else if (str.length()==3) {
			str2+="0"+str;
		} else {
			str2=str;
		}
		int[] tableau=new int[4];
		for (int i=0; i<str2.length(); i++) {
			tableau[i]=Character.getNumericValue(str2.charAt(i));
		}
		for (int i=0; i<S.length; i++) {
			for (int j=0; j<S[0].length; j++) {
				S[i][j]=r.nextInt(0,16);
			}
		}
		return Arrays.toString(tableau);
	}
	
	public String fonction_F(int[] uneCle,int[] unD) {
		int[] tab=new int[E.length];
		for (int i=0; i<E.length; i++) {
			if (E[i]==uneCle[i] && (E[i]==0 || E[i]==1)) {
				tab[i]=0;
			} else {
				tab[i]=1;
			}
		}
		int[] tab1=new int[6];
		int[] tab2=new int[6];
		int[] tab3=new int[6];
		int[] tab4=new int[6];
		int[] tab5=new int[6];
		int[] tab6=new int[6];
		int[] tab7=new int[6];
		int[] tab8=new int[6];
		for (int i=0; i<tab1.length; i++) {
			tab1[i]=tab[i];
			tab2[i]=tab[i+5];
			tab3[i]=tab[i+11];
			tab4[i]=tab[i+17];
			tab5[i]=tab[i+23];
			tab6[i]=tab[i+29];
			tab7[i]=tab[i+35];
			tab8[i]=tab[i+41];
		}
		String ts1="";
		String ts2="";
		String ts3="";
		String ts4="";
		String ts5="";
		String ts6="";
		String ts7="";
		String ts8="";
		for (int i=0; i<nb_ronde; i++) {
			ts1=fonction_S(tab1);
			ts2=fonction_S(tab2);
			ts3=fonction_S(tab3);
			ts4=fonction_S(tab4);
			ts5=fonction_S(tab5);
			ts6=fonction_S(tab6);
			ts7=fonction_S(tab7);
			ts8=fonction_S(tab8);
		}
		int[] tableau=new int[32];
		for (int i=0; i<string_to_inttab3(ts1).length; i++) {
			tableau[i]=string_to_inttab3(ts1)[i];
			tableau[i+4]=string_to_inttab3(ts2)[i];
			tableau[i+8]=string_to_inttab3(ts3)[i];
			tableau[i+12]=string_to_inttab3(ts4)[i];
			tableau[i+16]=string_to_inttab3(ts5)[i];
			tableau[i+20]=string_to_inttab3(ts6)[i];
			tableau[i+24]=string_to_inttab3(ts7)[i];
			tableau[i+28]=string_to_inttab3(ts8)[i];
		}
		return Arrays.toString(tableau);
	}

	public static void main(String[] args) {
		
		
	}

}
