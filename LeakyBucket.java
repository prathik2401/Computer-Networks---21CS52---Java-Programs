import java.util.Scanner;
public class LeakyBucket {
	public static void main(String[] args) throws Exception {
		int n, incoming, outgoing, bucketsize, store=0;
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter bucket size, outgoing rate, number of inputs and incoming size : ");
		bucketsize = scan.nextInt();
		outgoing = scan.nextInt();
		n = scan.nextInt();
		incoming = scan.nextInt();
		while(n!=0) {
			System.out.println("\nIncoming size is " + incoming);
			if(incoming< (bucketsize-store)) {
				store += incoming;
				System.out.println("Buffer Size is "+ store+ " out of "+bucketsize);
			}
			else {
				System.out.println("Packet Loss : "+(incoming - (bucketsize -store)));
				store = bucketsize;
				System.out.println("Buffer Size is "+store+" out of "+bucketsize);
			}
			store -= outgoing;
			System.out.println("After outgoing : "+store+" packets left out of "+bucketsize+" in buffer");
			n--;
			Thread.sleep(3000);
		}
		scan.close();
	}
}