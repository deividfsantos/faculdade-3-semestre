#include <stdio.h>
#include <string.h>

int tem_substring(char str1[], char str2[]) {
    int cont =  0;

    char first = str2[0];
    for (int i = 0; i < strlen(str1); i++) {
        if(str1[i] == first){
            
        }
    }

    if(strlen(str2) == cont){
        return 1;
    }
    return 0;
}

int main(){
    int tem = tem_substring("abcd", "abd");
    printf("%d", tem);
}
