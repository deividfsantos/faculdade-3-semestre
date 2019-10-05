#include <stdio.h>
#include <stdlib.h>

//k=k+N/k
//    2

float calcularValorRaiz(float n, float k){
    return (k + n/k)/2;
}

int main()
{
    float resultado = 1;
    for(float i = 1; i < 12; i++){
        resultado = calcularValorRaiz(16, resultado);
        printf("%f\n", resultado);
    }
}
