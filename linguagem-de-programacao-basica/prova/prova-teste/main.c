#include <stdio.h>
#include <stdlib.h>

int strcmp(char s1[], char s2[])
{
    int i = 0;
    while(s1[i] != 0 || s2[i] != 0)
    {
        if(s1[i] == 0 && s2[i] != 0)
        {
            return -1;
        }
        if(s1[i] != 0 && s2[i] == 0)
        {
            return 1;
        }
        if(s1[i] > s2[i])
        {
            return 1;
        }
        if(s1[i] < s2[i])
        {
            return -1;
        }
        i++;
    }
    return 0;
}

/*int main()
{
    char s1[] = "abcdd";
    char s2[] = "abcd";
    int teste;
    teste = strcmp(s1, s2);
    printf("%d", teste);
    return 0;
    float *mat = malloc(50 * 50 * sizeof *mat);
    for(int i = 0; i< 50; i++){
        for(int j = 0; j< 50; j++){
            mat[i * 50 + j]= rand()/100;
            if(i == 0){
                printf("i %d = %d\n",i, mat[i * 50 + j]);
            }
        }
    }

    int media[50];
    int diag = 0;
    for(int i = 0; i< 50; i++){
        int somaLinha = 0;
        for(int j = 0; j< 50; j++){
            if(i==j){
                diag = diag + mat[i * 50 + j];
            }
            somaLinha = somaLinha + mat[i * 50 + j];
        }
        media[i] = somaLinha/50;
    }

    for(int i = 0; i< 50; i++){
        printf("i %d = %d\n",i,  media[i]);
    }

}
*/



float quadrado(const void *x)
{
    int *x1 = (int *) x;
    return *x1 * *x1;
}

void calcula(int minX, int maxX, float x[], float y[], int maxArraysSize, float(*fptr)(const void *))
{
    for(int i=0; i<maxArraysSize; i++){
        x[i] = i;
        y[i] = fptr((void *)(i));
    }
}

int main()
{
    float x[100], y[100];
    int i;

    calcula(0, 10, x, y, 100, quadrado);
    for(i=0; i<100; i++)
        printf("%f,%f\n", x[i], y[i]);
}



