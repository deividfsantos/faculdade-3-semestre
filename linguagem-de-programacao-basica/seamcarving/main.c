#include <stdio.h>
#include <stdlib.h>
#include <string.h>        // Para usar strings

#ifdef WIN32
#include <windows.h>    // Apenas para Windows
#endif

#ifdef __APPLE__
#include <OpenGL/gl.h>
#include <OpenGL/glu.h>
#include <GLUT/glut.h>
#else
#include <GL/gl.h>     // Funções da OpenGL
#include <GL/glu.h>    // Funções da GLU
#include <GL/glut.h>   // Funções da FreeGLUT
#endif

// SOIL é a biblioteca para leitura das imagens
#include "SOIL.h"

// Um pixel RGB (24 bits)
typedef struct {
    unsigned char r, g, b;
} RGB;

// Uma imagem RGB
typedef struct {
    int width, height;
    RGB* img;
} Img;

// Protótipos
void load(char* name, Img* pic);
void uploadTexture();

// Funções da interface gráfica e OpenGL
void init();
void draw();
void keyboard(unsigned char key, int x, int y);

// Largura e altura da janela
int width, height;

// Identificadores de textura
GLuint tex[3];

// As 3 imagens
Img pic[3];

// Imagem selecionada (0,1,2)
int sel;

// Carrega uma imagem para a struct Img
void load(char* name, Img* pic)
{
    int chan;
    pic->img = (RGB*) SOIL_load_image(name, &pic->width, &pic->height, &chan, SOIL_LOAD_RGB);
    if(!pic->img)
    {
        printf( "SOIL loading error: '%s'\n", SOIL_last_result() );
        exit(1);
    }
    printf("Load: %d x %d x %d\n", pic->width, pic->height, chan);
}

int main(int argc, char** argv)
{
    if(argc < 2) {
        printf("seamcarving [origem] [mascara]\n");
        printf("Origem é a imagem original, mascara é a máscara desejada\n");
        exit(1);
    }
    glutInit(&argc,argv);

    // Define do modo de operacao da GLUT
    glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB);

    // pic[0] -> imagem original
    // pic[1] -> máscara desejada
    // pic[2] -> resultado do algoritmo

    // Carrega as duas imagens
    load(argv[1], &pic[0]);
    load(argv[2], &pic[1]);

    if(pic[0].width != pic[1].width || pic[0].height != pic[1].height) {
        printf("Imagem e máscara com dimensões diferentes!\n");
        exit(1);
    }

    // A largura e altura da janela são calculadas de acordo com a maior
    // dimensão de cada imagem
    width = pic[0].width;
    height = pic[0].height;

    // A largura e altura da imagem de saída são iguais às da imagem original (1)
    pic[2].width  = pic[1].width;
    pic[2].height = pic[1].height;

    // Especifica o tamanho inicial em pixels da janela GLUT
    glutInitWindowSize(width, height);

    // Cria a janela passando como argumento o titulo da mesma
    glutCreateWindow("Seam Carving");

    // Registra a funcao callback de redesenho da janela de visualizacao
    glutDisplayFunc(draw);

    // Registra a funcao callback para tratamento das teclas ASCII
    glutKeyboardFunc (keyboard);

    // Cria texturas em memória a partir dos pixels das imagens
    tex[0] = SOIL_create_OGL_texture((unsigned char*) pic[0].img, pic[0].width, pic[0].height, SOIL_LOAD_RGB, SOIL_CREATE_NEW_ID, 0);
    tex[1] = SOIL_create_OGL_texture((unsigned char*) pic[1].img, pic[1].width, pic[1].height, SOIL_LOAD_RGB, SOIL_CREATE_NEW_ID, 0);

    // Exibe as dimensões na tela, para conferência
    printf("Origem  : %s %d x %d\n", argv[1], pic[0].width, pic[0].height);
    printf("Destino : %s %d x %d\n", argv[2], pic[1].width, pic[0].height);
    sel = 0; // pic1

    // Define a janela de visualizacao 2D
    glMatrixMode(GL_PROJECTION);
    gluOrtho2D(0.0,width,height,0.0);
    glMatrixMode(GL_MODELVIEW);

    // Aloca memória para a imagem de saída
    pic[2].img = malloc(pic[1].width * pic[1].height * 3); // W x H x 3 bytes (RGB)
    // Pinta a imagem resultante de preto!
    memset(pic[2].img, 0, width*height*3);

    // Cria textura para a imagem de saída
    tex[2] = SOIL_create_OGL_texture((unsigned char*) pic[2].img, pic[2].width, pic[2].height, SOIL_LOAD_RGB, SOIL_CREATE_NEW_ID, 0);

    // Entra no loop de eventos, não retorna
    glutMainLoop();
}


// Gerencia eventos de teclado
void keyboard(unsigned char key, int x, int y)
{
    if(key==27) {
      // ESC: libera memória e finaliza
      free(pic[0].img);
      free(pic[1].img);
      free(pic[2].img);
      exit(1);
    }
    if(key >= '1' && key <= '3')
        // 1-3: seleciona a imagem correspondente (origem, máscara e resultado)
        sel = key - '1';
    if(key == 's') {
        // Aplica o algoritmo e gera a saida em pic[2].img...
        // ...
        // ... (crie uma função para isso!)

        // Exemplo: pintando tudo de amarelo
        int height = pic[0].height;
        int width = pic[0].width;

        //for(int i=0; i<pic[2].height*pic[2].width; i++)
        //    pic[2].img[i].r = pic[2].img[i].g = 255;

        int pic0Size = height * width;
        RGB matrix[height+1][width];
        printf("height %d\n", height);
        printf("width %d\n", width);
        printf("pic0Size %d\n", pic0Size);
        int j = 0;
        int k = 0;
        for(int i=0; i < pic0Size; i++){
            if((i % width) == 0){
                j++;
                k=0;
            }
            matrix[j][k] = pic[0].img[i];
            k++;
        }

        int *weights = (int *)malloc(height+1 * width * sizeof(int));

        for(int i=0; i< 10; i++){
            printf("i = %d\n", i);
            int proxLinha = i+1;
            for(int j=1; j < 10; j++){
                printf("j = %d\n", j);
                int rOri = matrix[i][j].r;
                int gOri = matrix[i][j].g;
                int bOri = matrix[i][j].b;

                int rMen1 = matrix[proxLinha][j-1].r;
                int gMen1 = matrix[proxLinha][j-1].g;
                int bMen1 = matrix[proxLinha][j-1].b;
                int r1 = rOri - rMen1;
                int g1 = gOri - gMen1;
                int b1 = bOri - bMen1;
                int deltaMen1 = r1*r1 + g1*g1+ b1*b1;

                int pesoIndiceMenor = *(weights + proxLinha*width + (j-1));
                if (pesoIndiceMenor < deltaMen1 || pesoIndiceMenor==0) {
                    pesoIndiceMenor = deltaMen1;
                }

                int rIgu = matrix[proxLinha][j].r;
                int gIgu = matrix[proxLinha][j].g;
                int bIgu = matrix[proxLinha][j].b;
                int r2 = rOri - rIgu;
                int g2 = gOri - gIgu;
                int b2 = bOri - bIgu;
                int deltaIg = r2*r2 + g2*g2+ b2*b2;

                int pesoIndiceIgual = *(weights + proxLinha*width + j);
                if (pesoIndiceIgual < deltaIg || pesoIndiceIgual==0){
                    pesoIndiceIgual = deltaIg;
                }

                int rMais1 = matrix[proxLinha][j+1].r;
                int gMais1 = matrix[proxLinha][j+1].g;
                int bMais1 = matrix[proxLinha][j+1].b;
                int r3 = rOri - rMais1;
                int g3 = gOri - gMais1;
                int b3 = bOri - bMais1;
                int deltaMais1 = r3*r3 + g3*g3+ b3*b3;

                int pesoIndiceMaior = *(weights + proxLinha*width + (j+1));
                if (pesoIndiceMaior < deltaMais1 || pesoIndiceMaior==0){
                    pesoIndiceMaior = deltaMais1;
                }
            }
        }

        for(int o=0; o< height; o++){
            for(int p=0; p< width; p++){
                int weightsOP = *(weights + o*width + p);
                printf("%d\n", weightsOP);
            }
        }

        pic[2].height = height;
        pic[2].width = width;
        int l = 0;
        for(int m=0; m< height; m++){
            for(int n=0; n< width; n++){
                pic[2].img[l] = matrix[m][n];

                l++;
            }
        }
        // Chame uploadTexture a cada vez que mudar
        // a imagem (pic[2])
        uploadTexture();
        free(weights);
    }
    glutPostRedisplay();
}

// Faz upload da imagem para a textura,
// de forma a exibi-la na tela
void uploadTexture()
{
    glEnable(GL_TEXTURE_2D);
    glBindTexture(GL_TEXTURE_2D, tex[2]);
    glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB,
        pic[2].width, pic[2].height, 0,
        GL_RGB, GL_UNSIGNED_BYTE, pic[2].img);
    glDisable(GL_TEXTURE_2D);
}

// Callback de redesenho da tela
void draw()
{
    glClearColor(0.0f, 0.0f, 0.0f, 1.0f);  // Preto
    glClear(GL_COLOR_BUFFER_BIT|GL_DEPTH_BUFFER_BIT);

    // Para outras cores, veja exemplos em /etc/X11/rgb.txt

    glColor3ub(255, 255, 255);  // branco

    // Ativa a textura corresponde à imagem desejada
    glBindTexture(GL_TEXTURE_2D, tex[sel]);
    // E desenha um retângulo que ocupa toda a tela
    glEnable(GL_TEXTURE_2D);
    glBegin(GL_QUADS);

    glTexCoord2f(0,0);
    glVertex2f(0,0);

    glTexCoord2f(1,0);
    glVertex2f(pic[sel].width,0);

    glTexCoord2f(1,1);
    glVertex2f(pic[sel].width, pic[sel].height);

    glTexCoord2f(0,1);
    glVertex2f(0,pic[sel].height);

    glEnd();
    glDisable(GL_TEXTURE_2D);

    // Exibe a imagem
    glutSwapBuffers();
}
