import java.util.Random;
import java.util.*;
class DiskScheduling
{
	static void sort(int a[],int n)
	{
		int temp;
		Random r = new Random();
		int x = r.nextInt();

		for(int i=0;i<n-1;i++)
			for(int j=0;j<n-1;j++)
				if(a[j]>a[j+1])
				{
						temp=a[j];
						a[j]=a[j+1];
						a[j+1]=temp;
				}
	}
	static void fcfs(int q[],int n,int ini)
	{
		int sum=0,prev=ini;
		System.out.print("Path: ");
		for(int i=0;i<n;i++)
		{
			System.out.print(q[i]+" ");
			sum=sum+Math.abs(q[i]-prev);
			prev=q[i];
		}
		System.out.println("\n"+"Seak Time: "+sum);
	}
	static void sstf(int q[],int n,int ini)
	{
		int i,loc=-1,sum=0,prev=ini,d=0,least=-1;
		int done[] =new int[n];
		System.out.print("Path: ");
		while(d!=n)
		{
			d++;
			least=100000;
			for(i=0;i<n;i++)
				if(done[i]==0 && Math.abs(q[i]-prev)<least)
				{
					least=Math.abs(q[i]-prev);
					loc=i;
				}
			System.out.print(q[loc]+" ");
			sum=sum+Math.abs(q[loc]-prev);
			prev=q[loc];
			done[loc]=1;
		}
		System.out.println("\n"+"Seak Time: "+sum);
	}
	static void scan(int q[],int n,int ini,int num)
	{
		int i,j,temp,prev=ini,sum=0;
		sort(q,n);
		System.out.print("Path: ");
		for(i=0;i<n;i++)
			if(q[i]>ini)
			{
				System.out.print(q[i]+" ");
				sum=sum+Math.abs(q[i]-prev);
				prev=q[i];
			}
		System.out.print(num+" ");
		sum=sum+Math.abs(num-prev);
		prev=num;
		for(i=n-1;i>=0;i--)
			if(q[i]<ini)
			{
				System.out.print(q[i]+" ");
				sum=sum+Math.abs(q[i]-prev);
				prev=q[i];
			}
		System.out.println("\n"+"Seak Time: "+sum);
	}
	static void look(int q[],int n,int ini)
	{
		int i,j,temp,prev=ini,sum=0;
		sort(q,n);
		System.out.print("Path: ");
		for(i=0;i<n;i++)
			if(q[i]>ini)
			{
				System.out.print(q[i]+" ");
				sum=sum+Math.abs(q[i]-prev);
				prev=q[i];
			}
		for(i=n-1;i>=0;i--)
			if(q[i]<ini)
			{
				System.out.print(q[i]+" ");
				sum=sum+Math.abs(q[i]-prev);
				prev=q[i];
			}
		System.out.println("\n"+"Seak Time: "+sum);
	}
	public static void main(String ar[])
	{
		int ini,n,nod;
		int queue[]=new int[50];
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter number of disks: ");
		nod=sc.nextInt();
		System.out.print("Enter initial header: ");
		ini=sc.nextInt();
		System.out.print("Enter number of requests in Requested Track: ");
		n=sc.nextInt();
		System.out.print("Requested Track: ");
		for(int i=0;i<n;i++)
			queue[i]=sc.nextInt();
		System.out.println("\nFCFS: ");
		fcfs(queue,n,ini);
		System.out.println("\nSSTF: ");
		sstf(queue,n,ini);
		System.out.println("\nSCAN: ");
		scan(queue,n,ini,nod);
		System.out.print("\nLOOK: ");
		look(queue,n,ini);
	}
}
