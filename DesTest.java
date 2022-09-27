import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DesTest {
	
	Des des=new Des();

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testStringToBits() {
		String result=des.stringToBits("bonjour");
		System.out.println("stringToBits : "+result);
	}
	
	@Test
	public void testBitsToString() {
		int[] blocs=new int[8];
		for (int i=0; i<blocs.length; i++) {
			blocs[i]=des.binaire[des.r.nextInt(des.binaire.length)];
		}
		System.out.println("bitsToStringd: "+des.bitsToString(blocs));
	}
	
	@Test
	public void testGenerePermutation() {
		String result=des.generePermutation(8);
		System.out.println("generePermutation : "+result);
	}
	
	@Test
	public void testPermutationEtInvPermutation() {
		int[] tab1= {1,2,3,4};
		int[] tab2= {1,2,1,2};
		int[] tab= {2, 4, 4, 6};
		int[] tab3= {1,2,3,4};
		String result=des.permutation(tab2,tab1);
		assertEquals(result,Arrays.toString(tab));
		String result2=des.invPermutation(tab2, tab1);
		assertEquals(result2,Arrays.toString(tab3));
	}
	
	@Test
	public void testDecoupage() {
		int[] blocs2={0,1,1,1,0,1};
		String result=des.decoupage(blocs2,6);
		int[][] tab2= {{0},{1},{1},{1},{0},{1}};
		assertEquals(result,Arrays.deepToString(tab2));
	}
	
	@Test
	public void testRecollageBlocs() {
		int[][] tab3={{1,2},{3,4}};
		String result=des.recollage_bloc(tab3);
		int[] tab= {1,2,3,4};
		assertEquals(result,Arrays.toString(tab));
	}
	
	@Test
	public void testGenereClé() {
		String result=des.génèreClé(1);
		System.out.println("genereClé : "+result);
	}
	
	@Test
	public void testDecalleGauche() {
		int[] blocs2={0,1,1,1,0,1};
		String result=des.decalle_gauche(blocs2, 4);
		int[] tab= {-4,-3,-3,-3,-4,-3};
		assertEquals(result,Arrays.toString(tab));
	}
	
	@Test
	public void testXor() {
		int[] blocs2={0,1,1,1,0,1};
		int[] blocs3= {1,1,0,0,1,1};
		String result=des.xor(blocs2, blocs3);
		int[] tab= {1,0,1,1,1,0};
		assertEquals(result,Arrays.toString(tab));
	}
	
	@Test
	public void testFonctionS() {
		int[] blocs2={0,1,1,1,0,1};
		int[] tab= {0,0,1,1};
		String result=des.fonction_S(blocs2);
		assertEquals(result,Arrays.toString(tab));
	}
	
	@Test
	public void testFonctionF() {
		String result=des.fonction_F(des.masterKey, des.E);
		System.out.println("fonction_F : "+result);
	}
	
	@Test
	public void testCrypte() {
		String result=des.crypte("bonjour");
		System.out.println("crypte : "+result);
	}
	
	@Test
	public void testDecrypte() {
		int[] blocs=new int[64];
		for (int i=0; i<blocs.length; i++) {
			blocs[i]=des.binaire[des.r.nextInt(des.binaire.length)];
		}
		String result=des.decrypte(blocs);
		System.out.println("decrypte : "+result);
	}
}
