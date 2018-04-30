#include<stdio.h>
typedef struct job
{
    int no,at,bt,ct,tt,wt,flag;
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
    int i,n,j,time=0,done=0,best;
    float avgtt=0,avgwt=0;
    job a[50];
    printf("Enter number of processes: ");
    scanf("%d",&n);
    for(i=0;i<n;i++)
    {
        printf("Enter Process no., Arrival time, Burst time: ");
        scanf("%d%d%d",&a[i].no,&a[i].at,&a[i].bt);
        a[i].flag=0;
    }
    sort(a,n);
    while(done!=n)
    {
        best=1000;
        j=-1;
        i=0;
        while(a[i].at<=time && i<n)
        {
            if(a[i].flag==0 && a[i].bt<best)
            {
                best=a[i].bt;
                j=i;
            }
            i++;
        }
        if(j==-1)
            time++;
        else
        {
            a[j].flag=1;
            done++;
            a[j].ct=a[j].bt+time;
            a[j].tt=a[j].ct-a[j].at;
            a[j].wt=a[j].tt-a[j].bt;
            time=time+a[j].bt;
        }
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