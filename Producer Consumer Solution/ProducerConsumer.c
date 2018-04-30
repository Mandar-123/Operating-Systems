#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <semaphore.h>
#define SIZE 5

pthread_mutex_t mutex;
sem_t full;
sem_t empty;

int q[SIZE],f=0,r=-1,count=0;

void insert(int ele)
{
	r=(r+1)%SIZE;
    q[r]=ele;
    count++;
}

int delete1()
{
	int z=q[f];
	f=(f+1)%SIZE;
	count--;
	return z;
}

void display()
{
    int i=f;
    if(count==0)
    {
        printf("Empty\n");
        return;
    }
    while(1)
    {
        printf("%d ",q[i]);
        if(i==r)
            break;
        i=(i+1)%SIZE;
    }
    printf("\n");
}

void *producer(void *t_no)
{
    int ele,no=*(int *)t_no;
    while (1)
    {
        sem_wait(&empty);
        pthread_mutex_lock(&mutex);
        ele=rand()%100;
        insert(ele);
        printf("Producer %2d enqueued %2d          Queue:  ",no,ele);
        display();
        pthread_mutex_unlock(&mutex);
        sem_post(&full);
    }
    pthread_exit(0);
}

void *consumer(void *t_no)
{
    int ele,no=*(int *)t_no;
    while (1)
    {
        sem_wait(&full);
        pthread_mutex_lock(&mutex);
        ele=delete1();
        printf("Consumer %2d dequeued %2d          Queue:  ",no,ele);
        display();
        pthread_mutex_unlock(&mutex);
        sem_post(&empty);
    }
    pthread_exit(0);
}

void main()
{
    int i,pro,con,t_no[100];
    pthread_mutex_init(&mutex, NULL);
    sem_init(&empty,0,SIZE);
    sem_init(&full,0,0);
    pthread_t threadp[100],threadc[100];
    printf("Enter number of producers: ");
    scanf("%d",&pro);
    printf("Enter number of consumers: ");
    scanf("%d",&con);
    for(i=0;i<pro;i++)
    {
        t_no[i]=i+1;
        pthread_create(&threadp[i], NULL, producer,&t_no[i]);
    }
    for(i=0;i<con;i++)
    {
        t_no[i]=i+1;
        pthread_create(&threadc[i], NULL, consumer,&t_no[i]);
    }
    for(i=0;i<pro;i++)
        pthread_join(threadp[i], NULL);
    for(i=0;i<con;i++)
        pthread_join(threadc[i], NULL);
    pthread_mutex_destroy(&mutex);
    sem_destroy(&full);
    sem_destroy(&empty);
}