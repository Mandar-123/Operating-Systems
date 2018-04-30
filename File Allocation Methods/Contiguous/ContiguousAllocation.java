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
		System.out.println("Name: "+name+"\n"+"Start: "+startIndex+"\n"+"Length: "+(endIndex-startIndex+1)+"\n");
	}
}
class ContiguousAllocation
{
	
	static void contiguous(int tb)
	{
		Scanner sc=new Scanner(System.in);
		String name;
		int i,cont=1,ch=-1,nob,free=0,nof=0,pos;
		int memory[] =new int[tb];
		LinkedList<file> directory=new LinkedList<file>();
		while(cont!=0)
		{
			pos=-1;
			System.out.print("Enter Choice 1.Create File   2.Delete File  3.View Directory: ");
			ch=sc.nextInt();
			switch(ch)
			{
				case 1:	free=0;
						System.out.print("Enter File of name: ");
						name=sc.next();
						System.out.print("Enter number of blocks needed:");
						nob=sc.nextInt();
						for(i=0;i<tb;i++)
						{
							if(memory[i]==0)
							{
								free++;
								if(free==nob)
									break;
							}
							else
							{	
								free=0;
								pos=i;
							}
						}
						if(free==nob)
						{
							file temp=new file(name,pos+1,pos+nob);
							directory.addLast(temp);
							for(i=pos+1;i<=pos+nob;i++)
								memory[i]=1;
							nof++;
							System.out.println("File Allocated\n");
						}
						else
							System.out.println("Not Enough Space\n");
						break;
				
				case 2: System.out.println("Enter name of file to delete: ");
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
						pos=i;
						for(i=directory.get(pos).startIndex;i<=directory.get(pos).endIndex;i++)
							memory[i]=0;
						directory.remove(directory.get(pos));
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
		int blocks;
		Scanner sc=new Scanner(System.in);
		System.out.println("****Contiguous File Allocation*****");
		System.out.print("Enter number of memory blocks: ");
		blocks=sc.nextInt();
		contiguous(blocks);
	}
}