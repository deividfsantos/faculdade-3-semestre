#include <stdio.h>

void troco(int valor, int* n100, int* n50, int* n10, int* n5, int* n1){
    int resto100 = valor % 100;
    *n100 = valor / 100;

    int resto50 = resto100 % 50;
    *n50 = resto100 / 50;

    *n10 = resto50 / 10;
    int resto10 = resto50 % 100;

    *n5 = resto10 / 5;
    int resto5 = resto10 % 100;

    *n1 = resto5 % 5;
}

int main()
{
    int n100 = 0;
    int n50 = 0;
    int n10 = 0;
    int n5 = 0;
    int n1 = 0;
    troco(487, &n100, &n50, &n10, &n5, &n1);
    printf("%d %d %d %d %d", n100, n50, n10, n5, n1);
    return 0;
}
