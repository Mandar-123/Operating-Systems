import java.util.*;

class banker
{ 
	static void displayarray(int arr[],int n)
	{
		for(int i=0;i<n;i++)
			System.out.print(arr[i]+" ");
		System.out.print("\n\n");
	}
	static void displaymatrix(int mat[][],int r,int c)
	{
		for(int i=0;i<r;i++)
		{
			for(int j=0;j<c;j++)
				System.out.print(mat[i][j]+" ");
			System.out.print("\n");
		}
	}
	public static void main(String ar[])
	{
		Scanner sc=new Scanner(System.in);
		int i,j,p,r,completed=0,passes=0,safe=1;
		System.out.print("Enter No. of processes: ");
		p=sc.nextInt();
		System.out.print("Enter No. of resources: ");
		r=sc.nextInt();
		int allocation[][]=new int[p][r];
		int max[][]=new int[p][r];
		int available[]=new int[r];
		int need[][]=new int[p][r];
		int exec[]=new int[p];
		System.out.println("\nEnter Allocation Matrix:\n");
		for(i=0;i<p;i++)
		{
			for(j=0;j<r;j++)
				allocation[i][j]=sc.nextInt();
			exec[i]=0;
		}
		System.out.println("\nEnter Max Matrix:\n");
		for(i=0;i<p;i++)
			for(j=0;j<r;j++)
				max[i][j]=sc.nextInt();
		System.out.println("\nEnter Available Vector:\n");
		for(i=0;i<r;i++)
			available[i]=sc.nextInt();
		for(i=0;i<p;i++)
			for(j=0;j<r;j++)
				need[i][j]=max[i][j]-allocation[i][j];
		System.out.println("\nNEED MATRIX:\n");
		displaymatrix(need,p,r);
		i=0;
		while(completed!=p && safe==1)
		{
			if(passes==p)
				safe=0;
			if(exec[i]==1)
			{
				i=(i+1)%p;
				passes++;
				continue;
			}
			for(j=0;j<r;j++)
				if(available[j]-(need[i][j])<0)
					break;
			if(j!=r)
			{
				passes++;
				System.out.println("\nProcess "+i+" Wait");
				System.out.println("Available Vector: ");
				displayarray(available,r);
				i=(i+1)%p;
				continue;
			}
			System.out.println("\nProcess "+i+" COMPLETED, All Resources Released");
			for(j=0;j<r;j++)
				available[j]+=allocation[i][j];
			System.out.println("Available Vector: ");
			displayarray(available,r);
			exec[i]=1;
			completed++;
			i=(i+1)%p;
			passes=0;
		}
		if(safe==1)
			System.out.println("All processes completed. The system is in SAFE state.");
		else 
		{
			System.out.print("Follwing Process did not finish: ");
			for(i=0;i<p;i++)
				if(exec[i]==0)
					System.out.print(i+" ");
			System.out.println("\nThe system is in UNSAFE state.");
		}
	}
}