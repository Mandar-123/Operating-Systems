import java.util.*;
class PageReplacement
{
	static void print(LinkedList<Integer> ll)
	{
		System.out.print("Current Frame list:  ");
		for(int i=0;i<ll.size();i++)
			System.out.print(ll.get(i)+" ");
		System.out.print("\n");
	}
	static void lru(int no,int a[],int num)
	{
		int i,k;
		float hit=0;
		Scanner sc=new Scanner(System.in);
		LinkedList<Integer> llcopy=new LinkedList<Integer>();
		LinkedList<Integer> ll = new LinkedList<Integer>();
		for(k=0;k<num;k++)
		{
			System.out.println("\nCurrent Page: "+a[k]); 
			for(i=0;i<ll.size();i++)
			{
				if(a[k] == ll.get(i))
					break;
			}
			if(i<ll.size())
			{
				ll.remove(ll.indexOf(a[k]));
				System.out.println("Page HIT !!");
				ll.addLast(a[k]);
				hit++;
			}
			else
			{
				if(ll.size()<no)
				{
					ll.addLast(a[k]);
					llcopy.addLast(a[k]);
					System.out.println("Page Fault, Page added");
				}
				else
				{
					int temp=llcopy.indexOf(ll.get(0));
					llcopy.remove(temp);
					ll.remove(0);
					ll.addLast(a[k]);
					llcopy.add(temp,a[k]);
					System.out.println("Page Fault, Page replaced");
				}
			}
			print(llcopy); 
			System.out.print("\n");
		} 
		System.out.println("\nHit Ratio = "+hit/num+"\n\n");
	}

	static void fifo(int no,int a[],int num)
	{
		int i,rem=0,k;
		float hit=0;
		Scanner sc=new Scanner(System.in);
		LinkedList<Integer> ll = new LinkedList<Integer>();
		for(k=0;k<num;k++)
		{
			System.out.println("\nCurrent Page: "+a[k]); 
			for(i=0;i<ll.size();i++)
			{
				if(a[k] == ll.get(i))
					break;
			}
			if(i<ll.size())
			{
				System.out.println("Page HIT!!");
				hit++;
			}
			else
			{
				if(ll.size()<no)
				{
					ll.addLast(a[k]);
					System.out.println("Page Fault, Page added");
				}
				else
				{
					ll.remove(rem);
					ll.add(rem,a[k]);
					System.out.println("Page Fault, Page replaced");
					rem=(rem+1)%no;
				}
			}
			print(ll);
			System.out.print("\n");
		} 
		System.out.println("\nHit Ratio = "+hit/num+"\n\n");
	}

	static void opt(int no,int a[],int num)
	{
		int i,j,k,m,max,rep=0;
		float hit=0;
		Scanner sc=new Scanner(System.in);
		LinkedList<Integer> ll = new LinkedList<Integer>();
		for(i=0;i<num;i++)
		{
			System.out.println("\nCurrent Page: "+a[i]); 
			for(m=0;m<ll.size();m++)
			{
				if(a[i] == ll.get(m))
					break;
			}
			if(m<ll.size())
			{
				System.out.println("Page HIT !!");
				print(ll);
				hit++;
			}
			else if(ll.size()<no)
			{
				ll.addLast(a[i]);
				System.out.println("Page Fault, Page added");
				print(ll);
			}
			else
			{
				max=0;
				l1:for(k=0;k<no;k++)
				{
					for(j=i+1;j<num;j++)
					{
						if(ll.get(k)==a[j])
						{
							if(j>max)
							{
								rep=k;
								max=j;
							}
							break;
						}
						if(j==num-1)
						{
							rep=k;
							break l1;
						}
					}
				}
				ll.remove(rep);
				ll.add(rep,a[i]);
				System.out.println("Page Fault, Page replaced");
				print(ll);
			}
		}
		System.out.println("\nHit Ratio = "+hit/num+"\n\n");
	}
	public static void main(String ar[])
	{
		Scanner sc=new Scanner(System.in);
		int no,num,i;
		int a[]=new int[50];
		System.out.print("Enter number of frames: ");
		no=sc.nextInt();		
		System.out.print("Enter number of pages in sequence: ");
		num=sc.nextInt();
		System.out.print("Enter sequence of pages: ");
		for(i=0;i<num;i++)	
			a[i]=sc.nextInt();
		System.out.println("\n**********************FIFO***************************");
		fifo(no,a,num);
		System.out.println("************************LRU****************************");
		lru(no,a,num);
		System.out.println("**********************OPTIMAL**************************");
		opt(no,a,num);
	}
}