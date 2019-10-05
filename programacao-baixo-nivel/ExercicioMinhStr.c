#include <stdio.h>
#include <string.h>

char* minhaStrstr(char* str1, char* str2){
    while(*str1++){
        if((*str1 == *str2)){
            return str1;
        }
    }
    return NULL;
}

int main(void)
{
    char *teste1 = "Alo mundo";
    char *teste2 = "lo";
    printf("%s\n", minhaStrstr(teste1, teste2));
    return 0;
}

