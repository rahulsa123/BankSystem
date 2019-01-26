n, k = 20, 4
l=[]
for  i in range(n):
    l.append([int(x) for x in input().split()])
res=0
for i in range(n):
    for j in range(n):
        #top
        if(i>=k-1):
            s=0
            for r in range(k):
                s+=l[i+r][j]
            if(res <s):
                res = s
        #botton
        if(i<=n-k):
            s=0
            for r in range(k):
                s+=l[i-r][j]
            if(res <s):
                res = s
        #left
        if(j>=k-1):
            s=0
            for r in range(k):
            	print(j+r)
                s+=l[i][j+r]
            if(res <s):
                res = s
        #right
        if(j<=n-k):
            s=0
            for r in range(k):
                s+=l[i][j-r]
            if(res <s):
                res = s

        #left diagonal
        if(i<=n-k and j<=n-k):
            s=0
            for r in range(k):
                s+=l[i+r][j+r]
            if(res <s):
                res = s
        #right diagonal
        if(i>=k-1 and j>=k-1):
            s=0
            for r in range(k):
                s+=l[i+r][j-r]
            if(res <s):
                res = s
print(res)
