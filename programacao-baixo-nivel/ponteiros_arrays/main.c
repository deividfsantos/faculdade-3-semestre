#include <stdio.h>
#include <stdlib.h>

void incrementa(int vet[]);
void exibe(int vet[]);
void exibe_pont(int vet[]);

// Incrementa um vetor (passado por referencia)
void incrementa(int vet[])
{
    for(int i=0; i<10; i++)
        vet[i]++;
}

// Exibe um vetor de 10 posicoes
void exibe(int vet[])
{
    for(int i=0; i<10; i++)
        printf("%d ",vet[i]);
    printf("\n");
}

// Exibe um vetor de 10 posicoes, usando aritmetica de ponteiros
void exibe_pont(int vet[])
{
    // ptr armazena o end. inicial, equivalente a "vet"
    int* ptr = vet; // end. inicial
    for(int i=0; i<10; i++) {
        printf("%d ",*ptr); // *ptr acessa conteudo apontado por ptr
        // ptr++ avanca ptr para a proxima posicao (no caso, 4 bytes para a frente)
        ptr++;
    }
}

int main()
{
    int dados[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    printf("Tam. int: %d\n", sizeof(int));
    printf("Inicio de dados: %p\n",dados);
    // &dados[0] e' a mesma coisa que dados, ou seja, o end. inicial
    printf("Inicio de dados: %p\n",&dados[0]);
    printf("Segundo elem.  : %p\n",&dados[1]);
    // (dados+1) e' a mesma coisa que &dados[1], ou seja, o end. inicial + o espaco ocupado por um int (4 bytes)
    printf("Segundo elem.  : %p\n",(dados+1));
    printf("Terceiro elem. : %p\n",&dados[2]);
    printf("Terceiro elem. : %p\n",(dados+2));
    printf("\nAcessando:\n");
    printf("dados[0]: %d\n", dados[0]);
    printf("dados[0]: %d\n", dados[0]);
    // *dados e' a mesma coisa que dados[0]
    printf("dados[0]: %d\n", *dados); // *(dados+0)
    printf("dados[1]: %d\n", dados[1]);
    // *(dados+1) e' a mesma coisa que dados[1]...
    printf("dados[1]: %d\n", *(dados+1));
    exibe(dados);
    incrementa(dados);
    exibe(dados);
    exibe_pont(dados);
    return 0;
}
