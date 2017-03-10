package cn.networkSecurity;
import java.io.*;
public class railfencecipher{
	public void encrypt(String line,int rail)
	{
		System.out.println("Result:");
		int shift=0,p=0,itr;
		for(int i=0;i<rail;i++)
		{
			p=i;
			if(i==0||i==rail-1)
			shift=((rail-2)*2)+2;
			itr=1;
			while(p<line.length())
			{
				System.out.print(line.charAt(p));
				if(i!=0&&i!=rail-1)
				{
					shift=((rail*itr-itr)-p)*2;
				}
				p+=shift;
				itr++;
			}
		}
	}
	public void decrypt(String line,int arr[])
	{
		System.out.println("Result:");
		int ptr[]=new int[arr.length+1];
		int p1=0,p2=0,p3=0,c=1;
		boolean chk=true;
		System.out.print(line.charAt(ptr[p3]+p1));
		ptr[p3]++;
		while(c<line.length())
		{
			if(chk)
			{
				p1+=arr[p2];
				p2++;
				p3++;
			}
			else
			{
				p1-=arr[p2];
				p2--;
				p3--;
			}
			System.out.print(line.charAt(ptr[p3]+p1));
			c++;
			ptr[p3]++;
			if(p2==arr.length)
			{
				p2--;
				chk=false;
			}
			else if(p2==-1)
			{
				p2++;
				chk=true;
			}
		}
	}
	public static void main(String args[])throws IOException
	{
		railfencecipher obj=new railfencecipher();
		String line;
		int rail,arr[],temp;
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("MENU:\n1) Encryption:\n2) Decryption:");
		int choice=Integer.parseInt(in.readLine());
		System.out.println("Enter the string:");
		line=in.readLine();
		System.out.println("Enter the no of rails:");
		rail=Integer.parseInt(in.readLine());
		switch(choice)
		{
		case 1:
			temp=line.length()-rail;
			int spaces;
			if(temp%(rail-1)!=0)
			{
				spaces=(rail-1)-(temp%(rail-1));
				if((temp/(rail-1))%2!=0)
				{
					spaces+=rail-1;
				}
			}
			else
			{
				spaces=temp%(rail-1);
				if((temp/(rail-1))%2==0)
				{
					spaces+=rail-1;
				}
			}
			for(int g=0;g<spaces;g++)
				line+=' ';
			obj.encrypt(line,rail);
			break;
		case 2:
			temp=line.length()-rail;
			arr=new int[rail-1];
			if((temp/(rail-1))%2==0)
				arr[0]=1+(temp/(rail-1))/2;
			else
				arr[0]=1+((temp/(rail-1))+1)/2;
			arr[1]=arr[0]*2-2;
			for(int i=2;i<rail-1;i++)
				arr[i]=arr[1];
			obj.decrypt(line,arr);
			break;
		}
	}
}