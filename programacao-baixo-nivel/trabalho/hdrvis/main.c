#include <math.h>
#include <string.h>		// para usar strings

// Rotinas para acesso da OpenGL
#include "opengl.h"

// Protótipos
void process();
void carregaHeader(FILE* fp);
void carregaImagem(FILE* fp, int largura, int altura);
void criaImagensTeste();

//
// Variáveis globais a serem utilizadas (NÃO ALTERAR!)
//

// Dimensões da imagem de entrada
int sizeX, sizeY;

// Header da imagem de entrada
unsigned char header[11];

// Pixels da imagem de ENTRADA (em formato RGBE)
unsigned char* image;

// Pixels da imagem de SAÍDA (em formato RGB)
unsigned char* image8;

// Fator de exposição inicial
float exposure = 1.0;

// Função principal de processamento: ela deve chamar outras funções
// quando for necessário (ex: algoritmo de tone mapping, etc)
void process()
{
    printf("Exposure: %.3f\n", exposure);
    //
    // EXEMPLO: preenche a imagem com pixels cor de laranja...
    //
    //
    // SUBSTITUA este código pelos algoritmos a serem implementados
    //
    unsigned char* ptr = image8;
    int totalBytes = sizeX * sizeY * 3; // RGB = 3 bytes por pixel
    for(int pos=0; pos<totalBytes; pos+=3) {
        // Gera tons de LARANJA, de acordo com o fator de exposição
        *ptr++ = (unsigned char) (255 * exposure);
        *ptr++ = (unsigned char) (127 * exposure);
        *ptr++ = (unsigned char) (0 * exposure);
    }

    //
    // NÃO ALTERAR A PARTIR DAQUI!!!!
    //
    buildTex();
}

int main(int argc, char** argv)
{
    if(argc==1) {
        printf("hdrvis [image file.hdf]\n");
        exit(1);
    }

    // Inicialização da janela gráfica
    init(argc,argv);

    //
    // PASSO 1: Leitura da imagem
    // A leitura do header já foi feita abaixo
    //
    FILE* arq = fopen("apartment.hdf", "rb");
    carregaHeader(arq);

    //
    // IMPLEMENTE AQUI o código necessário para
    // extrair as informações de largura e altura do header

    int altura = header[4]+header[5]+header[6]+header[7];
    int largura = header[8]+header[9]+header[10]+header[11];

    printf("\n\n%d\n", altura);
    printf("\n%d\n\n", largura);

    // Descomente a linha abaixo APENAS quando isso estiver funcionando!
    //
    //carregaImagem(arq, minhaLargura, minhaAltura);

    // Fecha o arquivo
    fclose(arq);

    //
    // COMENTE a linha abaixo quando a leitura estiver funcionando!
    // (caso contrário, ele irá sobrepor a imagem carrega com a imagem de teste)
    //
    criaImagensTeste();

    exposure = 1.0f; // exposição inicial

    // Aplica processamento inicial
    process();

    // Não retorna... a partir daqui, interação via teclado e mouse apenas, na janela gráfica

    // Mouse wheel é usada para aproximar/afastar
    // Setas direita e esquerda ajustam o fator de exposição

    glutMainLoop();

    return 0;
}

// Função apenas para a criação de uma imagem em memória, com o objetivo
// de testar a funcionalidade de exibição e controle de exposição do programa
void criaImagensTeste()
{
    // TESTE: cria uma imagem de 800x600
    sizeX = 800;
    sizeY = 600;

    printf("%d x %d\n", sizeX, sizeY);

    // Aloca imagem de entrada (32 bits RGBE)
    image = (unsigned char*) malloc(sizeof(unsigned char) * sizeX * sizeY * 4);

    // Aloca memória para imagem de saída (24 bits RGB)
    image8 = (unsigned char*) malloc(sizeof(unsigned char) * sizeX * sizeY * 3);
}

// Esta função deverá ser utilizada para ler o conteúdo do header
// para a variável header (depois você precisa extrair a largura e altura da imagem desse vetor)
void carregaHeader(FILE* fp)
{
    // Lê 11 bytes do início do arquivo
    fread(header, 11, 1, fp);
    // Exibe os 3 primeiros caracteres, para verificar se a leitura ocorreu corretamente
    printf("Id: %c%c%c\n", header[0], header[1], header[2]);
}

// Esta função deverá ser utilizada para carregar o restante
// da imagem (após ler o header e extrair a largura e altura corretamente)
void carregaImagem(FILE* fp, int largura, int altura)
{
    sizeX = largura;
    sizeY = altura;

    // Aloca imagem de entrada (32 bits RGBE)
    image = (unsigned char*) malloc(sizeof(unsigned char) * sizeX * sizeY * 4);

    // Aloca memória para imagem de saída (24 bits RGB)
    image8 = (unsigned char*) malloc(sizeof(unsigned char) * sizeX * sizeY * 3);

    // Lê o restante da imagem de entrada
    fread(image, sizeX * sizeY * 4, 1, fp);
    // Exibe primeiros 3 pixels, para verificação
    for(int i=0; i<12; i+=4) {
        printf("%02X %02X %02X %02X\n", image[i], image[i+1], image[i+2], image[i+3]);
    }
}

