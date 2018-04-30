#include<stdio.h>
#define Max 50
typedef struct job
{
    int no,at,bt,ct,tt,wt,rt;
}job;
int q[Max],f=0,r=-1;
int search(int n)
{
    int i;
    for(i=f;i<=r;i++)
        if(q[i]==n)
            return 1;
    return 0;
}
void main()
{
    int i,n,time=0,tq,done=0,t,del;
    float avgtt=0,avgwt=0;
    job a[50];
    printf("Enter number of processes: ");
    scanf("%d",&n);
    for(i=0;i<n;i++)
    {
        printf("Enter Process no., Arrival time, Burst time: ");
        scanf("%d%d%d",&a[i].no,&a[i].at,&a[i].bt);
        a[i].rt=a[i].bt;
    }
    printf("Enter time quantum: ");
    scanf("%d",&tq);
    q[++r]=0;
    while(done!=n)
    {
        if(f==r+1)
        {
            time++;
            continue;
        }
        del=q[f++];
        t=a[del].rt<tq?a[del].rt:tq;
        a[del].rt=a[del].rt-t;
        time=time+t;
        for(i=0;i<n;i++)
            if(a[i].at<=time && a[i].rt!=0 && i!=del && search(i)==0)
                    q[++r]=i;
        if(a[del].rt==0)
        {
            a[del].ct=time;
            done++;
            a[del].tt=a[del].ct-a[del].at;
            a[del].wt=a[del].tt-a[del].bt;
        }
        else
            q[++r]=del;
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