import java.io.*;
public class ErrorDetection {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int [] data, div, divisor, rem, crc;
        int data_bits, divisor_bits, tot_length;
        System.out.println("Enter the number of data bits : ");
        data_bits = Integer.parseInt(br.readLine());
        data = new int [data_bits];
        System.out.println("Enter data bits: ");
        for(int i=0; i<data_bits; i++)
            data[i] = Integer.parseInt(br.readLine());
        System.out.println("Enter number of data bits in divisor : ");
        divisor_bits = Integer.parseInt(br.readLine());
        divisor = new int[divisor_bits];
        System.out.println("Enter divisor bits : ");
        for(int i=0; i<divisor_bits; i++)
            divisor[i] = Integer.parseInt(br.readLine());
        tot_length = data_bits + divisor_bits - 1;
        div = new int[tot_length];
        rem = new int[tot_length];
        crc = new int[tot_length];
        for(int i=0; i<data.length; i++)
            div[i] = data[i];
        System.out.println("Dividend after appending 0's are : ");
        for(int i=0; i<div.length;i++)
            System.out.println(div[i]);
        System.out.println();
        for(int j=0; j<div.length; j++)
            rem[j] = div[j];
        rem = divide(div, divisor, rem);
        for(int i=0; i<rem.length; i++)
            crc[i] = (div[i]^rem[i]);
        System.out.println("CRC Code : ");
        for(int i=0; i<crc.length; i++)
            System.out.print(crc[i]);
        System.out.println();
        System.out.println("Enter CRC code of "+tot_length+" bits : ");
        for(int i=0; i<crc.length; i++)
            crc[i] = Integer.parseInt(br.readLine());
        for(int j=0; j<crc.length; j++)
            rem[j] = crc[j];
        rem = divide(crc, divisor, rem);
        for(int i=0; i<rem.length; i++) {
            if(rem[i]!=0) {
                System.out.println("Error!");
                break;
            }
            if(i==rem.length-1) {
                System.out.println("No Error!");
            }
        }
    }

    private static int[] divide(int[] div, int[] divisor, int[] rem) {
        int cur = 0;
        while(true) {
            for(int i=0; i<divisor.length; i++)
                rem[cur+i] = (rem[cur+i]^divisor[i]);
            while(rem[cur]==0 && cur!=rem.length-1)
                cur++;
            if((rem.length-cur)<divisor.length)
                break;
        }
        return rem;
    }
}