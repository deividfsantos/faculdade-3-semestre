#include <stdio.h>
#include <stdlib.h>

int main()
{
    float temperaturaFahrenheit;
    printf("Digite a temperatura em Fahrenheit: ");
    scanf("%f", &temperaturaFahrenheit);
    float temperaturaEmCelsius = (temperaturaFahrenheit - 32.0) / 1.8;
    printf("A temperatura em graus celsius é: %f",  temperaturaEmCelsius);
    return 0;
}
