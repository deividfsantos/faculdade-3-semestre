#include <stdio.h>
#include <stdlib.h>

int segundosDoDia(int horas, int minutos, int segundos){
    return segundos + minutos * 60 + horas * 60 * 60;
}

int main()
{
    printf("%d", segundosDoDia(24, 10, 0));
    return 0;
}
