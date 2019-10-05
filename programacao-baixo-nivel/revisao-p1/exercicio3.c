#include <stdio.h>
int main() {
	int *ptr;
	int vet[5];
	ptr = &vet[1];

	for (int i = 0; i<5; i++) {
        printf("%p ", &vet[i]);
        printf("%p\n", &ptr[i]);
	}

	for (int i = 0; i<3; i++) {
		ptr[i] = i+2;
        printf("\n%d", ptr[i]);
        printf(" %p", &ptr[i]);
	}

    printf("\n");

	ptr--;
	*ptr = 4;
	ptr[vet[0]] = 9;
	for (int i = 0; i<5; i++) {
		printf("%d ", vet[i]);
	}
	return 0;
}


//  VET  _ _ _ _ _
//  PTR    _ _ _ _

//  VET  _ 2 3 4 _
//  PTR    2 3 4 _

//  VET  4 2 3 4 _
//  PTR  4 2 3 4 9




