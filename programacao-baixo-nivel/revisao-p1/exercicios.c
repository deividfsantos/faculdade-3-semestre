#include <stdio.h>

int main()
{
	float f;                  
	float *ptrF;                     

	ptrF = &f;                              

	scanf("%f", ptrF); 
	printf("O Valor eh %f", *ptrF);
	return 0;
}
