import java.util.*;
import java.io.*;

class problock implements Serializable
{
	int p_id,p_size,b_id,b_start;
	problock(int id,int size)
	{
		p_id=id;
		p_size=size;
		b_start=-1;
		b_id=-1;
	}
}
class memblock implements Serializable
{
	int b_id,b_size,p_id,b_start;
	memblock(int id,int size,int add)
	{
		b_id=id;
		b_size=size;
		b_start=add;
		p_id=-1;
	}
}
class iterate implements Serializable
{
	int no_process,no_memory;
	LinkedList<memblock> ll=null;
	problock[] array=null;
	
	iterate(int no_process,int no_memory,problock[] array,LinkedList<memblock> ll)
	{
		this.no_process=no_process;
		this.no_memory=no_memory;
		this.array=array;
		this.ll=ll;
	}
	
	void firstfit()
	{
		int i,j;
		for(j=0;j<no_process;j++)
			for(i=0;i<ll.size();i++)
			{
				memblock temp1=ll.get(i);
				if(temp1.p_id==-1 && temp1.b_size>=array[j].p_size)
				{
					temp1.p_id=j;
					array[j].b_id=temp1.b_id;
					array[j].b_start=temp1.b_start;
					if(temp1.b_size>array[j].p_size)
					{
						memblock temp2=new memblock(temp1.b_id,temp1.b_size-array[j].p_size,temp1.b_start+array[j].p_size); 
						ll.add(i+1,temp2);
					}
					temp1.b_size=array[j].p_size;
					break;
				}
			}
		System.out.println("\nMethod: First Fit");
		display();
	}
	
	void bestfit()
	{
		int i,j,best,id;
		for(j=0;j<no_process;j++)
		{
			memblock bestblock=null;
			memblock temp1=null;
			best=10000;
			id=-1;
			for(i=0;i<ll.size();i++)
			{
				temp1=ll.get(i);
				if(temp1.p_id==-1 && temp1.b_size>=array[j].p_size && temp1.b_size<best)
				{
					best=temp1.b_size;
					bestblock=temp1;
					id=i;
				}
			}
			if(id==-1)
				continue;
			bestblock.p_id=j;
			array[j].b_id=bestblock.b_id;
			array[j].b_start=bestblock.b_start;
			if(bestblock.b_size>array[j].p_size)
			{
				temp1=new memblock(bestblock.b_id,bestblock.b_size-array[j].p_size,bestblock.b_start+array[j].p_size); 
				ll.add(id+1,temp1);
			}
			bestblock.b_size=array[j].p_size;
		}
		System.out.println("\nMethod: Best Fit");
		display();
	}
	void worstfit()
	{
		int i,j,worst,id;
		for(j=0;j<no_process;j++)
		{
			memblock worstblock=null;
			memblock temp1=null;
			worst=-1;
			id=-1;
			for(i=0;i<ll.size();i++)
			{
				temp1=ll.get(i);
				if(temp1.p_id==-1 && temp1.b_size>=array[j].p_size && temp1.b_size>worst)
				{
					worst=temp1.b_size;
					worstblock=temp1;
					id=i;
				}
			}
			if(id==-1)
				continue;
			worstblock.p_id=j;
			array[j].b_id=worstblock.b_id;
			array[j].b_start=worstblock.b_start;
			if(worstblock.b_size>array[j].p_size)
			{
				temp1=new memblock(worstblock.b_id,worstblock.b_size-array[j].p_size,worstblock.b_start+array[j].p_size); 
				ll.add(id+1,temp1);
			}
			worstblock.b_size=array[j].p_size;
		}
		System.out.println("\nMethod: Worst Fit");
		display();
	}
	void display()
	{
		System.out.println("Process Allocation\n");
		for(int j=0;j<no_process;j++)
		{
			if(array[j].b_id==-1)
				System.out.println("Process "+j+" NOT allocated");
			else
				System.out.println("Process "+j+" has been allocated block "+array[j].b_id+" with starting Hexadecimal address "+Integer.toHexString(array[j].b_start)+"h");
		}
		System.out.println("\n");
	}
}
class MemoryManagement
{
	public static Object deepClone(Object object) 
	{
		try {
		  ByteArrayOutputStream baos = new ByteArrayOutputStream();
		  ObjectOutputStream oos = new ObjectOutputStream(baos);
		  oos.writeObject(object);
		  ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
		  ObjectInputStream ois = new ObjectInputStream(bais);
		  return ois.readObject();
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String ar[])
	{
		String hexadd;
		int i,no_process,no_memory,size;
		LinkedList<memblock> ll=new LinkedList<memblock>();
		problock[] array=new problock[50];
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter no. of memory blocks: ");
		no_memory=sc.nextInt();
		for(i=0;i<no_memory;i++)
		{
			System.out.print("Enter size and starting Hexadecimal address of memory block "+i+":");
			size=sc.nextInt();
			hexadd=sc.next();
			memblock m=new memblock(i,size,Integer.parseInt(hexadd,16));
			ll.add(m);
		}
		System.out.print("Enter no. of process blocks: ");
		no_process=sc.nextInt();
		for(i=0;i<no_process;i++)
		{
			System.out.print("Enter size of process "+i+":");
			size=sc.nextInt();
			array[i]=new problock(i,size);
		}
		iterate obj1=new iterate(no_process,no_memory,array,ll);
		iterate obj2=(iterate)deepClone(obj1);
		iterate obj3=(iterate)deepClone(obj1);
		obj1.firstfit();
		obj2.bestfit();
		obj3.worstfit();
	}
}