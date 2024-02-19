#include <stdio.h>

#define nV 10 // numero de vertices

#define INF 999 // caso nao haja caminho entre os vertices

void floydWarshall(int matriz[][nV]);
void printMatriz(int matriz[][nV]);

int main() {
  int grafo[nV][nV] = {
  		{0, 3, INF, INF, INF, INF, 5, INF, 2, INF}, 
		{6, 0, INF, INF, 5, INF, 4, INF, INF, 5}, 
		{INF, INF, 0, INF, 4, INF, 6, INF, INF, INF}, 
		{INF, INF, INF, 0, 4, 7, INF, INF, 3, INF}, 
		{INF, 2, 6, 8, 0, 5, INF, INF, INF, 6}, 
		{INF, INF, INF, 5, 4, 0, INF, INF, INF, INF}, 
		{3, 6, 5, INF, INF, INF, 0, INF, INF, INF}, 
		{INF, INF, INF, INF, INF, INF, INF, 0, 3, 8}, 
		{2, INF, INF, 6, INF, INF, INF, 4, 0, INF}, 
		{INF, 6, INF, INF, 4, INF, INF, 6, INF, 0}, 
	};
     
  floydWarshall(grafo);
}

// Algoritmo de Floyd-Warshall
void floydWarshall(int matriz[][nV]) {
	int i, j, k;
	
	for (k = 0; k < nV; k++) {
		for (i = 0; i < nV; i++) {
		  for (j = 0; j < nV; j++) {
		    if ( (matriz[i][k] + matriz[k][j]) < matriz[i][j] ) {
		    	matriz[i][j] = matriz[i][k] + matriz[k][j];
			}
			}
		}
  	}
  
  printMatriz(matriz);
}


void printMatriz(int matriz[][nV]) {
  for (int i = 0; i < nV; i++) {
    for (int j = 0; j < nV; j++) {
      if (matriz[i][j] == INF)
        printf("%4s", "INF");
      else
        printf("%4d", matriz[i][j]);
    }
    printf("\n");
  }
  printf("\n");
}
