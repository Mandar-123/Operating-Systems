#include<stdio.h>
typedef struct job
{
    int no,at,bt,ct,tt,wt;
}job;
void sort(job a[],int n)
{
    int i,j;
    job t;
    for(i=0;i<n-1;i++)
        for(j=0;j<n-1;j++)
            if(a[j].at>a[j+1].at)
            {
                t=a[j];
                a[j]=a[j+1];
                a[j+1]=t;
            }
}

void main()
{
    int i=0,n,time=0;
    float avgtt=0,avgwt=0;
    job a[50];
    printf("Enter number of processes: ");
    scanf("%d",&n);
    for(i=0;i<n;i++)
    {
        printf("Enter Process no., Arrival time, Burst time: ");
        scanf("%d%d%d",&a[i].no,&a[i].at,&a[i].bt);
    }
    sort(a,n);
    i=0;
    while(i<n)
    {
        if(a[i].at<=time)
        {
            a[i].ct=a[i].bt+time;
            a[i].tt=a[i].ct-a[i].at;
            a[i].wt=a[i].tt-a[i].bt;
            time=time+a[i].bt;
            i++;
        }
        else
            time++;
    }
    printf("\nProcess  Arrival-time  Burst-time  Completion-time  Turnaround-time  Waiting-time");
    for(i=0;i<n;i++)
    {
        avgtt=avgtt+(float)a[i].tt/n;
        avgwt=avgwt+(float)a[i].wt/n;
        printf("\n%7d  %9d  %9d  %13d  %13d  %13d",a[i].no,a[i].at,a[i].bt,a[i].ct,a[i].tt,a[i].wt);
    }
    printf("\nAverage Turnaround time: %.2f",avgtt);
    printf("\nAverage Waiting time   : %.2f",avgwt);
}