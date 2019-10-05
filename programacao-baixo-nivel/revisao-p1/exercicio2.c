#include <stdio.h>
#include <stdlib.h>

// esta função deve zerar o valor
// da variável passada por parâmetro
void Zera(int *X)
{
	*X = 0; 
}

int main() 
{  
	int A = 3;
	printf("Numero de bytes do A: %d\n", A );
	printf("Endereco do A: %p\n", &A);
	Zera (&A);
	printf("Valor de A: %d\n", A);
    printf("Endereco do A: %p\n", &A);
	return 0;
}