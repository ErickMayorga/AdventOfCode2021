#include <iostream>
#include <fstream>
#include <string>

using namespace std;

char* path1 = "measures.txt";
char* path2 = "sums.txt";

int contLines(char* path){
  ifstream file;
  int i, dato, cont = 0;
  file.open(path);
  if(!file)
    cout << "File error" << endl;
  else{
    while(file >> dato){
      cont++;
    }
    file.close();
  }
  return cont;
}

void setArray(int A[], char* path){
  ifstream file;
  int i=0, dato, cont = 0;
  file.open(path);
  if(!file)
    cout << "File error" << endl;
  else{
    while(file >> dato){
      A[i] = dato;
      i++;
    }
    file.close();
  }
}

void writeFile(string text, char* path){
  fstream file;
  string espacio = "\n";
  file.open(path, ios_base::app);
  if (file.is_open()){
    file.write(text.data(), text.size());
    file.write(espacio.data(), espacio.size());
  }  
}

void createMatrix(int matrix[][5], int A[], int n){
  for(int i=0; i<n; i++){
    for(int j=0; j<5; j++){
      if(j==0){
        matrix[i][j] = A[i];
      }else{
        matrix[i][j] = 0;
      }
    }
  }

  int letter = 65, cont = 0, j = 1;

  for(int i=0; i<n; i++){
    if(cont%3 != 0 || cont == 0){
      matrix[i][j] = letter;
      cont++;
    }else{
       i-=3;
      letter++;
      cont=0;

      if(j<4)
        j++;
      else
        j=1;
    }
  }
}

void printMatrix(int A[][5], int n){
  for(int i=0; i<n; i++){
    for(int j=0; j<5; j++){
      cout << A[i][j] << "\t";
    }
    cout << endl;
  }
}

int sumSlidingWindow(int A[][5], int n, int letter){
  int sum = 0;
  for(int i=0; i<n; i++){
    for(int j=0; j<5; j++){
      if(A[i][j]==letter){
        sum += A[i][0];
      }
    }
  }
  return sum;
}

void contLargerMeasures(int measures[], int n){
  int cont = 0;
  for(int i = 0; i<n-1; i++){
    if(measures[i+1]>measures[i]){
      cont++;
    }
  }
  cout << "Counting: " << cont << endl;
}

int main() {
  int n = contLines(path1);
  int measures[n];
  setArray(measures, path1);
  cout << "Larger Measurments" << endl;
  contLargerMeasures(measures, n);

  int matrix[n][5];
  createMatrix(matrix, measures, n);
  //printMatrix(matrix, n);

  int k =65;
  ifstream file(path2);
  if(file.good()){
    remove(path2);
  }
  while(sumSlidingWindow(matrix, n, k)!=0){
    writeFile(to_string(sumSlidingWindow(matrix, n, k)), path2);
    //cout << (char)k << ": " << sumSlidingWindow(matrix, n, k) << endl;;
    k++;
  }

   
  int m = contLines(path2);
  int sums[m];
  setArray(sums, path2);
  cout << "Larger Sums" << endl;
  contLargerMeasures(sums, m);
} 
