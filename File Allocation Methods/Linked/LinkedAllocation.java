import java.util.*;
class file
{
	String name;
	int startIndex;
	int endIndex;
	file(String n,int s,int e)
	{
		name=n;
		startIndex=s;
		endIndex=e;
	}
	void display()
	{
		System.out.println("Name: "+name+"\n"+"Start: "+startIndex+"\n"+"End: "+endIndex+"\n");
	}
}
class LinkedAllocation
{
	static void linked(int tb)
	{
		Scanner sc=new Scanner(System.in);
		String name;
		int i,cont=1,ch=-1,nob,free=0,nof=0,pos,start=0,next;
		int memory[] =new int[tb];
		LinkedList<file> directory=new LinkedList<file>();
		while(cont!=0)
		{
			System.out.print("Enter Choice 1.Create File   2.Delete File  3.View Directory: ");
			ch=sc.nextInt();
			switch(ch)
			{
				case 1:
						pos=-1;
						free=0;
						System.out.print("Enter File of name: ");
						name=sc.next();
						System.out.print("Enter number of blocks needed:");
						nob=sc.nextInt();
						for(i=0;i<tb;i++)
						{
							if(memory[i]==0)
								free++;
							if(free==1)
								start=pos=i;
						}
						if(free>=nob)
						{
							System.out.print("Path: "+pos+" ");
							for(i=pos+1;i<tb;i++)
							{
								if(memory[i]==0 && nob!=1)
								{
									memory[pos]=i;
									nob--;
									pos=i;
									System.out.print(pos+" ");
								}
							}
							
							memory[pos]=-1;
							nof++;
							file temp=new file(name,start,pos);
							directory.addLast(temp);
							System.out.println("\nFile Allocated\n");
						}
						else
							System.out.println("Not Enough Space\n");
						break;
				
				case 2: System.out.print("Enter name of file to delete: ");
						name=sc.next();
						for(i=0;i<nof;i++)
						{
							if(directory.get(i).name.equals(name))
								break;
						}
						if(i==nof)
						{
							System.out.println("File not Found\n");
							break;
						}
						nof--;
						pos=directory.get(i).startIndex;
						directory.remove(directory.get(i));
						while(true)
						{
							next=memory[pos];
							memory[pos]=0;
							if(next==-1)
								break;
							else pos=next;
						}
						System.out.println("File Deleted\n");
						break;
				case 3: System.out.println("\n***Directory***\n");
						for(i=0;i<nof;i++)
							directory.get(i).display();
			}
		}
	}
	public static void main(String ar[])
	{
		int technique,blocks;
		Scanner sc=new Scanner(System.in);
		System.out.println("****Linked File Allocation*****");
		System.out.print("Enter number of memory blocks: ");
		blocks=sc.nextInt();
		linked(blocks);
	}
}