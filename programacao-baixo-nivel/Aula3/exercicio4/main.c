#include <stdio.h>
#include <stdlib.h>

int segundosFaltantes(int horas, int minutos, int segundos){
    int segundosAteOMomento = segundos + minutos * 60 + horas * 60 * 60;
    return 86400 - segundosAteOMomento;
}

int main()
{
    printf("%d", segundosFaltantes(23, 59, 59));
    return 0;
}
