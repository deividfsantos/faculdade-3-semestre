#include <stdio.h>
#include <stdlib.h>

int main()
{
//    int vet[] = {4,9,12};
//    int i, *ptr;
//    ptr = vet;
//    for (i = 0; i<3; i++)
//        printf("%d ", *ptr++); // vs print("%d", (*ptr)++)

    int nums[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 245 };
    int* ptr = nums;
    int i, bytes;
    char* ptr2 = (char*) ptr;

    for(i=0, bytes=0; i<40; i++, bytes+=1){
         printf("%d: %p + %d bytes = %p ==> %d\n",
            i, ptr, bytes, (ptr2+i), *(ptr2+i));
    }
    return 0;
}
