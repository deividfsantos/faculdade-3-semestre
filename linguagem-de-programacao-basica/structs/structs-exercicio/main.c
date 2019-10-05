#include <stdio.h>
#include <stdlib.h>

typedef struct {
    int num;
    int denom;
} Fracao;

Fracao multiplicacao(Fracao fracao1, Fracao fracao2){
    int num = fracao1.num * fracao2.num;
    int denom = fracao1.denom * fracao2.denom;
    int mdcf = mdc(num, denom);
    int numResumido = num / mdcf;
    int denomResumido = denom / mdcf;
    Fracao result = {numResumido, denomResumido};
    return result;
}

Fracao divisao(Fracao fracao1, Fracao fracao2){
    int num = fracao1.num * fracao2.denom;
    int denom = fracao1.denom * fracao2.num;
    int mdcf = mdc(num, denom);
    int numResumido = num / mdcf;
    int denomResumido = denom / mdcf;
    Fracao result = {numResumido, denomResumido};
    return result;
}

Fracao soma(Fracao fracao1, Fracao fracao2){
    int denom = fracao1.denom * fracao2.denom;
    int num1 = denom / fracao1.denom * fracao1.num;
    int num2 = denom / fracao2.denom * fracao2.num;
    int num = num1 + num2;
    int mdcf = mdc(num, denom);
    int numResumido = num / mdcf;
    int denomResumido = denom / mdcf;
    Fracao result = {numResumido, denomResumido};
    return result;
}

Fracao subtracao(Fracao fracao1, Fracao fracao2){
    int denom = fracao1.denom * fracao2.denom;
    int num1 = denom / fracao1.denom * fracao1.num;
    int num2 = denom / fracao2.denom * fracao2.num;
    int num = num1 - num2;
    int mdcf = mdc(num, denom);
    int numResumido = num / mdcf;
    int denomResumido = denom / mdcf;
    Fracao result = {numResumido, denomResumido};
    return result;
}

int mdc(int a, int b){
    if (b == 0) {
      return a;
    } else {
      return mdc(b, a % b);
    }
}

Fracao convert(float val) {
    return
}

int main()
{
    Fracao f1 = {4, 3};
    Fracao f2 = {5, 2};
    Fracao resul  = multiplicacao(f1, f2);
    printf("%d/%d", resul.num, resul.denom);
    return 0;
}
