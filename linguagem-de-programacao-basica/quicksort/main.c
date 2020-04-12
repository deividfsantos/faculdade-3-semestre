#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int compare (void * a, void * b) {
  return *(int*)a - *(int*)b;
}

// Comparacao de doubles: cuidado ao comparar,
// uma vez que nao se deve simplesmente subtrair
// os dois valores
int compara_double(void* a, void* b) {
    double x = *(double*)a;
    double y = *(double*)b;
    if (x < y)
        return -1;
    else if (x > y)
        return 1;
    return 0;
    //return (int) (*(double*)a - *(double*)b);
}

// Comparacao de strings e' trivial: basta  usar
// a funcao strcmp, repassando os ponteiros
int compara_str(void* a, void* b) {
    return strcmp((char*)a, (char*)b);
}

// Exemplo de ponteiro para funcao sem classes: quicksort
int main()
{
   double values[1000000];
   srand(time(0));
   int i;
   for(i=0; i<1000000; i++)
       values[i] = rand() % 1000000;

   ///exemplo do quicksort em C
   qsort (values, 1000000, sizeof(double), compara_double); // compare é um ponteiro para funcao

   printf("Usando o quicksort (e passagem de funcoes por parametro)\n");

   // Descomente as linhas abaixo para ver o resultado (demora)
   //for (i=0; i<1000000; i++)
   //   printf("%lf ", values[i]);
   //printf("\n");

   // Ordenando um vetor de strings
   char nomes[5][20] = {
    "Ana",
    "Jose",
    "Pedro",
    "Carlos",
    "Maria"
   };

   // Exemplo: testando a função compara_str
   printf("%d\n",compara_str(nomes[0],nomes[1]));
   // Observe que e' preciso informar o tamanho MAXIMO de cada
   // string ou nao funcionara...
   qsort(nomes, 5, 20, compara_str);

   for(i=0; i<5; i++)
    printf("%p: %s\n", nomes[i], nomes[i]);
   return 0;
}

